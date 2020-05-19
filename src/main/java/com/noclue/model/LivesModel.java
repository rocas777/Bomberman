package com.noclue.model;

public class LivesModel {
    int lives;
    Position position;

    public LivesModel(int lives, Position position) {
        this.lives = lives;
        this.position = position;
    }

    public LivesModel(Position position) {
        this.position = position;
        lives=3;
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
