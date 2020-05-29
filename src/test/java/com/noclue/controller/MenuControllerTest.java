package com.noclue.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.model.MenuModel;
import com.noclue.model.Position;
import com.noclue.model.character.HeroModel;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.model.difficulty.Hard;
import com.noclue.view.MenuView;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuControllerTest {

    @Test
    public void killProgram() {
    }

    @Test
    public void run() {
        /*MenuModel menuModel = mock(MenuModel.class);
        MenuController menuController = new MenuController(menuModel,mock(MenuView.class));
        Screen screen = mock(Screen.class);
        MenuModel.setScreen(screen);
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(screen.readInput()).thenReturn(keyStroke);
        when(menuModel.getOnSubMenu()).thenReturn(false);
        when(keyStroke.getCharacter()).thenReturn('w');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        when(menuModel.getLevel()).thenReturn(1);
        when(menuModel.getLevels()).thenReturn("");

        menuController.run();

        verify(menuModel,times(1)).optUp();*/


    }

    @Test
    public void chooseOption() {
    }

    @Test
    public void startNewGame() {
    }

    @Test
    public void updateOnKeyboard() {
    }
}