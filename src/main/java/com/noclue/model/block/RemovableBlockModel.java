package com.noclue.model.block;

import com.noclue.model.Filler;
import com.noclue.model.Position;

public class RemovableBlockModel extends Filler {
    Position position;

    public RemovableBlockModel(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isFilled() {
        return true;
    }

    @Override
    public boolean deactivate() {
        isActive = false;
        return true;
    }
}
