package com.noclue.model.block;

import com.noclue.CanExplode;
import com.noclue.model.Position;

public class RemovableBlockModel implements Block, CanExplode {
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
