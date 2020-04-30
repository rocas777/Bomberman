package com.noclue.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Filler;
import com.noclue.Position;

import static com.googlecode.lanterna.SGR.BOLD;


public class IndestructableBlock implements Block, Drawable {
    private Position position;
    public IndestructableBlock(Position position){
       this.position=position;
    }


    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"+----+",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"|XXXX|",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"+----+",BOLD);
    }

    @Override
    public boolean isFilled() {
        return true;
    }
}
