package com.noclue.view.collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.Position;
import com.noclue.controller.TimeListener;
import com.noclue.model.collectible.CoinModel;
import com.noclue.view.IView;

import static com.googlecode.lanterna.SGR.BOLD;

public class CoinView implements IView {
    CoinModel model;
    TextGraphics textGraphics;
    public CoinView(CoinModel model, TextGraphics textGraphics){
        this.model=model;
        this.textGraphics=textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"  00  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1," 0000 ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"  00  ",BOLD);
    }

    @Override
    public void draw() {
        draw(textGraphics,model.getPosition());
    }
}
