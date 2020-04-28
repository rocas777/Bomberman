package com.noclue.collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Position;

public interface Collectible {
    public void draw(TextGraphics textGraphics, Position position);
}
