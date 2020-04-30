package com.noclue;

import com.googlecode.lanterna.graphics.TextGraphics;

public interface Filler {
    void draw(TextGraphics textGraphics,Position position);
    boolean isFilled();
}
