package com.noclue.view.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.Position;
import com.noclue.model.block.IndestructibleBlockModel;
import com.noclue.controller.TimeListener;
import com.noclue.view.IView;

import static com.googlecode.lanterna.SGR.BOLD;

public class IndestructibleBlockView implements IView {
    IndestructibleBlockModel model;
    TextGraphics textGraphics;
    public IndestructibleBlockView(IndestructibleBlockModel model, TextGraphics textGraphics){
        this.model=model;
        this.textGraphics=textGraphics;
    }
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"+----+",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"|XXXX|",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"+----+",BOLD);
    }

    @Override
    public void draw() {
        draw(textGraphics,model.getPosition());
    }
}
