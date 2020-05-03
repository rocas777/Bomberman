package com.noclue.keyboard;

import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KeyBoardTest {
    KeyBoard keyBoard;
    @Before
    public void setup(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        keyBoard = new KeyBoard(screen);
    }

    @Test
    public void addListener() {
        KeyboardListener keyboardListener = Mockito.mock(KeyboardListener.class);
        keyBoard.addListener(keyboardListener);
        ArrayList<KeyboardListener> listeners= new ArrayList<>();
        listeners.add(keyboardListener);
        Assert.assertEquals(keyBoard.getKeyboardListeners(),listeners);
    }

    @Test
    public void getKeyboardListeners() {
        KeyboardListener keyboardListener = Mockito.mock(KeyboardListener.class);
        keyBoard.addListener(keyboardListener);
        ArrayList<KeyboardListener> listeners= new ArrayList<>();
        listeners.add(keyboardListener);
        Assert.assertEquals(keyBoard.getKeyboardListeners(),listeners);
    }

    @Test
    public void start() {
        keyBoard.start();
    }

    @Test
    public void stop() {
    }
}