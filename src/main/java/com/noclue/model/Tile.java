package com.noclue.model;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Filler;
import com.noclue.model.collectible.Collectible;

public class Tile {
    Filler filler;
    Collectible collectible;
    final Position position;

    public Tile(Position position, Collectible collectible, Filler filler){
        this.collectible=collectible;
        this.filler=filler;
        this.position=position;
    }

    public Filler getFiller() {
        return filler;
    }

    public Collectible getCollectible() {
        return collectible;
    }

    public void setCollectible(Collectible collectible) {
        this.collectible = collectible;
    }

    public void setFiller(Filler filler) {
        this.filler = filler;
    }

    public boolean isFilled(){
        return filler.isFilled();
    }
}
