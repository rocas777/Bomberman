package com.noclue.model.character;

import com.noclue.Movement;
import com.noclue.model.Filler;
import com.noclue.model.Position;
import com.noclue.model.difficulty.Difficulty;

import java.util.ArrayList;

public class MonsterModel extends Filler implements Character {
    final private Difficulty difficulty;
    Position position;

    @Override
    public boolean isFilled() {
        return true;
    }

    @Override
    public boolean deactivate() {
        isActive = false;
        return true;
    }

    public MonsterModel(Difficulty difficulty,Position position){
        this.position=position;
        this.difficulty=difficulty;
    }

    public ArrayList<Movement> nextMove(Position position){
        return difficulty.nextMove(this.position,position);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public boolean isTouching(Filler filler) {
        if(filler.isFilled())
            return false;
        else
            filler.deactivate();
        return true;
    }
}
