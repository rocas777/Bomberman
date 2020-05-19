package com.noclue.model.character;

import com.noclue.model.Filler;
import com.noclue.model.Position;

public class HeroModel extends Filler implements Character {
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
    public boolean isTouching(Filler filler) {
        return false;
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public boolean deactivate() {
        isActive = false;
        return true;
    }


}
