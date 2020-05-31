package com.noclue.model;

public class LivesModel {
    private int lives;
    private Position position;

    public LivesModel(int lives, Position position) {
        this.setLives(lives);
        this.setPosition(position);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
