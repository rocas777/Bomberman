package com.noclue.model;

import com.noclue.controller.TileController;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Grid {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();

    public void add_column() {
        tiles.add(new CopyOnWriteArrayList<>());
    }

    public TileController getTile(Position position) {
        return tiles.get(position.getY()).get(position.getX());
    }

    public void setTiles(TileController tiles, Position position) {
        this.tiles.get(position.getY()).set(position.getX(), tiles);
    }

    public void setTiles(CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles){
        this.tiles = tiles;
    }

    public void addTile(){
        tiles.get(tiles.size()-1).add(null);
    }


    public CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> getTiles() {
        return tiles;
    }
}
