package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.AddTime;

import static com.googlecode.lanterna.SGR.BOLD;

public class AddTimeView implements IView {
    AddTime addTime;
    TextGraphics textGraphics;

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0000ff"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY(),"  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"      ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+2,"  ",BOLD);
    }

    public AddTimeView(AddTime addTime, TextGraphics textGraphics) {
        this.addTime = addTime;
        this.textGraphics = textGraphics;
    }

    @Override
    public void draw() {
        draw(textGraphics,addTime.getPosition());
    }
}
