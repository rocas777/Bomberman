package com.noclue.model;

import com.noclue.IBombInterface;
import com.noclue.Movement;
import com.noclue.keyboard.KeyBoard;
import com.noclue.model.character.HeroModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.timer.Timer;
import com.sun.tools.javac.code.Types;

import java.util.concurrent.CopyOnWriteArrayList;

public class FieldModel  {
    private final int width;
    private final int height;
    private Grid tiles;
    private HeroModel hero;
    private CopyOnWriteArrayList<MonsterModel> monsters=new CopyOnWriteArrayList<>();
    IBombInterface bombController =null;
    KeyBoard kServer;
    Timer tServer;
    Integer points=0;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
    public void addPoint() {
        this.points++;
    }

    public void setMonsters(CopyOnWriteArrayList<MonsterModel> monsters) {
        this.monsters = monsters;
    }

    public FieldModel(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new Grid();
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

    public Grid getTiles() {
        return tiles;
    }

    public CopyOnWriteArrayList<MonsterModel> getMonsters() {
        return monsters;
    }

    public IBombInterface getBomb() {
        return bombController;
    }

    public KeyBoard getkServer() {
        return kServer;
    }

    public HeroModel getHero() {
        return hero;
    }

    public Timer gettServer() {
        return tServer;
    }

    public void setHero(HeroModel hero) {
        this.hero = hero;
    }

    public void setBombModel(IBombInterface bombController) {
        this.bombController = bombController;
    }

    public boolean checkPos(Position position, Movement movement){
        //System.out.println(position.getX()+" "+position.getY());
        /*for(CopyOnWriteArrayList<TileController> lt:getTiles().getTiles()) {
            for (TileController t : lt) {
                if(t.isFilled())
                    System.out.print(1+" ");
                else
                    System.out.print(0+" ");
            }
            System.out.println();
        }
        System.out.println();*/
        if (movement==Movement.left) {
            return !(tiles.getTile(position.getLeft()).isFilled());
        }
        else if (movement==Movement.right) {
            return !(tiles.getTile(position.getRight()).isFilled());
        }
        else if (movement==Movement.up) {
            return !(tiles.getTile(position.getUp()).isFilled());
        }
        else if (movement==Movement.down) {
            return !(tiles.getTile(position.getDown()).isFilled());
        }
        else if (movement==Movement.stay) {
            return !(tiles.getTile(position).isFilled());
        }
        return false;
    }



}
