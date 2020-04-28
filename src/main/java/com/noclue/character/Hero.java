package com.noclue.character;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class Hero implements Character, CanExplode, Drawable {
    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.putString(position.getX(),position.getY(),"  ()  ",BOLD);
        textGraphics.putString(position.getX(),position.getY()+1,"0=||=0",BOLD);
        textGraphics.putString(position.getX(),position.getY()+2,"0=||=0",BOLD);
    }
}
