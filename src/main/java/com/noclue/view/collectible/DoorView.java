package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.DoorModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class DoorView implements IView {
    DoorModel model;
    TextGraphics textGraphics;
    public DoorView(DoorModel model, TextGraphics textGraphics){
        this.model=model;
        this.textGraphics=textGraphics;
    }
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffFF00"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()," 0000 ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"000000",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"000000",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public DoorModel getModel() {
        return model;
    }

    @Override
    public void draw() {
        draw(textGraphics,model.getPosition());
    }
}
