package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    private class bombMock implements Drawable{
        @Override
        public void draw(TextGraphics textGraphics, Position position) {

        }
    }

    private class tileMock implements Drawable{
        @Override
        public void draw(TextGraphics textGraphics, Position position) {

        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void setkServer() {
    }

    @Test
    public void settServer() {
    }

    @Test
    public void setup() {
    }

    @Test
    public void draw() {

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(width-8, 0), new TerminalSize(width-8, height), ' ');

        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(width-8-6-6, height-3-3), ' ');
        for (int y=0;y<15;y++){
            for (Tile t : tiles.get(y)) {
                t.draw(textGraphics);
            }
        }
        if(bomb!=null)
            bomb.draw(textGraphics);
    }

    @Test
    public void updateOnKeyboard() {
    }

    @Test
    public void updateOnTime() {
    }

    @Test
    public void explode() {
    }
}