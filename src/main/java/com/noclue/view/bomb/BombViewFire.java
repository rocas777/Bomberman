package com.noclue.view.bomb;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.IView;

import static com.googlecode.lanterna.SGR.BOLD;

public class BombViewFire implements IView {
    TextGraphics textGraphics;
    BombModel model;
    public BombViewFire(TextGraphics textGraphics, BombModel bombModel){
        this.textGraphics = textGraphics;
        this.model = bombModel;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public BombModel getModel() {
        return model;
    }

    public void draw(TextGraphics textGraphics, BombModel model){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));

        for(Position p:model.getExplosionList()) {
            textGraphics.putString(
                    p.getRealPosition().getX()+2
                    , p.getRealPosition().getY()+1
                    , "++", BOLD
            );
        }

    }
    @Override
    public void draw() {
        draw(textGraphics,model);
    }
}
