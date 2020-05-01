package com.noclue.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class DoorTest {

    @Test
    public void draw() {
        Door door = new Door();

        Position p1 = Mockito.mock(Position.class);
        when(p1.getRealPosition()).thenReturn(new Position(20,20,15,15));
        when(p1.getX()).thenReturn(15);
        when(p1.getY()).thenReturn(15);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        door.draw(textGraphics, p1);

        Mockito.verify(textGraphics, Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ffFF00"));

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()," 0000 ",BOLD);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()+1,"000000",BOLD);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()+2,"000000",BOLD);

        Mockito.verify(textGraphics, Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(textGraphics, Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));

    }
}