package com.noclue.controller;

import com.noclue.model.BombModel;
import com.noclue.view.BombView;

public class BombController implements TimeListener {
    BombModel model;
    BombView view;

    BombController(BombModel model,BombView view){
        this.view=view;
        this.model=model;

        model.getTimer().addListener(this);
        model.getTimer().start();
    }

    @Override
    public void updateOnTime() {
        model.setSum(model.getSum()+1);
        if(model.getSum()*model.getTimer().getMSeconds()>model.getMseconds()) {
            model.getExplosionListener().explode(model.getPosition());
            model.getTimer().removeListener(this);
        }
        view.draw();
    }
}
