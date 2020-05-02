package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.view.BombViewFire;
import com.noclue.view.BombViewTicking;
import com.noclue.view.IView;

public class BombController implements TimeListener {
    private BombModel model;
    private IView viewTicking;
    private IView viewFire;
    private IView view;
    private TextGraphics textGraphics;

    BombController(BombModel model, TextGraphics textGraphics){
        this.viewTicking=new BombViewTicking(textGraphics,model);
        this.viewFire= new BombViewFire(textGraphics,model);
        view=viewTicking;
        this.model=model;
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

    @Override
    public synchronized void updateOnTime() {
        model.setSum(model.getSum()+1);
        if (model.getSum()*20>=250+model.getMseconds()) {
            model.getTimerInterface().removeListener(this);
            model.getExplosionListener().fireDone(model.getPosition());
        }
        else if(model.getSum()*20>=model.getMseconds()) {
            model.getExplosionListener().explode(model.getPosition());
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
