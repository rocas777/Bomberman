package com.noclue.model.state;

import com.noclue.model.Filler;
import com.noclue.model.character.HeroModel;

public class NormalIsTouching extends IsTouchingState {
    private HeroModel model;

    public NormalIsTouching(HeroModel model) {
        this.setModel(model);
    }

    @Override
    public boolean isTouching(Filler filler) {
        return false;
    }

    @Override
    public void Activate() {
        if (getModel().getTouchCounter() <= 0) {
            getModel().setTouchCounter(10);
            synchronized (getModel().getIsTouchingState()) {
                synchronized (getModel().getDeactivateState()) {
                    getModel().getTimer().removeListeners();
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
