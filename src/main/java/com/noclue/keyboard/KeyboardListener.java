package com.noclue.keyboard;

import com.googlecode.lanterna.input.KeyStroke;

public interface KeyboardListener {
    void updateOnKeyboard(KeyStroke keyPressed);
}
