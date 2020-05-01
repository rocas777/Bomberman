package com.noclue.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Movement;
import com.noclue.Position;
import com.noclue.difficulty.Difficulty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MonsterTest {

    Monster monster;
    private class mockDifficulty implements Difficulty {

        @Override
        public ArrayList<Movement> nextMove(Position position) {

            ArrayList tmp= new ArrayList();
            tmp.add(new Position(20,20,15,15));
            return tmp;
        }
    }

    @Before
    public void setup(){
        monster=new Monster(new mockDifficulty());
    }

    @Test
    public void draw() {


        Position p1 = Mockito.mock(Position.class);
        when(p1.getRealPosition()).thenReturn(new Position(20,20,15,15));
        when(p1.getX()).thenReturn(15);
        when(p1.getY()).thenReturn(15);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        monster.draw(textGraphics, p1);

        Mockito.verify(textGraphics, Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ff0000"));

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY(),"*(OO)*",BOLD);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()+1,"X=VV=X",BOLD);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX(),p1.getRealPosition().getY()+2,"X=VV=X",BOLD);

        Mockito.verify(textGraphics, Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(textGraphics, Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    @Test
    public void isFilled() {
        Assert.assertEquals(true,monster.isFilled());
    }

    @Test
    public void nextMove() {
        Assert.assertEquals(new mockDifficulty().nextMove(new Position(20,20,15,15))
                ,monster.nextMove(new Position(20,20,15,15)));
    }
}