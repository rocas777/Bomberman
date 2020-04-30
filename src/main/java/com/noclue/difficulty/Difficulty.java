package com.noclue.difficulty;

import com.noclue.Movement;
import com.noclue.Position;

import java.util.ArrayList;

public interface Difficulty {
    ArrayList<Movement> nextMove(Position position);
}
