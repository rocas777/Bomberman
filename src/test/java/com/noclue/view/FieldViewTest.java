package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.noclue.model.FieldModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FieldViewTest {
    FieldView fieldView;
    @Before
    public void setup(){
        FieldModel model = Mockito.mock(FieldModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        fieldView = new FieldView(textGraphics,model);

        when(model.getWidth()).thenReturn(200);
        when(model.getHeight()).thenReturn(200);
    }

    @Test
    public void draw() {

        fieldView.draw(fieldView.getModel(),fieldView.getTextGraphics());

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



    }
}