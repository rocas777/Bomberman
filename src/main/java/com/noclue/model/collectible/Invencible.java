package com.noclue.model.collectible;

import com.noclue.model.Position;

public class Invencible implements Collectible{
    Position position;
    public Invencible(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
