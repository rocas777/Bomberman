package com.noclue.Block;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;


public class IndestructableBlock implements Block {
    private Position position;
    IndestructableBlock(Position position){
       this.position=position;
    }

}
