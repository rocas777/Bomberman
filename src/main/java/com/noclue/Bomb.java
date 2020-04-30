package com.noclue;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.character.TimeListener;

import static com.googlecode.lanterna.SGR.BOLD;

public class Bomb implements TimeListener {
    private int mseconds;
    private int sum;
    private ExplosionListener explosionListener;
    Position position;
    public Bomb(int mseconds,ExplosionListener explosionListener,Position position){
        synchronized (explosionListener) {
            this.explosionListener = explosionListener;
        }
        this.mseconds=mseconds;
        this.position=position;
    }
    public void start(){
        Timer.addListener(this);
    }

    @Override
    public void updateOnTime() {
        sum++;
        System.out.println(sum);
        if(sum*Timer.getMSeconsd()>=mseconds) {
            synchronized (explosionListener) {
                explosionListener.explode(position);
            }
            Timer.removeListener(this);
        }
    }
    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+1,"++",BOLD);
    }
}
