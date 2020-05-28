package com.noclue.model.state;

import com.noclue.model.Filler;

public abstract class IsTouchingState {
    public abstract boolean isTouching(Filler filler);

    public abstract void Activate();
}
