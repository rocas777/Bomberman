package com.noclue.view.bomb;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.BombModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class BombViewTicking implements IView {
    private TextGraphics textGraphics;
    private BombModel model;
    private int counter = 0;

    public BombViewTicking(TextGraphics textGraphics, BombModel bombModel) {
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
        setCounter(getCounter() + 1);
        if (getCounter() % 5 < 2) {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        } else {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        }
        textGraphics.putString(model.getPosition().getRealPosition().getX() + 2
                , model.getPosition().getRealPosition().getY() + 1
                , "  ", BOLD
        );
    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getModel());
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
