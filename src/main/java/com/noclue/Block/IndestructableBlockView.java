package com.noclue.Block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;

public class IndestructableBlockView implements BlockView {
    IndestructableBlock indestructableBlock;
    IndestructableBlockView(IndestructableBlock indestructableBlock){
        this.indestructableBlock=indestructableBlock;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setCharacter(position.getTerminalPosition(),'I');
    }
}
