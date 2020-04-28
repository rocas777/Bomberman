package com.noclue.block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Filler;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;


public class IndestructableBlock implements Block, Drawable {
    private Position position;
    public IndestructableBlock(Position position){
       this.position=position;
    }


    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.putString(position.getX(),position.getY(),"+----+",BOLD);
        textGraphics.putString(position.getX(),position.getY()+1,"|XXXX|",BOLD);
        textGraphics.putString(position.getX(),position.getY()+2,"+----+",BOLD);
    }
}
