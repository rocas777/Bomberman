package com.noclue.model;

import com.noclue.controller.TileController;

import java.util.concurrent.CopyOnWriteArrayList;

public class Grid {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();

    Grid() {

    }

    public void add_collumn() {
        tiles.add(new CopyOnWriteArrayList<>());
    }

    public void addTile(TileController tileController) {
        tiles.get(tiles.size() - 1).add(tileController);
    }

    public TileController getTile(Position position) {
        return tiles.get(position.getY()).get(position.getX());
    }

    public void setTiles(TileController tiles, Position position) {
        this.tiles.get(position.getY()).set(position.getX(), tiles);
    }

    public void addTile(TileController tiles, Position position) {
        this.tiles.get(position.getY()).add(tiles);
    }


    public CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> getTiles() {
        return tiles;
    }
}
