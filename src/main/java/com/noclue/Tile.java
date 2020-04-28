package com.noclue;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.collectible.Collectible;

public class Tile {
    Filler filler;
    Collectible collectible;
    Position position;

    Tile(Position position, Collectible collectible, Filler filler){
        this.collectible=collectible;
        this.filler=filler;
        this.position=position;
    }

    public void draw(TextGraphics textGraphics){
        collectible.draw(textGraphics,position);
        filler.draw(textGraphics,position);
    }
}
