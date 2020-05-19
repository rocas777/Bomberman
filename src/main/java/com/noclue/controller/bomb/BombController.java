package com.noclue.controller.bomb;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IBombInterface;
import com.noclue.IView;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.view.bomb.BombViewFire;
import com.noclue.view.bomb.BombViewTicking;

import java.util.ArrayList;

public class BombController implements IBombInterface {
    private BombModel model;
    private IView viewTicking;
    private IView viewFire;
    private IView view;
    private int sum=0;
    private TextGraphics textGraphics;

    public BombController(BombModel model, TextGraphics textGraphics){
        this.viewTicking=new BombViewTicking(textGraphics,model);
        this.viewFire= new BombViewFire(textGraphics,model);
        view=viewTicking;
        this.model=model;
    }

    public int getSum() {
        return sum;
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
        if (getSum()*20>=250+model.getMseconds()) {
            model.getTimerInterface().removeListener(this);
            model.getExplosionListener().fireDone();
        }
        else if(getSum()*20>=model.getMseconds()) {
            ArrayList<Position> arrayList = new ArrayList();
            arrayList.add(model.getPosition().getRight());
            arrayList.add(model.getPosition().getRight().getRight());
            arrayList.add(model.getPosition().getLeft());
            arrayList.add(model.getPosition().getLeft().getLeft());
            arrayList.add(model.getPosition().getDown());
            arrayList.add(model.getPosition().getDown().getDown());
            arrayList.add(model.getPosition().getUp());
            arrayList.add(model.getPosition().getUp().getUp());
            arrayList.add(model.getPosition());
            model.getExplosionListener().explode(arrayList);
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
