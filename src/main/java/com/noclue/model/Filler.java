package com.noclue.model;

public abstract class Filler {
    protected boolean isActive = true;
    public abstract boolean isFilled();
    public abstract boolean deactivate();
    public boolean isActive() {
        return isActive;
    }
}
