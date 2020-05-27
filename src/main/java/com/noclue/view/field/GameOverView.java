package com.noclue.view.field;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.IView;

import java.io.IOException;

public class GameOverView implements IView {
    private TextGraphics textGraphics;
    private Screen screen;
    private int width = 146;
    private int height = 45;

    public GameOverView(Screen screen, TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
        this.screen = screen;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void draw() {

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(getWidth() / 2 - 34, getHeight() / 2 - 12), new TerminalSize(68, 24), ' ');

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(getWidth() / 2 - 30, getHeight() / 2 - 10), new TerminalSize(60, 20), ' ');

        //textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(getWidth()-20, getHeight()-6), ' ');

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff00ff"));
        textGraphics.putString(46, 22, "YOU HAVE LOST!!! I didn't know that was even possible!");

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
