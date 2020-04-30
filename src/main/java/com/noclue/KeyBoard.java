package com.noclue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.noclue.character.TimeListener;

import java.io.IOException;
import java.util.ArrayList;

public class KeyBoard {
    static private ArrayList<KeyboardListener> keyboardListeners=new ArrayList<>();
    boolean isOn;
    static private TerminalScreen screen;
    KeyBoard(TerminalScreen screen){
        isOn=false;
        KeyBoard.screen=screen;
    }

    static public void addListener(KeyboardListener keyboardListener){
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
                        //System.out.println(key.getCharacter());
                        if (key.getCharacter() == 'q') {
                            isOn = false;
                            //System.out.println(key);
                            screen.close();
                        } else if (key.getKeyType() == KeyType.EOF) {
                            isOn = false;
                        } else {
                            for(KeyboardListener listener:keyboardListeners)
                                listener.updateOnKeyboard(key);
                        }
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
