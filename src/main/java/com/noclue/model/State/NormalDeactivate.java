package com.noclue.model.State;

import com.noclue.model.LivesModel;
import com.noclue.model.character.HeroModel;
import com.noclue.timer.TimeListener;

public class NormalDeactivate extends DeactivateState implements TimeListener {
    HeroModel model;
    public NormalDeactivate(HeroModel model){
        this.model = model;
    }
    @Override
    public boolean deactivate(LivesModel livesModel) {
        model.getTimer().addListener(this);
        synchronized (model.getDeactivateState()) {
            livesModel.setLives(livesModel.getLives()-1);
            model.setDeactivateState(model.getInvincibleDeactivate());
        }
        if(livesModel.getLives()==0){
            model.setActive(false);
        }
        return false;
    }

    @Override
    public void updateOnTime() {
        model.setTimerCount(model.getTimerCount()+1);
        if(model.getTimerCount()>=40){
            model.getTimer().removeListener(this);
            model.setTimerCount(0);
            synchronized (model.getDeactivateState()) {
                model.setDeactivateState(model.getNormalDeactivate());
            }
        }
    }
}
