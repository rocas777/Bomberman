package com.noclue.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CoinTest {
    Coin coin;
    @Before
    public void setup(){
        coin = new Coin();
    }

    @Test
    public void draw() {
        Position p1 = Mockito.mock(Position.class);
        when(p1.getRealPosition()).thenReturn(new Position(20,20,15,15));
        when(p1.getX()).thenReturn(15);
        when(p1.getY()).thenReturn(15);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        coin.draw(textGraphics, p1);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY(),"  00  ",BOLD);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()+1," 0000 ",BOLD);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()+2,"  00  ",BOLD);
    }
}