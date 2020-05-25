package com.noclue.model.collectible;

import com.noclue.model.Position;

public class AddTime implements Collectible{
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public AddTime(Position position) {
        this.position = position;
    }
}
