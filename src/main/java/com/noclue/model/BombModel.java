package com.noclue.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.ExplosionListener;
import com.noclue.TimerInterface;
import com.noclue.model.character.TimeListener;

import static com.googlecode.lanterna.SGR.BOLD;

public class BombModel {
    private int mseconds;
    private int sum = 0;
    private ExplosionListener explosionListener;
    Position position;
    TimerInterface timer;

    public BombModel(int mseconds, ExplosionListener explosionListener, Position position, TimerInterface timer){
        synchronized (explosionListener) {
            this.explosionListener = explosionListener;
        }
        this.mseconds=mseconds;
        this.position=position;
        this.timer = timer;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public TimerInterface getTimer() {
        return timer;
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
