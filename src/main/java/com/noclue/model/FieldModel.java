package com.noclue.model;

import com.noclue.*;
import com.noclue.KeyBoard;
import com.noclue.Timer;

import java.util.ArrayList;

public class FieldModel  {
    private final int width;
    private final int height;
    final private ArrayList<ArrayList<Tile>> tiles;
    private Position hero_pos;
    private ArrayList<Position> monsters=new ArrayList<>();
    BombModel bombModel =null;
    KeyBoard kServer;
    Timer tServer;

    public FieldModel(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new ArrayList<>();
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

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public ArrayList<Position> getMonsters() {
        return monsters;
    }

    public BombModel getBombModel() {
        return bombModel;
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

    public void setBombModel(BombModel bombModel) {
        this.bombModel = bombModel;
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
        return false;
    }



}
