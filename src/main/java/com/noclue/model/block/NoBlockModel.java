package com.noclue.model.block;

import com.noclue.model.Filler;

public class NoBlockModel extends Filler {
    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public boolean deactivate() {
        this.isActive = false;
        return true;
    }
}
