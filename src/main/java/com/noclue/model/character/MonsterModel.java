package com.noclue.model.character;

import com.noclue.Movement;
import com.noclue.model.Filler;
import com.noclue.model.Grid;
import com.noclue.model.Position;
import com.noclue.model.difficulty.Difficulty;

import java.util.ArrayList;

public class MonsterModel extends Filler implements Character {
    final private Difficulty difficulty;
    private Position position;

    public MonsterModel(Difficulty difficulty, Position position) {
        this.setPosition(position);
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
        return getDifficulty().nextMove(this.getPosition(), position, bomb);
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

    //methods to move monster in each direction

    public void moveLeft(Grid grid) {
        grid.getTile(getPosition()).moveTile(grid.getTile(getPosition().getLeft()));
        getPosition().setLeft();
    }

    public void moveRight(Grid grid) {
        grid.getTile(getPosition()).moveTile(grid.getTile(getPosition().getRight()));
        getPosition().setRight();
    }

    public void moveUp(Grid grid) {
        grid.getTile(getPosition()).moveTile(grid.getTile(getPosition().getUp()));
        getPosition().setUp();
    }

    public void moveDown(Grid grid) {
        grid.getTile(getPosition()).moveTile(grid.getTile(getPosition().getDown()));
        getPosition().setDown();
    }
}
