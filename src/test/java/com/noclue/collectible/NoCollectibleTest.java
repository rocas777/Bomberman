package com.noclue.collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class NoCollectibleTest {

    @Test
    public void draw() {
        NoCollectible noCollectible = new NoCollectible();

        Position p1 = Mockito.mock(Position.class);
        when(p1.getRealPosition()).thenReturn(new Position(20,20,15,15));
        when(p1.getX()).thenReturn(15);
        when(p1.getY()).thenReturn(15);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        noCollectible.draw(textGraphics,p1);
    }
}