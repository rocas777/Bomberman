package com.noclue.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.model.difficulty.Difficulty;

import java.util.ArrayList;

public class MenuModel {
    private static Screen screen;
    private int option;
    private int subOption;
    private boolean onSubMenu;
    private int score = 0;
    private TextGraphics textGraphics;
    private ArrayList<ArrayList<Difficulty>> difficultiesA;
    private String levels;
    private int level;

    public MenuModel(int option, int subOption) {
        this.setOption(option);
        this.setSubOption(subOption);
        setOnSubMenu(false);

    }

    public static Screen getScreen() {
        return screen;
    }

    public static void setScreen(Screen screen) {
        MenuModel.screen = screen;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public ArrayList<ArrayList<Difficulty>> getDifficultiesA() {
        return difficultiesA;
    }

    public void setDifficultiesA(ArrayList<ArrayList<Difficulty>> difficultiesA) {
        this.difficultiesA = difficultiesA;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void optUp() {
        if (getOption() != 1) {
            setOption(getOption() - 1);
        } else {
            setOption(4);
        }
    }

    public void optDown() {
        if (getOption() != 4) {
            setOption(getOption() + 1);
        } else {
            setOption(1);
        }
    }

    public void subOptUp() {
        if (getSubOption() != 1) {
            setSubOption(getSubOption() - 1);
        } else {
            setSubOption(3);
        }
    }

    public void subOptDown() {
        if (getSubOption() != 3) {
            setSubOption(getSubOption() + 1);
        } else {
            setSubOption(1);
        }
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public int getSubOption() {
        return subOption;
    }

    public void setSubOption(int subOption) {
        this.subOption = subOption;
    }

    public boolean getOnSubMenu() {
        return isOnSubMenu();
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public boolean isOnSubMenu() {
        return onSubMenu;
    }

    public void setOnSubMenu(boolean onSubMenu) {
        this.onSubMenu = onSubMenu;
    }
}
