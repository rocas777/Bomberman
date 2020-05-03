package com.noclue.model.character;

import com.noclue.Movement;
import com.noclue.model.Position;
import com.noclue.model.difficulty.Easy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MonsterModelTest {
    MonsterModel h1;
    MonsterModel h2;
    Position p1;
    Position p2;
    ArrayList<Movement> positions;
    @Before
    public void setup(){
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        Easy easy = Mockito.mock(Easy.class);
        positions = new ArrayList<>();
        positions.add(Movement.right);
        positions.add(Movement.left);
        positions.add(Movement.up);
        positions.add(Movement.down);
        when(easy.nextMove(p1)).thenReturn(positions);
        when(easy.nextMove(p2)).thenReturn(positions);
        h1 = new MonsterModel(easy, p1);
        h2 = new MonsterModel(easy, p2);
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(h1.getPosition(),p1);
        Assert.assertEquals(h2.getPosition(),p2);

        Assert.assertNotEquals(h1.getPosition(),p2);
        Assert.assertNotEquals(h2.getPosition(),p1);
    }

    @Test
    public void setPosition() {
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        h1.setPosition(p2);
        h2.setPosition(p1);

        Assert.assertNotEquals(h1.getPosition(),p1);
        Assert.assertNotEquals(h2.getPosition(),p2);

        Assert.assertEquals(h2.getPosition(),p1);
        Assert.assertEquals(h1.getPosition(),p2);
    }

    @Test
    public void isFilled() {
        Assert.assertEquals(h1.isFilled(),true);
    }

    @Test
    public void nextMove() {
        Assert.assertEquals(h1.nextMove(p1),positions);
        Assert.assertEquals(h2.nextMove(p2),positions);
    }
}