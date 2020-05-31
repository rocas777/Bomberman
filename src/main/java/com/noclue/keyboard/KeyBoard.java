package com.noclue.keyboard;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;

public class KeyBoard {
    private final TerminalScreen screen;
    Boolean isOn;
    private ArrayList<KeyboardListener> keyboardListeners = new ArrayList<>();
    Thread thread;

    public KeyBoard(TerminalScreen screen) {
        isOn = false;
        this.screen = screen;
    }

    public void addListener(KeyboardListener keyboardListener) {
        keyboardListeners.add(keyboardListener);
    }

    public ArrayList<KeyboardListener> getKeyboardListeners() {
        return keyboardListeners;
    }

    public void start() {
        isOn = true;
        thread = new Thread(() -> {
            while (isOn) {
                KeyStroke key = null;
                try {
                    key = screen.readInput();
                    KeyType keyType = key.getKeyType();
                    if (keyType == KeyType.Character || keyType == KeyType.EOF) {
                        for (KeyboardListener listener : keyboardListeners) {   //notify all listeners and pass keystroke as argument
                            listener.updateOnKeyboard(key);
                        }
                        if(keyType == KeyType.EOF){
                            isOn = false;   //leave loop if terminal closes abnormally
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void stop() {
        isOn = false;
    }

    public void removeListeners() {
        keyboardListeners = null;
    }
}
