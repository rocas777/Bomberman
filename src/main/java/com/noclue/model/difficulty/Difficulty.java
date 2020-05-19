package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;

import java.util.ArrayList;

public interface Difficulty {
    ArrayList<Movement> nextMove(Position monster,Position hero, ArrayList<Position> bomb);
}
