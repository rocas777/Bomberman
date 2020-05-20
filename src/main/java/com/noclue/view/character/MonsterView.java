package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.character.MonsterModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class MonsterView implements IView {
    TextGraphics textGraphics;
    MonsterModel monsterModel;
    public MonsterView(MonsterModel monsterModel,TextGraphics textGraphics){
        this.monsterModel=monsterModel;
        this.textGraphics=textGraphics;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public MonsterModel getMonsterModel() {
        return monsterModel;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        /*
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+5,position.getRealPosition().getY()+1," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY(),"  ",BOLD);


        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getRealPosition().getX()+1,position.getRealPosition().getY()+1,"    ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+1,position.getRealPosition().getY()+2," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+4,position.getRealPosition().getY()+2," ",BOLD);
         */

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+1,"  ",BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+5,position.getRealPosition().getY()," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+1,position.getRealPosition().getY()+1," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+4,position.getRealPosition().getY()+1," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+5,position.getRealPosition().getY()+2," ",BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY(),"  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+2,"  ",BOLD);

    }

    @Override
    public void draw() {
        draw(textGraphics,monsterModel.getPosition());
    }
}
