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

    public DoorView(DoorModel model, TextGraphics textGraphics) {
        this.model = model;
        this.textGraphics = textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#964b00"));
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY(), "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 1, "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 2, "      ", BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#422600"));
        textGraphics.putString(position.getRealPosition().getX() + 1, position.getRealPosition().getY() + 1, "    ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 1, position.getRealPosition().getY() + 2, "    ", BOLD);


        /*
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#964b00"));
        textGraphics.putString(position.getRealPosition().getX()+1,position.getRealPosition().getY(),"    ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"      ",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"      ",BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#422600"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+1,"  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+2,"  ",BOLD);
        */
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public DoorModel getModel() {
        return model;
    }

    @Override
    public void draw() {
        draw(textGraphics, model.getPosition());
    }
}
