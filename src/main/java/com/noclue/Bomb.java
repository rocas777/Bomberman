package com.noclue;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.character.TimeListener;

import static com.googlecode.lanterna.SGR.BOLD;

public class Bomb implements TimeListener {
    private int mseconds;
    private int sum = 0;
    private ExplosionListener explosionListener;
    Position position;
    TimerInterface timer;

    public Bomb(int mseconds,ExplosionListener explosionListener,Position position,TimerInterface timer){
        synchronized (explosionListener) {
            this.explosionListener = explosionListener;
        }
        this.mseconds=mseconds;
        this.position=position;
        this.timer = timer;
        timer.addListener(this);
        timer.start();
    }

    @Override
    public void updateOnTime() {
        sum++;
        if(sum*timer.getMSeconds()>mseconds) {
            synchronized (explosionListener) {
                explosionListener.explode(position);
            }
            timer.removeListener(this);
        }
    }
    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+1,"++",BOLD);
    }
}
