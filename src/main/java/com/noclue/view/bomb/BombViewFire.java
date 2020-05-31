package com.noclue.view.bomb;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.BombModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class BombViewFire implements IView {
    private TextGraphics textGraphics;
    private BombModel model;

    public BombViewFire(TextGraphics textGraphics, BombModel bombModel) {
        this.setTextGraphics(textGraphics);
        this.setModel(bombModel);
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public BombModel getModel() {
        return model;
    }

    public void setModel(BombModel model) {
        this.model = model;
    }

    public void draw(TextGraphics textGraphics, BombModel model) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));

        for (int i = 0; i < model.getExplosionList().size(); i++) {
            textGraphics.putString(
                    model.getExplosionList().get(i).getRealPosition().getX() + 2
                    , model.getExplosionList().get(i).getRealPosition().getY() + 1
                    , "  ", BOLD
            );
        }

    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getModel());
    }
}
