package com.noclue.view.bomb;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.BombModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class BombViewTicking implements IView {
    TextGraphics textGraphics;
    BombModel model;
    int counter = 0;

    public BombViewTicking(TextGraphics textGraphics, BombModel bombModel) {
        this.textGraphics = textGraphics;
        this.model = bombModel;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public BombModel getModel() {
        return model;
    }

    public void draw(TextGraphics textGraphics, BombModel model) {
        counter++;
        if (counter % 5 < 2) {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
            textGraphics.putString(model.getPosition().getRealPosition().getX() + 2
                    , model.getPosition().getRealPosition().getY() + 1
                    , "  ", BOLD
            );
        } else {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            textGraphics.putString(model.getPosition().getRealPosition().getX() + 2
                    , model.getPosition().getRealPosition().getY() + 1
                    , "  ", BOLD
            );
        }
    }


    @Override
    public void draw() {
        draw(textGraphics, model);
    }
}
