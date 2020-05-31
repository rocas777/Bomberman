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
    private IBombInterface bombController = null;
    private KeyBoard kServer;
    private Timer tServer;
    private Integer points = 0;
    private ArrayList<Difficulty> difficulties = new ArrayList<>();
    private boolean won = false;
    private int level;
    private Grid tiles;
    private HeroController hero;
    private CopyOnWriteArrayList<MonsterModel> monsters = new CopyOnWriteArrayList<>();

    public FieldModel(int width, int height, int level) {
        this.height = height;
        this.width = width;
        this.setLevel(level);
        setTiles(new Grid());
    }

    public int getLevel() {
        return level;
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
        this.setPoints(this.getPoints() + 1);
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

    public void setTiles(Grid tiles) {
        this.tiles = tiles;
    }

    public CopyOnWriteArrayList<MonsterModel> getMonsters() {
        return monsters;
    }

    public void setMonsters(CopyOnWriteArrayList<MonsterModel> monsters) {
        this.monsters = monsters;
    }

    public IBombInterface getBomb() {
        return getBombController();
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
        this.setBombController(bombController);
    }

    public boolean checkPos(Position position, Movement movement) { //checks if a character can move to a new position. A move can be made if the target psoition isn't filled or isn't active
        if (movement != null)
            switch (movement) {
                case left:
                    return !getTiles().getTile(position.getLeft()).isFilled() || !getTiles().getTile(position.getLeft()).getFiller().isActive();
                case right:
                    return !getTiles().getTile(position.getRight()).isFilled() || !getTiles().getTile(position.getRight()).getFiller().isActive();
                case up:
                    return !getTiles().getTile(position.getUp()).isFilled() || !getTiles().getTile(position.getUp()).getFiller().isActive();
                case down:
                    return !getTiles().getTile(position.getDown()).isFilled() || !getTiles().getTile(position.getDown()).getFiller().isActive();
                case stay:
                    return !getTiles().getTile(position).isFilled() || !getTiles().getTile(position).getFiller().isActive();
            }
        return false;
    }


    public IBombInterface getBombController() {
        return bombController;
    }

    public void setBombController(IBombInterface bombController) {
        this.bombController = bombController;
    }
}
