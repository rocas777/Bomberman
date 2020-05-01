package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.Position;
import com.noclue.model.character.HeroModel;
import com.noclue.TimeListener;

import static com.googlecode.lanterna.SGR.BOLD;

public class HeroView implements TimeListener {
    HeroModel model;
    TextGraphics textGraphics;
    public HeroView(HeroModel model, TextGraphics textGraphics){
        this.model=model;
        this.textGraphics=textGraphics;
    }
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"  ()  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"0=||=0",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"0=||=0",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    @Override
    public void updateOnTime() {
        draw(textGraphics,model.getPosition());
    }
}
