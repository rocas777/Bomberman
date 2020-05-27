package com.noclue.view;

import com.noclue.IView;
import com.noclue.model.TileModel;

public class TileView implements IView {
    IView filler;
    IView collectible;
    TileModel model;

    public TileView(TileModel model) {
        this.model = model;
    }

    @Override
    public void draw() {
        if (collectible != null)
            collectible.draw();
        if (filler != null)
            filler.draw();
    }

    public IView getCollectible() {
        return collectible;
    }

    public void setCollectible(IView collectible) {
        this.collectible = collectible;
    }

    public IView getFiller() {
        return filler;
    }

    public void setFiller(IView filler) {
        this.filler = filler;
    }
}
