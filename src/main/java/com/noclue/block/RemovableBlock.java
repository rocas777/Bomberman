package com.noclue.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Filler;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class RemovableBlock implements Block, CanExplode, Drawable {
    private Position position;
    public RemovableBlock(Position position){
        this.position=position;
    }


    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getX(),position.getY(),"######",BOLD);
        textGraphics.putString(position.getX(),position.getY()+1,"######",BOLD);
        textGraphics.putString(position.getX(),position.getY()+2,"######",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }
}
