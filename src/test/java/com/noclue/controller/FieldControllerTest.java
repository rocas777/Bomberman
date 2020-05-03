package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.noclue.Game;
import com.noclue.IView;
import com.noclue.keyboard.KeyBoard;
import com.noclue.model.FieldModel;
import com.noclue.model.Filler;
import com.noclue.model.Position;
import com.noclue.timer.Timer;
import com.noclue.view.field.FieldView;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.text.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FieldControllerTest {
    FieldController fieldController;
    FieldController fieldSpy;
    @Test
    public void setup() {
        IView view = Mockito.mock(FieldView.class);
        FieldModel fieldModel = new FieldModel(146,45);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Position position = Mockito.mock(Position.class);
        fieldModel.settServer(new Timer(20));
        fieldModel.setkServer(new KeyBoard(screen));
        fieldController = new FieldController(fieldModel,view,view,view,textGraphics);

        fieldSpy = Mockito.spy(fieldController);
        fieldSpy.setup();
        when(fieldSpy.setHeroPos()).thenReturn(position);

        Mockito.verify(fieldSpy,Mockito.times(1))
                .setHeroPos();

        Mockito.verify(fieldSpy,Mockito.times(1))
                .setIndestructibleBlocks();

    }

    @Test
    public void updateOnKeyboard() {

    }

    @Test
    public void updateOnTime() {
    }

    @Test
    public void explode() {

    }

    @Test
    public void testSetup() {
    }

    @Test
    public void checkForHero() {
    }

    @Test
    public void testUpdateOnKeyboard() {
    }

    @Test
    public void testUpdateOnTime() {
    }

    @Test
    public void testExplode() {
    }

    @Test
    public void fireDone() {
    }
}