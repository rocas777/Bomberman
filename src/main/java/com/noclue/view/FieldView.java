package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.model.FieldModel;

import java.io.IOException;

public class FieldView implements IView{
    private FieldModel model;
    private TextGraphics textGraphics;
    private Screen screen;
    public FieldView(Screen screen,TextGraphics textGraphics, FieldModel model){
        this.model=model;
        this.textGraphics=textGraphics;
        this.screen=screen;
    }

    public FieldModel getModel() {
        return model;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public void setModel(FieldModel model) {
        this.model = model;
    }


    public void draw(FieldModel model, TextGraphics textGraphics, Screen screen){
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(model.getWidth()-8, 0), new TerminalSize(model.getWidth()-8, model.getHeight()), ' ');

        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(model.getWidth()-20, model.getHeight()-6), ' ');



        for(int i1=0;i1<model.getViews().size();i1++){
            for(int i2=0;i2<model.getViews().get(i1).size();i2++) {
                model.getViews().get(i1).get(i2).draw();

            }
        }

        if(model.getBomb()!=null)
            model.getBomb().draw();
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw() {
        draw(model,textGraphics,screen);
    }
}
