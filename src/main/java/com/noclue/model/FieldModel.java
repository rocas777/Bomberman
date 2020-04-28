package com.noclue.;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.googlecode.lanterna.SGR.BOLD;

public class Field {
    private final int width;
    private final int height;
    ArrayList<Tile> tiles=new ArrayList<>();

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setTiles(ArrayList<Tile> tile){
        this.tiles=tiles;
    }
}
