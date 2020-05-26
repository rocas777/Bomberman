package com.noclue.model.State;

import com.noclue.model.Filler;
import com.noclue.model.LivesModel;

public abstract class IsTouchingState {
    public abstract boolean isTouching(Filler filler);
    public abstract void Activate();
}
