package com.noclue.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.MenuModel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.googlecode.lanterna.SGR.BOLD;

public class MenuView implements IView {
    MenuModel menuModel;

    public MenuView(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void draw_B(int c,int r,TextGraphics textGraphics){
        textGraphics.putString(c, r, "    ", BOLD);
        textGraphics.putString(c, r+1, "  ", BOLD);
        textGraphics.putString(c, r+2, "    ", BOLD);
        textGraphics.putString(c, r+3, "  ", BOLD);
        textGraphics.putString(c, r+4, "    ", BOLD);
        textGraphics.putString(c+3, r+1, "  ", BOLD);
        textGraphics.putString(c+3, r+3, "  ", BOLD);
    }

    public void draw_O(TextGraphics textGraphics){
        textGraphics.putString(48, 4, "     ", BOLD);
        textGraphics.putString(48, 8, "     ", BOLD);
        textGraphics.putString(48, 5, "  ", BOLD);
        textGraphics.putString(48, 6, "  ", BOLD);
        textGraphics.putString(48, 7, "  ", BOLD);
        textGraphics.putString(51, 5, "  ", BOLD);
        textGraphics.putString(51, 6, "  ", BOLD);
        textGraphics.putString(51, 7, "  ", BOLD);
    }

    public void draw_M(int c,int r,TextGraphics textGraphics){
        textGraphics.putString(c, r, " ", BOLD);
        textGraphics.putString(c, r+1, " ", BOLD);
        textGraphics.putString(c, r+2, " ", BOLD);
        textGraphics.putString(c, r+3, " ", BOLD);
        textGraphics.putString(c, r+4, " ", BOLD);
        textGraphics.putString(c+1, r, " ", BOLD);
        textGraphics.putString(c+1, r+1, " ", BOLD);
        textGraphics.putString(c+1, r+2, " ", BOLD);
        textGraphics.putString(c+1, r+3, " ", BOLD);
        textGraphics.putString(c+1, r+4, " ", BOLD);

        textGraphics.putString(c+2, r+1, " ", BOLD);
        textGraphics.putString(c+3, r+2, " ", BOLD);
        textGraphics.putString(c+4, r+1, " ", BOLD);

        textGraphics.putString(c+5, r, " ", BOLD);
        textGraphics.putString(c+5, r+1, " ", BOLD);
        textGraphics.putString(c+5, r+2, " ", BOLD);
        textGraphics.putString(c+5, r+3, " ", BOLD);
        textGraphics.putString(c+5, r+4, " ", BOLD);
        textGraphics.putString(c+6, r, " ", BOLD);
        textGraphics.putString(c+6, r+1, " ", BOLD);
        textGraphics.putString(c+6, r+2, " ", BOLD);
        textGraphics.putString(c+6, r+3, " ", BOLD);
        textGraphics.putString(c+6, r+4, " ", BOLD);
    }

    public void draw_E(TextGraphics textGraphics){
        textGraphics.putString(72, 4, "     ", BOLD);
        textGraphics.putString(72, 5, "  ", BOLD);
        textGraphics.putString(72, 6, "     ", BOLD);
        textGraphics.putString(72, 7, "  ", BOLD);
        textGraphics.putString(72, 8, "     ", BOLD);
    }

    public void draw_R(TextGraphics textGraphics){
        textGraphics.putString(80, 4, "    ", BOLD);
        textGraphics.putString(80, 5, "  ", BOLD);
        textGraphics.putString(80, 6, "    ", BOLD);
        textGraphics.putString(80, 7, "  ", BOLD);
        textGraphics.putString(80, 8, "  ", BOLD);
        textGraphics.putString(83, 5, "  ", BOLD);
        textGraphics.putString(83, 7, " ", BOLD);
        textGraphics.putString(83, 8, "  ", BOLD);
    }

    public void draw_A(TextGraphics textGraphics){
        textGraphics.putString(97, 6, " ", BOLD);
        textGraphics.putString(97, 7, " ", BOLD);
        textGraphics.putString(97, 8, " ", BOLD);
        textGraphics.putString(98, 6, " ", BOLD);
        textGraphics.putString(98, 7, " ", BOLD);
        textGraphics.putString(98, 8, " ", BOLD);

        textGraphics.putString(98, 5, " ", BOLD);

        textGraphics.putString(99, 5, " ", BOLD);
        textGraphics.putString(99, 4, " ", BOLD);
        textGraphics.putString(100, 4, " ", BOLD);
        textGraphics.putString(101, 4, " ", BOLD);
        textGraphics.putString(101, 5, " ", BOLD);

        textGraphics.putString(99, 7, " ", BOLD);
        textGraphics.putString(100, 7, " ", BOLD);
        textGraphics.putString(101, 7, " ", BOLD);

        textGraphics.putString(102, 5, " ", BOLD);

        textGraphics.putString(102, 6, " ", BOLD);
        textGraphics.putString(102, 7, " ", BOLD);
        textGraphics.putString(102, 8, " ", BOLD);
        textGraphics.putString(103, 6, " ", BOLD);
        textGraphics.putString(103, 7, " ", BOLD);
        textGraphics.putString(103, 8, " ", BOLD);
    }

    public  void draw_N(TextGraphics textGraphics){
        textGraphics.putString(107, 4, " ", BOLD);
        textGraphics.putString(107, 5, " ", BOLD);
        textGraphics.putString(107, 6, " ", BOLD);
        textGraphics.putString(107, 7, " ", BOLD);
        textGraphics.putString(107, 8, " ", BOLD);
        textGraphics.putString(108, 4, " ", BOLD);
        textGraphics.putString(108, 5, " ", BOLD);
        textGraphics.putString(108, 6, " ", BOLD);
        textGraphics.putString(108, 7, " ", BOLD);
        textGraphics.putString(108, 8, " ", BOLD);

        textGraphics.putString(109, 5, " ", BOLD);
        textGraphics.putString(110, 6, " ", BOLD);
        textGraphics.putString(111, 7, " ", BOLD);

        textGraphics.putString(111, 4, " ", BOLD);
        textGraphics.putString(111, 5, " ", BOLD);
        textGraphics.putString(111, 6, " ", BOLD);
        textGraphics.putString(111, 7, " ", BOLD);
        textGraphics.putString(111, 8, " ", BOLD);
        textGraphics.putString(112, 4, " ", BOLD);
        textGraphics.putString(112, 5, " ", BOLD);
        textGraphics.putString(112, 6, " ", BOLD);
        textGraphics.putString(112, 7, " ", BOLD);
        textGraphics.putString(112, 8, " ", BOLD);
    }

    public void draw(TextGraphics textGraphics) {
        MenuModel.getScreen().clear();


        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#c5c5c5"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(146, 45), ' ');

        drawHero(textGraphics);

        //DRAW TITLE
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));

