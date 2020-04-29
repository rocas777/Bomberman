package com.noclue.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class NoBlock implements Block {
    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getX(),position.getY(),"......",BOLD);
        textGraphics.putString(position.getX(),position.getY()+1,"......",BOLD);
        textGraphics.putString(position.getX(),position.getY()+2,"......",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));

    }

    @Override
    public boolean isFilled() {
        return false;
    }
}
