package com.noclue.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.controller.FieldController;
import com.noclue.controller.FieldControllerTest;
import com.noclue.controller.Timer;
import com.noclue.model.BombModel;
import com.noclue.model.FieldModel;
import com.noclue.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BombViewTest {
    BombView bombView;

    @Before
    public void setup(){
        BombModel model = Mockito.mock(BombModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);

        when(model.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);
        bombView = new BombView(textGraphics,model);

    }

    @Test
    public void draw() {
        bombView.draw(bombView.getTextGraphics(),bombView.getModel());

        Mockito.verify(bombView.getTextGraphics(),Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(bombView.getTextGraphics(),Mockito.times(1))
                .putString(bombView.getModel().getPosition().getRealPosition().getX()+2
                        ,bombView.getModel().getPosition().getRealPosition().getY()+1
                        ,"++",BOLD
                );

    }
}