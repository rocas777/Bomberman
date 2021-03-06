package com.noclue.model.block;


import com.noclue.model.Filler;
import com.noclue.model.Position;

public class IndestructibleBlockModel extends Filler {
    private Position position;

    public IndestructibleBlockModel(Position position) {
        this.setPosition(position);
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
        return false;
    }
}
