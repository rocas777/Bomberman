package com.noclue.model.character;

import com.noclue.Movement;
import com.noclue.model.Filler;
import com.noclue.model.Grid;
import com.noclue.model.Position;
import com.noclue.model.difficulty.Difficulty;

import java.util.ArrayList;

public class MonsterModel extends Filler implements Character {
    final private Difficulty difficulty;
    Position position;

    public MonsterModel(Difficulty difficulty, Position position) {
        this.position = position;
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public boolean isFilled() {
        return true;
    }

    @Override
    public boolean deactivate() {
        isActive = false;
        return true;
    }

    public ArrayList<Movement> nextMove(Position position, ArrayList<Position> bomb) {
        return difficulty.nextMove(this.position, position, bomb);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isTouching(Filler filler) {
        if (filler.isFilled())
            return false;
        filler.deactivate();
        return true;
    }

    public void moveLeft(Grid grid) {
        grid.getTile(position).moveTile(grid.getTile(position.getLeft()));
        position.setLeft();
    }

    public void moveRight(Grid grid) {
        grid.getTile(position).moveTile(grid.getTile(position.getRight()));
        position.setRight();
    }

    public void moveUp(Grid grid) {
        grid.getTile(position).moveTile(grid.getTile(position.getUp()));
        position.setUp();
    }

    public void moveDown(Grid grid) {
        grid.getTile(position).moveTile(grid.getTile(position.getDown()));
        position.setDown();
    }
}
