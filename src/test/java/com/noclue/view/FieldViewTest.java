package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.IView;
import com.noclue.controller.bomb.BombController;
import com.noclue.model.FieldModel;
import com.noclue.view.field.FieldView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Mockito.when;

public class FieldViewTest {
    FieldView fieldView;
    CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews;
    FieldView fmv;
    FieldModel model;
    TextGraphics textGraphics;
    Screen screen;
    @Before
    public void setup(){
        model = Mockito.mock(FieldModel.class);
        BombController bombModel = Mockito.mock(BombController.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        screen = Mockito.mock(Screen.class);
        fieldView = new FieldView(screen,textGraphics,model);
        when(model.getBomb()).thenReturn(bombModel);
        when(model.getViews()).thenReturn(iViews);

        when(model.getWidth()).thenReturn(200);
        when(model.getHeight()).thenReturn(200);
        NoView noView = Mockito.mock(NoView.class);
        iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(noView);
        fmv = Mockito.spy(fieldView);

    }

    @Test
    public void draw() {

        fieldView.draw(fieldView.getModel(),fieldView.getTextGraphics(),fieldView.getScreen(),iViews);


        try {
            Mockito.verify(fieldView.getScreen(),Mockito.times(1))
                    .refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mockito.verify(fieldView.getScreen(),Mockito.times(1))
                .clear();

        Mockito.verify(fieldView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(fieldView.getTextGraphics(),Mockito.times(1))
                .fillRectangle(new TerminalPosition(0, 0), new TerminalSize(fieldView.getModel().getWidth(), fieldView.getModel().getHeight()), ' ');
        Mockito.verify(fieldView.getTextGraphics(),Mockito.times(1))
                .fillRectangle(new TerminalPosition(fieldView.getModel().getWidth()-8, 0), new TerminalSize(fieldView.getModel().getWidth()-8, fieldView.getModel().getHeight()), ' ');

        Mockito.verify(fieldView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ffffff"));

        Mockito.verify(fieldView.getTextGraphics(),Mockito.times(1))
                .fillRectangle(new TerminalPosition(6, 3), new TerminalSize(fieldView.getModel().getWidth()-20, fieldView.getModel().getHeight()-6), ' ');

        Mockito.verify(iViews.get(0).get(0),Mockito.times(1))
                .draw();

        Mockito.verify(fieldView.getModel().getBomb(),Mockito.times(1))
                .draw();


    }

    @Test
    public void drawI() {



    }

    @Test
    public void setTextGraphics(){
        TextGraphics tmp = Mockito.mock(TextGraphics.class);
        Assert.assertEquals(textGraphics,fieldView.getTextGraphics());
        fieldView.setTextGraphics(tmp);
        Assert.assertEquals(tmp,fieldView.getTextGraphics());
    }

    @Test
    public void setModel(){
        FieldModel tmp = Mockito.mock(FieldModel.class);
        Assert.assertEquals(model,fieldView.getModel());
        fieldView.setModel(tmp);
        Assert.assertEquals(tmp,fieldView.getModel());

    }
}