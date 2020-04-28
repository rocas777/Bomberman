package com.noclue.block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Filler;
import com.noclue.Position;

public class RemovableBlock implements Block, CanExplode, Drawable {
    private Position position;
    public RemovableBlock(Position position){
        this.position=position;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setCharacter(position.getTerminalPosition(),'M');
    }
}
