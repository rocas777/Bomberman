package com.noclue.model;


import com.noclue.model.collectible.Collectible;

public class TileModel {
    private Filler filler;
    private Collectible collectible;

    public TileModel(Collectible collectible, Filler filler) {
        this.setCollectible(collectible);
        this.setFiller(filler);
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
        return getFiller().isFilled();
    }


}
