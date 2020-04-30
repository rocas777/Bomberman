package com.noclue.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.CanExplode;
import com.noclue.Drawable;
import com.noclue.Movement;
import com.noclue.Position;
import com.noclue.difficulty.Difficulty;

import java.util.ArrayList;

import static com.googlecode.lanterna.SGR.BOLD;

public class Monster implements Character, CanExplode, Drawable {
    private Difficulty difficulty;
    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"*(OO)*",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"X=VV=X",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"X=VV=X",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    @Override
    public boolean isFilled() {
        return true;
    }

    public Monster(Difficulty difficulty){
        this.difficulty=difficulty;
    }

    public ArrayList<Movement> nextMove(Position position){
        return difficulty.nextMove(position);
    }
}
