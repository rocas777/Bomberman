package com.noclue.controller;

import com.noclue.model.Filler;
import com.noclue.model.TileModel;
import com.noclue.model.block.NoBlockModel;
import com.noclue.model.collectible.Collectible;
import com.noclue.model.collectible.NoCollectibleModel;
import com.noclue.view.NoView;
import com.noclue.view.TileView;

public class TileController implements Cloneable {
    private final TileModel model;
    private final TileView view;

    TileController(TileModel model, TileView view) {
        this.model = model;
        this.view = view;
    }

    public void blankTile() {   //sets tile to have no filler and thus it will draw nothing
        this.getModel().setFiller(new NoBlockModel());
        this.getView().setFiller(new NoView());
    }

    public void moveTile(TileController tileModel) {    //moves information on this tile to the one passed as argument
        tileModel.setFiller(getModel().getFiller());
        tileModel.getView().setFiller(getView().getFiller());
        blankTile();    //cleans this tile
    }

    public void blankCollectible() {    //sets colletible to null basically
        this.getModel().setCollectible(new NoCollectibleModel());
        this.getView().setCollectible(new NoView());
    }

    public boolean isFilled() {
        return getModel().getFiller().isFilled();
    }

    public void draw() {
        getView().draw();
    }

    public TileView getView() {
        return view;
    }

    public Filler getFiller() {
        return getModel().getFiller();
    }

    public void setFiller(Filler filler) {
        getModel().setFiller(filler);
    }

    public Collectible getCollectible() {
        return getModel().getCollectible();
    }

    public void setCollectible(Collectible collectible) {
        getModel().setCollectible(collectible);
    }

    public TileModel getModel() {
        return model;
    }
}
