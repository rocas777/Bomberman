package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.AddLife;

import static com.googlecode.lanterna.SGR.BOLD;

public class AddLifeView implements IView {
    AddLife addLife;
    TextGraphics textGraphics;

    public AddLifeView(AddLife addLife, TextGraphics textGraphics) {
        this.addLife = addLife;
        this.textGraphics = textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY(),"  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"      ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+2,"  ",BOLD);
    }

    @Override
    public void draw() {
        draw(textGraphics,addLife.getPosition());
    }
}
