package com.noclue.model.state;

import com.noclue.model.Filler;
import com.noclue.model.character.HeroModel;

public class InvencibleIsTouching extends IsTouchingState {
    HeroModel model;

    public InvencibleIsTouching(HeroModel model) {
        this.model = model;
    }

    @Override
    public boolean isTouching(Filler filler) {
        if (model.getTouchCounter() <= 0) {
            model.setIsTouchingState(model.getNormalIsTouching());
            model.setDeactivateState(model.getNormalDeactivate());
        }
        model.setTouchCounter(model.getTouchCounter() - 1);
        filler.deactivate();
        return true;
    }

    @Override
    public void Activate() {
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
