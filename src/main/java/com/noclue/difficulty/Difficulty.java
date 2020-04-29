package com.noclue.difficulty;

import com.noclue.Position;

import java.util.ArrayList;

public interface Difficulty {
    ArrayList<Position> nextMove(Position position);
}
