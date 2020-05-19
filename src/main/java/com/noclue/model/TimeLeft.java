package com.noclue.model;

public class TimeLeft {
    int seconds;
    Position position;

    public TimeLeft(int seconds, Position position) {
        this.seconds = seconds;
        this.position = position;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void minusSecond(){
        this.seconds--;
    }
}
