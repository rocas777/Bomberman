package com.noclue.controller;

import com.noclue.model.BombModel;
import com.noclue.view.BombView;

public class BombController implements TimeListener {
    BombModel model;
    BombView view;

    BombController(BombModel model,BombView view){
        this.view=view;
        this.model=model;
    }

    @Override
    public void updateOnTime() {
        model.setSum(model.getSum()+1);
        if(model.getSum()*20>=model.getMseconds()) {
            model.getExplosionListener().explode(model.getPosition());
        }
    }

    public void draw(){
        view.draw();
    }
}
