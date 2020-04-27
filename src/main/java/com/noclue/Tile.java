package com.noclue;

import com.noclue.Block.Block;
import com.noclue.Collectible.Collectible;

public class Tile {
    private Filler filler;
    private Collectible collectible;
    private int x;
    private int y;

    Tile(int x,int y){
        this.x=x;
        this.y=y;
    }

    public Filler getFiller() {
        return filler;
    }
    public Collectible getCollectible() {
        return collectible;
    }

    public void setFiller(Filler filler) {
        this.filler = filler;
    }
    public void setCollectible(Collectible collectible) {
        this.collectible = collectible;
    }
    public void explode(){
        filler.explode();
    }
    public void collect(){
        collectible.collect();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
