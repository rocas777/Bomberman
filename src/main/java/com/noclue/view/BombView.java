package com.noclue.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.model.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class BombView {
    TextGraphics textGraphics;
    BombModel model;
    public BombView(TextGraphics textGraphics, BombModel bombModel){
        this.textGraphics = textGraphics;
        this.model = bombModel;
    }
    public void draw(){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        textGraphics.putString(model.getPosition().getRealPosition().getX()+2
                ,model.getPosition().getRealPosition().getY()+1
                ,"++",BOLD
        );
    }
}
