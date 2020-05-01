package com.noclue.model.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class NoBlockModel implements Block {
    @Override
    public boolean isFilled() {
        return false;
    }
}
