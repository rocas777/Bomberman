package com.noclue.model.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.model.Position;

import static com.googlecode.lanterna.SGR.BOLD;


public class IndestructibleBlockModel implements Block {
    Position position;
    public IndestructibleBlockModel(Position position){
        this.position=position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isFilled() {
        return true;
    }
}
