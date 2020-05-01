package com.noclue.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;

public class KeyBoard {
    private ArrayList<KeyboardListener> keyboardListeners=new ArrayList<>();
    boolean isOn;
    private TerminalScreen screen;
    public KeyBoard(TerminalScreen screen){
        isOn=false;
        this.screen=screen;
    }

    public void addListener(KeyboardListener keyboardListener){
        keyboardListeners.add(keyboardListener);
    }

    public void start(){
        isOn=true;
        new Thread(() -> {
            while (isOn) {
                KeyStroke key = null;
                try {
                    key = screen.readInput();
                    if (key.getKeyType() == KeyType.Character) {
                        for(KeyboardListener listener:keyboardListeners)
                            listener.updateOnKeyboard(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void stop(){
        isOn=false;
    }
}
