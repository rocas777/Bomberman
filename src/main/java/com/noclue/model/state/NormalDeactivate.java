package com.noclue.model.state;

import com.noclue.model.LivesModel;
import com.noclue.model.character.HeroModel;
import com.noclue.timer.TimeListener;

public class NormalDeactivate extends DeactivateState implements TimeListener {
    private HeroModel model;

    public NormalDeactivate(HeroModel model) {
        this.setModel(model);
    }

    @Override
    public boolean deactivate(LivesModel livesModel) {
        getModel().getTimer().addListener(this); //subscribes timer for the timed invincible state
        synchronized (getModel().getDeactivateState()) {   //subtract lide and active Invincible state
            livesModel.setLives(livesModel.getLives() - 1);
            getModel().setDeactivateState(getModel().getInvincibleDeactivate());
        }
        if (livesModel.getLives() == 0) {   //if lifes are 0, deactivate hero
            getModel().setActive(false);
        }
        return false;
    }

    @Override
    public void updateOnTime() {
        getModel().setTimerCount(getModel().getTimerCount() + 1);
        if (getModel().getTimerCount() >= 40) {  //wait
            getModel().getTimer().removeListener(this);  //unsubscribe timer
            getModel().setTimerCount(0);
            synchronized (getModel().getDeactivateState()) { //return to normal
                getModel().setDeactivateState(getModel().getNormalDeactivate());
            }
        }
    }

    public HeroModel getModel() {
        return model;
    }

    public void setModel(HeroModel model) {
        this.model = model;
    }
}
