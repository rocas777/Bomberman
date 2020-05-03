package com.noclue.model.block;


import com.noclue.model.Position;

public class IndestructibleBlockModel implements Block {
    Position position;
    public IndestructibleBlockModel(Position position){
        this.position=position;
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
}
