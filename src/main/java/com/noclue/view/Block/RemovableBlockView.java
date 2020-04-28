package com.noclue.Block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;
import org.w3c.dom.css.Rect;

public class RemovableBlockView implements BlockView {
    RemovableBlock removableBlock;
    RemovableBlockView(RemovableBlock removableBlock){
        this.removableBlock=removableBlock;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setCharacter(position.getTerminalPosition(),'M');
    }
}
