package com.noclue.keyboard;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


import static org.mockito.Mockito.*;

public class KeyBoardTest {

    KeyBoard keyBoard;
    KeyboardListener keyboardListener;
    ArrayList<KeyboardListener> listeners;
    TerminalScreen screen;

    class MockKeyboardListener implements KeyboardListener{
        CopyOnWriteArrayList<KeyStroke> keyTypes = new CopyOnWriteArrayList<>();
        MockKeyboardListener(){

        }
        @Override
        public void updateOnKeyboard(KeyStroke keyPressed) {
            keyTypes.add(keyPressed);
        }
    }

    @Before
    public void setup(){
        screen = Mockito.mock(TerminalScreen.class);
        keyBoard = spy(new KeyBoard(screen));
        keyboardListener = Mockito.mock(KeyboardListener.class);
        keyBoard.addListener(keyboardListener);
    }

    @Test
    public void addListener() {
        listeners = new ArrayList<>();
        listeners.add(keyboardListener);
        Assert.assertEquals(keyBoard.getKeyboardListeners(),listeners);
    }

    @Test
    public void getKeyboardListeners() {
        listeners = new ArrayList<>();
        listeners.add(keyboardListener);
        Assert.assertEquals(keyBoard.getKeyboardListeners(),listeners);
    }

    @Test
    public void start() {
        KeyStroke keyStroke1 = null;
        KeyStroke keyStroke2 = null;
        try {
            MockKeyboardListener mock = new MockKeyboardListener();
            keyBoard.addListener(mock);
            keyStroke1 = Mockito.mock(KeyStroke.class);
            keyStroke2 = Mockito.mock(KeyStroke.class);
            when(keyStroke1.getKeyType()).thenReturn(KeyType.Character);
            when(keyStroke2.getKeyType()).thenReturn(KeyType.EOF);
            when(screen.readInput()).thenReturn(keyStroke1).thenReturn(keyStroke2);
            keyBoard.start();
            Thread.sleep(40);
            keyBoard.stop();
            Assert.assertEquals(mock.keyTypes.get(0).getKeyType(),KeyType.Character);
            Assert.assertEquals(mock.keyTypes.get(1).getKeyType(),KeyType.EOF);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}