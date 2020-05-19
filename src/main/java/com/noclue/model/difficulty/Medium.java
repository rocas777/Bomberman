package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;

import java.util.ArrayList;

public class Medium implements Difficulty {
    @Override
    public ArrayList<Movement> nextMove(Position monster, Position hero) {
        return null;
    }
}
