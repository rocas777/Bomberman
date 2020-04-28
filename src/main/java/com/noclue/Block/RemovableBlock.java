package com.noclue.Block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Position;

public class RemovableBlock implements Block, CanExplode, Drawable {
    private Position position;
    RemovableBlock(Position position){
        this.position=position;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setCharacter(position.getTerminalPosition(),'M');
    }
}
