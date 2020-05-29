package com.noclue.view.field;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.IView;

import java.io.IOException;

public class WinView implements IView {
    private final Screen screen;
    private final int width = 146;
    private final int height = 45;
    private TextGraphics textGraphics;

    public WinView(Screen screen, TextGraphics textGraphics) {
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

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff00ff"));
        textGraphics.putString(50, 22, "YOU HAVE WON THE GAME!!! That was a tough one!");
        textGraphics.putString(63, 23, "Press 'q' to return");

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
