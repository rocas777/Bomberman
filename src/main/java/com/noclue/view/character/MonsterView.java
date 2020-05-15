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
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"*(OO)*",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"X=VV=X",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"X=VV=X",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    @Override
    public void draw() {
        draw(textGraphics,monsterModel.getPosition());
    }
}