        draw_B(40,4,textGraphics);
        draw_O(textGraphics);
        draw_M(56,4,textGraphics);
        draw_B(65,4,textGraphics);
        draw_E(textGraphics);
        draw_R(textGraphics);
        draw_M(87,4,textGraphics);
        draw_A(textGraphics);
        draw_N(textGraphics);

        //versão fixe
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#c5c5c5"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(95, 11, "Versão Fixe :)", BOLD);


        //DRAW MENU
        if (menuModel.getOption() == 1) {
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            textGraphics.putString(68, 20, "Start Game",BOLD);
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        } else {
            textGraphics.putString(68, 20, "Start Game");
        }
        if (menuModel.getOption() == 2) {
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            textGraphics.putString(65, 22, "Choose Difficulty", BOLD);
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            if (menuModel.getOnSubMenu()) {
                if (menuModel.getSubOption() == 1) {
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
                    textGraphics.putString(90, 20, "Easy", BOLD);
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
                } else {
                    textGraphics.putString(90, 20, "Easy");
                }
                if (menuModel.getSubOption() == 2) {
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
                    textGraphics.putString(90, 22, "Medium", BOLD);
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
                } else {
                    textGraphics.putString(90, 22, "Medium");
                }
                if (menuModel.getSubOption() == 3) {
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
                    textGraphics.putString(90, 24, "Hard", BOLD);
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
                } else {
                    textGraphics.putString(90, 24, "Hard");
                }
            }
        } else {
            textGraphics.putString(65, 22, "Choose Difficulty");
        }
        if (menuModel.getOption() == 3) {
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            textGraphics.putString(71, 24, "Exit", BOLD);
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        } else {
            textGraphics.putString(71, 24, "Exit");
        }
        if (menuModel.getOption() == 4) {
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            if (menuModel.getLevel() == 1)
                textGraphics.putString(66, 18, "Start Campaign", BOLD);
            else
                textGraphics.putString(65, 18, "Continue Campaign", BOLD);
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        } else {
            if (menuModel.getLevel() == 1)
                textGraphics.putString(66, 18, "Start Campaign");
            else
                textGraphics.putString(65, 18, "Continue Campaign");
        }
        try {
            MenuModel.getScreen().refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawHero(TextGraphics textGraphics){
        //DRAW BIG HERO
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
        //HEAD
        textGraphics.putString(20, 14, "      ", BOLD);
        textGraphics.putString(20, 15, "      ", BOLD);
        textGraphics.putString(20, 16, "      ", BOLD);
        //LEFT ARM
        textGraphics.putString(17, 17, "   ", BOLD);
        textGraphics.putString(17, 18, "   ", BOLD);
        textGraphics.putString(17, 19, "   ", BOLD);
        //RIGHT ARM
        textGraphics.putString(26, 17, "   ", BOLD);
        textGraphics.putString(26, 18, "   ", BOLD);
        textGraphics.putString(26, 19, "   ", BOLD);
        //SHIRT
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#3B5998"));
        textGraphics.putString(20, 17, "      ", BOLD);
        textGraphics.putString(20, 18, "      ", BOLD);
        textGraphics.putString(20, 19, "      ", BOLD);
        //LEGS
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(20, 20, "      ", BOLD);
        textGraphics.putString(20, 21, "      ", BOLD);
        textGraphics.putString(20, 22, "      ", BOLD);
    }

    public void draw() {
        draw(menuModel.getTextGraphics());
    }
}
