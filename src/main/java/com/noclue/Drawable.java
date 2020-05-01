package com.noclue;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.Position;

public interface Drawable {
    void draw(TextGraphics textGraphics, Position position);
}
