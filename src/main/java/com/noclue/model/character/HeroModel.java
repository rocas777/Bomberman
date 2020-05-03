package com.noclue.model.character;

import com.noclue.model.Position;

public class HeroModel implements Character {
    Position position;
    public HeroModel(Position position){
        this.position=position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isFilled() {
        return false;
    }
}
