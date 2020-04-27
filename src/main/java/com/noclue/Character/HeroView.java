package com.noclue.Character;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;

public class HeroView implements Drawable {
    Hero hero;
    HeroView(Hero hero){
        this.hero=hero;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {

    }
}
