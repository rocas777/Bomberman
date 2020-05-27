package com.noclue.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.model.difficulty.Difficulty;

import java.util.ArrayList;

public class MenuModel {
    static Screen screen;
    int option;
    int subOption;
    boolean onSubMenu;
    int score = 0;
    TextGraphics textGraphics;
    ArrayList<ArrayList<Difficulty>> difficultiesA;
    String levels;
    int level;
    public MenuModel(int option, int subOption) {
        this.option = option;
        this.subOption = subOption;
        onSubMenu = false;

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
        if (option != 1) {
            option--;
        } else {
            option = 4;
        }
    }

    public void optDown() {
        if (option != 4) {
            option++;
        } else {
            option = 1;
        }
    }

    public void subOptUp() {
        if (subOption != 1) {
            subOption--;
        } else {
            subOption = 4;
        }
    }

    public void subOptDown() {
        if (subOption != 4) {
            subOption++;
        } else {
            subOption = 1;
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
        return onSubMenu;
    }

    public void setOnSubMenu(boolean onSubMenu) {
        this.onSubMenu = onSubMenu;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
