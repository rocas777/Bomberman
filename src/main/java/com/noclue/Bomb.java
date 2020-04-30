package com.noclue;

import com.noclue.character.TimeListener;

public class Bomb implements TimeListener {
    private int seconds;
    private int sum;
    private ExplosionListener explosionListener;
    Position position;
    public Bomb(int seconds,ExplosionListener explosionListener,Position position){
        this.explosionListener=explosionListener;
        this.seconds=seconds;
        this.position=position;
    }

    @Override
    public void updateOnTime() {
        sum++;
        if(sum*0.5>=seconds)
            explosionListener.explode(position);
    }
}
