package com.noclue.model.State;

import com.noclue.model.Filler;

public class NormalIsTouching extends IsTouchingHimselfState{

    @Override
    public boolean isTouching(Filler filler) {
        return false;
    }
}
