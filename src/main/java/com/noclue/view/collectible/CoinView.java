package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.CoinModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class CoinView implements IView {
    CoinModel model;
    TextGraphics textGraphics;
    public CoinView(CoinModel model, TextGraphics textGraphics){
        this.model=model;
        this.textGraphics=textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffff00"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY(),"  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"      ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+2,"  ",BOLD);
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public CoinModel getModel() {
        return model;
    }

    @Override
    public void draw() {
        draw(textGraphics,model.getPosition());
    }
}
