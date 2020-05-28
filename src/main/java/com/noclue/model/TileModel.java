package com.noclue.model;


import com.noclue.model.collectible.Collectible;

public class TileModel {
    Filler filler;
    Collectible collectible;

    public TileModel( Collectible collectible, Filler filler) {
        this.collectible = collectible;
        this.filler = filler;
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
