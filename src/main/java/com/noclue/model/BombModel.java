package com.noclue.model;

import com.noclue.ExplosionListener;
import com.noclue.timer.TimerInterface;

import java.util.ArrayList;

public class BombModel {
    private int mseconds;
    private int sum = 0;
    private ExplosionListener explosionListener;
    private Position position;
    private TimerInterface timerInterface;
    private ArrayList<Position> explosionList;

    public TimerInterface getTimerInterface() {
        return timerInterface;
    }

    public void setExplosionList(ArrayList<Position> explosionList) {
        this.explosionList = explosionList;
    }

    public ArrayList<Position> getExplosionList() {
        return explosionList;
    }

    public BombModel(int mseconds, ExplosionListener explosionListener, Position position, TimerInterface timer){
        synchronized (explosionListener) {
            this.explosionListener = explosionListener;
        }
        this.mseconds=mseconds;
        this.position=position;
        timerInterface=timer;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getMseconds() {
        return mseconds;
    }

    public ExplosionListener getExplosionListener() {
        return explosionListener;
    }

    public Position getPosition() {
        return position;
    }
}
