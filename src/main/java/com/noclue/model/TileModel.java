package com.noclue.model;


import com.noclue.model.collectible.Collectible;

public class TileModel {
    final Position position;
    Filler filler;
    Collectible collectible;

    public TileModel(Position position, Collectible collectible, Filler filler) {
        this.collectible = collectible;
        this.filler = filler;
        this.position = position;
    }

    public Filler getFiller() {
        return filler;
    }

    public void setFiller(Filler filler) {
        this.filler = filler;
    }

    public Collectible getCollectible() {
        return collectible;
    }

    public void setCollectible(Collectible collectible) {
        this.collectible = collectible;
    }

    public boolean isFilled() {
        return filler.isFilled();
    }


}
