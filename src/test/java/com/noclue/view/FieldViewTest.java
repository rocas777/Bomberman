package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.noclue.controller.BombController;
import com.noclue.model.BombModel;
import com.noclue.model.FieldModel;
import com.noclue.view.character.MonsterView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FieldViewTest {
    FieldView fieldView;
    CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews;
    @Before
    public void setup(){
        FieldModel model = Mockito.mock(FieldModel.class);
        BombController bombModel = Mockito.mock(BombController.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Screen screen = Mockito.mock(Screen.class);
        fieldView = new FieldView(screen,textGraphics,model);
        when(model.getBomb()).thenReturn(bombModel);

        when(model.getWidth()).thenReturn(200);
        when(model.getHeight()).thenReturn(200);
        NoView noView = Mockito.mock(NoView.class);
        iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(noView);
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
}