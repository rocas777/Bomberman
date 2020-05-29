package com.noclue.model;

import com.noclue.IBombInterface;
import com.noclue.Movement;
import com.noclue.controller.HeroController;
import com.noclue.keyboard.KeyBoard;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.timer.Timer;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class FieldModel {
    private final int width;
    private final int height;
    private Grid tiles;
    IBombInterface bombController = null;
    KeyBoard kServer;
    Timer tServer;
    Integer points = 0;
    ArrayList<Difficulty> difficulties = new ArrayList<>();
    boolean won = false;
    int level;
    private HeroController hero;
    private CopyOnWriteArrayList<MonsterModel> monsters = new CopyOnWriteArrayList<>();

    public FieldModel(int width, int height, int level) {
        this.height = height;
        this.width = width;
        this.level = level;
        tiles = new Grid();
    }

    public int getLevel() {
        return level;
    }

    public void setTiles(Grid tiles){
        this.tiles = tiles;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public ArrayList<Difficulty> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(ArrayList<Difficulty> difficulties) {
        this.difficulties = difficulties;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void addPoint() {
        this.points++;
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

    public void setMonsters(CopyOnWriteArrayList<MonsterModel> monsters) {
        this.monsters = monsters;
    }

    public IBombInterface getBomb() {
        return bombController;
    }

    public KeyBoard getkServer() {
        return kServer;
    }

    public void setkServer(KeyBoard kServer) {
        this.kServer = kServer;
    }

    public HeroController getHero() {
        return hero;
    }

    public void setHero(HeroController hero) {
        this.hero = hero;
    }

    public Timer gettServer() {
        return tServer;
    }

    public void settServer(Timer tServer) {
        this.tServer = tServer;
    }

    public void setBombModel(IBombInterface bombController) {
        this.bombController = bombController;
    }

    public boolean checkPos(Position position, Movement movement) {
        if(movement!=null)
            switch (movement) {
                case left:
                    return !tiles.getTile(position.getLeft()).isFilled() || !tiles.getTile(position.getLeft()).getFiller().isActive();
                case right:
                    return !tiles.getTile(position.getRight()).isFilled() || !tiles.getTile(position.getRight()).getFiller().isActive();
                case up:
                    return !tiles.getTile(position.getUp()).isFilled() || !tiles.getTile(position.getUp()).getFiller().isActive();
                case down:
                    return !tiles.getTile(position.getDown()).isFilled() || !tiles.getTile(position.getDown()).getFiller().isActive();
                case stay:
                    return !tiles.getTile(position).isFilled() || !tiles.getTile(position).getFiller().isActive();
            }
        return false;
    }


}
