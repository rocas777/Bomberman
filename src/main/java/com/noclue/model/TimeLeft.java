package com.noclue.model;

public class TimeLeft {
    private int seconds;
    private Position position;

    public TimeLeft(int seconds, Position position) {
        this.setSeconds(seconds);
        this.setPosition(position);
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

    public void minusSecond() {
        this.setSeconds(this.getSeconds() - 1);
    }

    public void addTime() {
        this.setSeconds(this.getSeconds() + 15);
    }
}
