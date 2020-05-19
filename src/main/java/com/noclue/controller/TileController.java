package com.noclue.controller;

import com.noclue.model.Filler;
import com.noclue.model.TileModel;
import com.noclue.model.block.NoBlockModel;
import com.noclue.model.collectible.NoCollectibleModel;
import com.noclue.model.collectible.Collectible;
import com.noclue.view.NoView;
import com.noclue.view.TileView;

public class TileController implements Cloneable {
    private TileModel model;
    private TileView view;
    TileController(TileModel model, TileView view){
        this.model = model;
        this.view = view;
    }
    public void blankTile(){
        this.model.setFiller(new NoBlockModel());
        //this.model.setCollectible(new NoCollectibleModel());
        this.view.setFiller(new NoView());
    }

    public void moveTile(TileController tileModel){
        tileModel.setFiller(model.getFiller());
        //tileModel.setCollectible(model.getCollectible());
        tileModel.view.setFiller(view.getFiller());
        //tileModel.view.setCollectible(view.getCollectible());
        blankTile();
    }
    public void blankCollectible(){
        this.model.setCollectible(new NoCollectibleModel());
        this.view.setCollectible(new NoView());
    }
    public boolean isFilled(){
        return model.getFiller().isFilled();
    }
    public void draw(){
        view.draw();
    }

    public TileView getView() {
        return view;
    }
    public Filler getFiller(){
        return model.getFiller();
    }

    public Collectible getCollectible(){
        return model.getCollectible();
    }
    public void setCollectible(Collectible collectible) {
        model.setCollectible(collectible);
    }

    public void setFiller(Filler filler) {
        model.setFiller(filler);
    }
}
