package com.noclue.model.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.model.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class DoorModel implements Collectible {
    Position position;
    public DoorModel(Position position){
        this.position=position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
