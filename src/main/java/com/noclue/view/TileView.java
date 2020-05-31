package com.noclue.view;

import com.noclue.IView;
import com.noclue.model.TileModel;

public class TileView implements IView {
    private IView filler;
    private IView collectible;
    private TileModel model;

    public TileView(TileModel model) {
        this.model = model;
    }

    @Override
    public void draw() {
        if (getCollectible() != null)
            getCollectible().draw();
        if (getFiller() != null)
            getFiller().draw();
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
