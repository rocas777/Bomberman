package com.noclue.model.state;

import com.noclue.model.LivesModel;
import com.noclue.model.character.HeroModel;
import com.noclue.timer.TimeListener;

public class NormalDeactivate extends DeactivateState implements TimeListener {
    HeroModel model;

    public NormalDeactivate(HeroModel model) {
        this.model = model;
    }

    @Override
    public boolean deactivate(LivesModel livesModel) {
        model.getTimer().addListener(this); //subscribes timer for the timed invincible state
        synchronized (model.getDeactivateState()) {   //subtract lide and active Invincible state
            livesModel.setLives(livesModel.getLives() - 1);
            model.setDeactivateState(model.getInvincibleDeactivate());
        }
        if (livesModel.getLives() == 0) {   //if lifes are 0, deactivate hero
            model.setActive(false);
        }
        return false;
    }

    @Override
    public void updateOnTime() {
        model.setTimerCount(model.getTimerCount() + 1);
        if (model.getTimerCount() >= 40) {  //wait
            model.getTimer().removeListener(this);  //unsubscribe timer
            model.setTimerCount(0);
            synchronized (model.getDeactivateState()) { //return to normal
                model.setDeactivateState(model.getNormalDeactivate());
            }
        }
    }
}
