package com.noclue.model.State;

import com.noclue.model.Filler;
import com.noclue.model.character.HeroModel;

public class NormalIsTouching extends IsTouchingState {
    HeroModel model;

    public NormalIsTouching(HeroModel model) {
        this.model = model;
    }

    @Override
    public boolean isTouching(Filler filler) {
        return false;
    }

    @Override
    public void Activate() {
        if (model.getTouchCounter() <= 0) {
            model.setTouchCounter(10);
            synchronized (model.getIsTouchingState()) {
                synchronized (model.getDeactivateState()) {
                    model.getTimer().removeListeners();
                    model.setIsTouchingState(model.getInvencibleIsTouching());
                    model.setDeactivateState(model.getInvincibleDeactivate());
                }
            }
        }
    }
}
