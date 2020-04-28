package com.noclue.character;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class Monster implements Character, CanExplode, Drawable {
    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.putString(position.getX(),position.getY(),"*(OO)*",BOLD);
        textGraphics.putString(position.getX(),position.getY()+1,"X=VV=X",BOLD);
        textGraphics.putString(position.getX(),position.getY()+2,"X=VV=X",BOLD);
    }
}
