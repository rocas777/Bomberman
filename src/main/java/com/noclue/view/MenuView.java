package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.MenuModel;

import java.io.IOException;

import static com.googlecode.lanterna.SGR.BOLD;

public class MenuView implements IView {
    MenuModel menuModel;

    public MenuView(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void draw(TextGraphics textGraphics) {
        MenuModel.getScreen().clear();


        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#c5c5c5"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(146, 45), ' ');


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

        //DRAW TITLE
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        //B
        textGraphics.putString(40, 4, "    ", BOLD);
        textGraphics.putString(40, 5, "  ", BOLD);
        textGraphics.putString(40, 6, "    ", BOLD);
        textGraphics.putString(40, 7, "  ", BOLD);
        textGraphics.putString(40, 8, "    ", BOLD);
        textGraphics.putString(43, 5, "  ", BOLD);
        textGraphics.putString(43, 7, "  ", BOLD);

        //O
        textGraphics.putString(48, 4, "     ", BOLD);
        textGraphics.putString(48, 8, "     ", BOLD);
        textGraphics.putString(48, 5, "  ", BOLD);
        textGraphics.putString(48, 6, "  ", BOLD);
        textGraphics.putString(48, 7, "  ", BOLD);
        textGraphics.putString(51, 5, "  ", BOLD);
        textGraphics.putString(51, 6, "  ", BOLD);
        textGraphics.putString(51, 7, "  ", BOLD);

        //M
        textGraphics.putString(56, 4, " ", BOLD);
        textGraphics.putString(56, 5, " ", BOLD);
        textGraphics.putString(56, 6, " ", BOLD);
        textGraphics.putString(56, 7, " ", BOLD);
        textGraphics.putString(56, 8, " ", BOLD);
        textGraphics.putString(57, 4, " ", BOLD);
        textGraphics.putString(57, 5, " ", BOLD);
        textGraphics.putString(57, 6, " ", BOLD);
        textGraphics.putString(57, 7, " ", BOLD);
        textGraphics.putString(57, 8, " ", BOLD);

        textGraphics.putString(58, 5, " ", BOLD);
        textGraphics.putString(59, 6, " ", BOLD);
        textGraphics.putString(60, 5, " ", BOLD);

        textGraphics.putString(61, 4, " ", BOLD);
        textGraphics.putString(61, 5, " ", BOLD);
        textGraphics.putString(61, 6, " ", BOLD);
        textGraphics.putString(61, 7, " ", BOLD);
        textGraphics.putString(61, 8, " ", BOLD);
        textGraphics.putString(62, 4, " ", BOLD);
        textGraphics.putString(62, 5, " ", BOLD);
        textGraphics.putString(62, 6, " ", BOLD);
        textGraphics.putString(62, 7, " ", BOLD);
        textGraphics.putString(62, 8, " ", BOLD);

        //B
        textGraphics.putString(65, 4, "    ", BOLD);
        textGraphics.putString(65, 5, "  ", BOLD);
        textGraphics.putString(65, 6, "    ", BOLD);
        textGraphics.putString(65, 7, "  ", BOLD);
        textGraphics.putString(65, 8, "    ", BOLD);
        textGraphics.putString(68, 5, "  ", BOLD);
        textGraphics.putString(68, 7, "  ", BOLD);

        //E
        textGraphics.putString(72, 4, "     ", BOLD);
        textGraphics.putString(72, 5, "  ", BOLD);
        textGraphics.putString(72, 6, "     ", BOLD);
        textGraphics.putString(72, 7, "  ", BOLD);
        textGraphics.putString(72, 8, "     ", BOLD);


        //R
        textGraphics.putString(80, 4, "    ", BOLD);
        textGraphics.putString(80, 5, "  ", BOLD);
        textGraphics.putString(80, 6, "    ", BOLD);
        textGraphics.putString(80, 7, "  ", BOLD);
        textGraphics.putString(80, 8, "  ", BOLD);
        textGraphics.putString(83, 5, "  ", BOLD);
        textGraphics.putString(83, 7, " ", BOLD);
        textGraphics.putString(83, 8, "  ", BOLD);

        //M
        textGraphics.putString(87, 4, " ", BOLD);
        textGraphics.putString(87, 5, " ", BOLD);
        textGraphics.putString(87, 6, " ", BOLD);
        textGraphics.putString(87, 7, " ", BOLD);
        textGraphics.putString(87, 8, " ", BOLD);
        textGraphics.putString(88, 4, " ", BOLD);
        textGraphics.putString(88, 5, " ", BOLD);
        textGraphics.putString(88, 6, " ", BOLD);
        textGraphics.putString(88, 7, " ", BOLD);
        textGraphics.putString(88, 8, " ", BOLD);

        textGraphics.putString(89, 5, " ", BOLD);
        textGraphics.putString(90, 6, " ", BOLD);
        textGraphics.putString(91, 5, " ", BOLD);

        textGraphics.putString(92, 4, " ", BOLD);
        textGraphics.putString(92, 5, " ", BOLD);
        textGraphics.putString(92, 6, " ", BOLD);
        textGraphics.putString(92, 7, " ", BOLD);
        textGraphics.putString(92, 8, " ", BOLD);
        textGraphics.putString(93, 4, " ", BOLD);
        textGraphics.putString(93, 5, " ", BOLD);
        textGraphics.putString(93, 6, " ", BOLD);
        textGraphics.putString(93, 7, " ", BOLD);
        textGraphics.putString(93, 8, " ", BOLD);

        //A
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

        //N
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

        //versão fixe
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#c5c5c5"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(95, 11, "Versão Fixe :)", BOLD);


        //DRAW MENU


        if (menuModel.getOption() == 1) {
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            textGraphics.putString(68, 20, "Start Game", BOLD);
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

    public void draw() {
        draw(menuModel.getTextGraphics());
    }
}
