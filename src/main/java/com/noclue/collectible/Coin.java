package com.noclue.collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class Coin implements Collectible, Drawable {
    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"  00  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1," 0000 ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"  00  ",BOLD);
    }
}
