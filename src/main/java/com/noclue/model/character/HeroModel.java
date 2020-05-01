package com.noclue.model.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.model.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class HeroModel implements Character, CanExplode {
    Position position;
    public HeroModel(Position position){
        this.position=position;
    }

    @Override
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
