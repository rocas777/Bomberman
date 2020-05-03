package com.noclue.model;

import com.noclue.model.block.Block;

public class RemovableBlockModel implements Block {
    Position position;
    public RemovableBlockModel(Position position){
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
