package com.noclue.view.field;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.IView;
import com.noclue.model.FieldModel;
import com.noclue.model.Grid;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.view.LivesView;
import com.noclue.view.TimeLeftView;

import java.io.IOException;

import static com.googlecode.lanterna.SGR.BOLD;

public class FieldView implements IView {
    private FieldModel model;
    private TextGraphics textGraphics;
    private Screen screen;
    private TimeLeftView timeLeftView;

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

    public Screen getScreen() {
        return screen;
    }

    public void setTimeLeftView(TimeLeftView timeLeftView) {
        this.timeLeftView = timeLeftView;
    }

    public void drawScore(TextGraphics textGraphics, Position position){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getX(),position.getY()," Score: ",BOLD);
        textGraphics.putString(position.getX(),position.getY()+1,"   "+Integer.toString(model.getPoints()),BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
    }

    public void draw(FieldModel model, TextGraphics textGraphics, Screen screen, Grid views){
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(model.getWidth()-8, 0), new TerminalSize(model.getWidth()-8, model.getHeight()), ' ');

        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(model.getWidth()-20, model.getHeight()-6), ' ');


        if(views!= null && views.getTiles()!=null) {
            for (int i1 = 0; i1 < views.getTiles().size(); i1++) {
                for (int i2 = 0; i2 < views.getTiles().get(i1).size(); i2++) {
                    views.getTiles().get(i1).get(i2).draw();
                }
            }
            drawScore(textGraphics,new Position(146,45,138,35));
        }

        if(timeLeftView!=null){
            timeLeftView.draw();
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
        draw(model,textGraphics,screen,model.getTiles());
    }
}
