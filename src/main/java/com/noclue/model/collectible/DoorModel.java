package com.noclue.model.collectible;

import com.noclue.model.Position;

public class DoorModel implements Collectible {
    Position position;
    public DoorModel(Position position){
        this.position=position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
