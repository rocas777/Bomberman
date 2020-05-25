package com.noclue.model.State;

import com.noclue.model.Filler;

public class InvencibleIsTouching extends IsTouchingHimselfState{
    @Override
    public boolean isTouching(Filler filler) {
        filler.deactivate();
        return  true;
    }
}
