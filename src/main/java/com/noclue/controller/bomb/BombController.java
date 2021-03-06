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
    private final BombModel model;
    private final ArrayList<Position> explosionList = new ArrayList();
    private IView viewTicking;
    private IView viewFire;
    private IView view;
    private int sum = 0;

    public BombController(BombModel model, TextGraphics textGraphics) {
        this.viewTicking = new BombViewTicking(textGraphics, model);
        this.viewFire = new BombViewFire(textGraphics, model);
        view = viewTicking;
        this.model = model;

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

    public IView getViewTicking() {
        return viewTicking;
    }

    public void setViewTicking(IView viewTicking) {
        this.viewTicking = viewTicking;
    }

    public void setViewFire(IView viewFire) {
        this.viewFire = viewFire;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public TextGraphics getTextGraphics() {
        return null;
    }

    public ArrayList<Position> getExplosionList() {
        return explosionList;
    }

    public BombModel getModel() {
        return model;
    }

    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public synchronized void updateOnTime() {
        sum++;
        if (sum * Timer.getSeconds() >= 250 + model.getMseconds()) {    //stop explosion
            model.getTimerInterface().removeListener(this);     //unsubscribe timer
            model.getExplosionListener().fireDone();
        } else if (sum * Timer.getSeconds() >= model.getMseconds()) {   //explode and change view
            model.getExplosionListener().explode(explosionList);
            synchronized (view) {
                view = viewFire;
            }
        }
    }

    public synchronized void draw() {
        synchronized (view) {
            view.draw();
        }
    }
}
