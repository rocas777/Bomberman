package com.noclue.Character;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;

public class MonsterView implements Drawable {
    Monster monster;
    MonsterView(Monster monster){
        this.monster=monster;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {

    }
}
