package com.noclue.controller.bomb;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IBombInterface;
import com.noclue.IView;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.timer.Timer;
import com.noclue.view.bomb.BombViewFire;
import com.noclue.view.bomb.BombViewTicking;

import java.util.ArrayList;

public class BombController implements IBombInterface {
    private BombModel model;
    private IView viewTicking;
    private IView viewFire;
    private IView view;
    private int sum=0;
    private ArrayList<Position> explosionList = new ArrayList();
    private TextGraphics textGraphics;

    public BombController(BombModel model, TextGraphics textGraphics){
        this.viewTicking=new BombViewTicking(textGraphics,model);
        this.viewFire= new BombViewFire(textGraphics,model);
        view=viewTicking;
        this.model=model;

        explosionList.add(model.getPosition().getRight());
        explosionList.add(model.getPosition().getRight().getRight());
        explosionList.add(model.getPosition().getLeft());
        explosionList.add(model.getPosition().getLeft().getLeft());
        explosionList.add(model.getPosition().getDown());
        explosionList.add(model.getPosition().getDown().getDown());
        explosionList.add(model.getPosition().getUp());
        explosionList.add(model.getPosition().getUp().getUp());
        explosionList.add(model.getPosition());
    }

    public int getSum() {
        return sum;
    }


    public ArrayList<Position> getExplosionList() {
        return explosionList;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public BombModel getModel() {
        return model;
    }

    public IView getView() {
        return view;
    }

    public void setViewFire(IView viewFire) {
        this.viewFire = viewFire;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public void setViewTicking(IView viewTicking) {
        this.viewTicking = viewTicking;
    }

    @Override
    public synchronized void updateOnTime() {
        setSum(getSum()+1);
        if (getSum()* Timer.getSeconds() >=250+model.getMseconds()) {
            model.getTimerInterface().removeListener(this);
            model.getExplosionListener().fireDone();
        }
        else if(getSum()*Timer.getSeconds()>=model.getMseconds()) {
            model.getExplosionListener().explode(explosionList);
            synchronized (view) {
                view = viewFire;
            }
        }
    }

    public synchronized void draw(){
        synchronized (view) {
            view.draw();
        }
    }
}
