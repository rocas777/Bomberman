package com.noclue.Collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;

public class DoorView implements Drawable {
    Door door;
    DoorView(Door door){
        this.door=door;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {

    }
}
