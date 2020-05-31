package com.noclue.model.state;

import com.noclue.model.Filler;
import com.noclue.model.character.HeroModel;

public class InvincibleIsTouching extends IsTouchingState {
    private HeroModel model;

    public InvincibleIsTouching(HeroModel model) {
        this.setModel(model);
    }

    @Override
    public boolean isTouching(Filler filler) {
        if (getModel().getTouchCounter() <= 0) { //go back to normal state
            getModel().setIsTouchingState(getModel().getNormalIsTouching());
            getModel().setDeactivateState(getModel().getNormalDeactivate());
        }
        getModel().setTouchCounter(getModel().getTouchCounter() - 1); //subtract a step
        filler.deactivate();
        return true;
    }

    @Override
    public void Activate() {    //activates power-up
        if (getModel().getTouchCounter() <= 0) {
            getModel().setTouchCounter(10);
            synchronized (getModel().getIsTouchingState()) {
                synchronized (getModel().getDeactivateState()) {
                    getModel().setIsTouchingState(getModel().getInvencibleIsTouching());
                    getModel().setDeactivateState(getModel().getInvincibleDeactivate());
                }
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
