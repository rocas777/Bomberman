package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.FieldModel;
import com.noclue.model.Tile;

public class FieldView {
    FieldModel model;
    TextGraphics textGraphics;
    public FieldView(TextGraphics textGraphics, FieldModel model){
        this.model=model;
        this.textGraphics=textGraphics;
    }

    public void draw(){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(model.getWidth()-8, 0), new TerminalSize(model.getWidth()-8, model.getHeight()), ' ');

        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(model.getWidth()-20, model.getHeight()-6), ' ');
        //System.out.println("okkpoiuy!");
    }
}
