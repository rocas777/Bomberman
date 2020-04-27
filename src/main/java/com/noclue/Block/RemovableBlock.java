package com.noclue.Block;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Position;

public class RemovableBlock implements Block, CanExplode {
    private Position position;
    RemovableBlock(Position position){
        this.position=position;
    }


}
