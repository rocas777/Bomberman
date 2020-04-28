package com.noclue.block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Filler;
import com.noclue.Position;


public class IndestructableBlock implements Block, Drawable {
    private Position position;
    public IndestructableBlock(Position position){
       this.position=position;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setCharacter(position.getTerminalPosition(),'I');
    }
}
