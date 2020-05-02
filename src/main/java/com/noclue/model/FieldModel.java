package com.noclue.model;

import com.noclue.*;
import com.noclue.controller.BombController;
import com.noclue.controller.KeyBoard;
import com.noclue.controller.TimeListener;
import com.noclue.controller.Timer;
import com.noclue.view.IView;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class FieldModel  {
    private final int width;
    private final int height;
    final private CopyOnWriteArrayList<CopyOnWriteArrayList<Tile>> tiles;
    private Position hero_pos;
    private CopyOnWriteArrayList<Position> monsters=new CopyOnWriteArrayList<>();
    BombController bombController =null;
    KeyBoard kServer;
    Timer tServer;
    CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> views = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> getViews() {
        return views;
    }

    public void setMonsters(CopyOnWriteArrayList<Position> monsters) {
        this.monsters = monsters;
    }

    public void setViews(CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> views) {
        this.views = views;
    }

    public FieldModel(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new CopyOnWriteArrayList<>();
    }

    public void setkServer(KeyBoard kServer) {
        this.kServer = kServer;
    }

    public void settServer(Timer tServer) {
        this.tServer = tServer;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CopyOnWriteArrayList<CopyOnWriteArrayList<Tile>> getTiles() {
        return tiles;
    }

    public CopyOnWriteArrayList<Position> getMonsters() {
        return monsters;
    }

    public BombController getBomb() {
        return bombController;
    }

    public KeyBoard getkServer() {
        return kServer;
    }

    public Position getHero_pos() {
        return hero_pos;
    }

    public Timer gettServer() {
        return tServer;
    }

    public void setHero_pos(Position hero_pos) {
        this.hero_pos = hero_pos;
    }

    public void setBombModel(BombController bombController) {
        this.bombController = bombController;
    }

    public boolean checkPos(Position position, Movement movement){
        //System.out.println(position.getX()+" "+position.getY());
        if (movement==Movement.left) {
            return !(tiles.get(position.getY()).get(position.getX()-1).isFilled());
        }
        else if (movement==Movement.right) {
            return !(tiles.get(position.getY()).get(position.getX()+1).isFilled());
        }
        else if (movement==Movement.up) {
            return !(tiles.get(position.getY()-1).get(position.getX()).isFilled());
        }
        else if (movement==Movement.down) {
            return !(tiles.get(position.getY()+1).get(position.getX()).isFilled());
        }
        else if (movement==Movement.stay) {
            return !(tiles.get(position.getY()).get(position.getX()).isFilled());
        }
        return false;
    }



}
