package com.noclue;

import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    public void run() {
    }

    @Test
    public void updateOnKeyboard() {
        /*
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        com.googlecode.lanterna.input.KeyStroke keyStroke = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);

        when(keyStroke.getCharacter()).thenReturn('q');
        when(keyStroke.getKeyType()).thenReturn(KeyType.EOF);
        Game game =new Game(40,20);
        game.updateOnKeyboard(keyStroke);
        game.setScreen(screen);
        try {
            verify(screen,times(1))
                    .close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}