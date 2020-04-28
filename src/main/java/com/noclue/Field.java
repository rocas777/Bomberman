package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
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
    public void draw(TextGraphics textGraphics) throws IOException {
        Random random = new Random();
        int integer = random.nextInt(60);
        textGraphics.putString(integer,integer,"HERO",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(width-8, 0), new TerminalSize(width-8, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(width-8-6-6, height-3-3), ' ');
    }
}