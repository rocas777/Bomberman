package com.noclue.keyboard;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;

public class KeyBoard {
    private final TerminalScreen screen;
    private Boolean isOn;
    private ArrayList<KeyboardListener> keyboardListeners = new ArrayList<>();
    private Thread thread;

    public KeyBoard(TerminalScreen screen) {
        setOn(false);
        this.screen = screen;
    }

    public void addListener(KeyboardListener keyboardListener) {
        getKeyboardListeners().add(keyboardListener);
    }

    public ArrayList<KeyboardListener> getKeyboardListeners() {
        return keyboardListeners;
    }

    public void setKeyboardListeners(ArrayList<KeyboardListener> keyboardListeners) {
        this.keyboardListeners = keyboardListeners;
    }

    public void start() {
        setOn(true);
        setThread(new Thread(() -> {
            while (getOn()) {
                KeyStroke key = null;
                try {
                    key = getScreen().readInput();
                    KeyType keyType = key.getKeyType();
                    if (keyType == KeyType.Character || keyType == KeyType.EOF) {
                        for (KeyboardListener listener : getKeyboardListeners()) {   //notify all listeners and pass keystroke as argument
                            listener.updateOnKeyboard(key);
                        }
                        if (keyType == KeyType.EOF) {
                            setOn(false);   //leave loop if terminal closes abnormally
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        getThread().start();
    }

    public void stop() {
        setOn(false);
    }

    public void removeListeners() {
        setKeyboardListeners(null);
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
