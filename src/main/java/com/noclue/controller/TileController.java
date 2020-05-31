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
        this.model.setFiller(new NoBlockModel());
        this.view.setFiller(new NoView());
    }

    public void moveTile(TileController tileModel) {    //moves information on this tile to the one passed as argument
        tileModel.setFiller(model.getFiller());
        tileModel.view.setFiller(view.getFiller());
        blankTile();    //cleans this tile
    }

    public void blankCollectible() {    //sets colletible to null basically
        this.model.setCollectible(new NoCollectibleModel());
        this.view.setCollectible(new NoView());
    }

    public boolean isFilled() {
        return model.getFiller().isFilled();
    }

    public void draw() {
        view.draw();
    }

    public TileView getView() {
        return view;
    }

    public Filler getFiller() {
        return model.getFiller();
    }

    public void setFiller(Filler filler) {
        model.setFiller(filler);
    }

    public Collectible getCollectible() {
        return model.getCollectible();
    }

    public void setCollectible(Collectible collectible) {
        model.setCollectible(collectible);
    }

}
