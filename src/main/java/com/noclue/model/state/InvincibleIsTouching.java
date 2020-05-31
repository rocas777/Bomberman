package com.noclue.model.state;

import com.noclue.model.Filler;
import com.noclue.model.character.HeroModel;

public class InvincibleIsTouching extends IsTouchingState {
    HeroModel model;

    public InvincibleIsTouching(HeroModel model) {
        this.model = model;
    }

    @Override
    public boolean isTouching(Filler filler) {
        if (model.getTouchCounter() <= 0) { //go back to normal state
            model.setIsTouchingState(model.getNormalIsTouching());
            model.setDeactivateState(model.getNormalDeactivate());
        }
        model.setTouchCounter(model.getTouchCounter() - 1); //subtract a step
        filler.deactivate();
        return true;
    }

    @Override
    public void Activate() {    //activates power-up
        if (model.getTouchCounter() <= 0) {
            model.setTouchCounter(10);
            synchronized (model.getIsTouchingState()) {
                synchronized (model.getDeactivateState()) {
                    model.setIsTouchingState(model.getInvencibleIsTouching());
                    model.setDeactivateState(model.getInvincibleDeactivate());
                }
            }
        }
    }
}
