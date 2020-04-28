package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

import static com.googlecode.lanterna.SGR.BOLD;

public class Field {
    private final int width;
    private final int height;

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setLayout() {

    }

    public void draw(TextGraphics textGraphics) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        Random random = new Random();
        int integer = random.nextInt(30);
        textGraphics.putString(integer, integer, "HERO", BOLD);
    }
}
