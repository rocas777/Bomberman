package com.noclue.model.block;

import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class IndestructibleBlockModelTest {
    IndestructibleBlockModel i1;
    IndestructibleBlockModel i2;
    Position p1;
    Position p2;

    @Before
    public void setup(){
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        i1 = new IndestructibleBlockModel(p1);
        i2 = new IndestructibleBlockModel(p2);
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(i1.getPosition(),p1);
        Assert.assertEquals(i2.getPosition(),p2);

        Assert.assertNotEquals(i1.getPosition(),p2);
        Assert.assertNotEquals(i2.getPosition(),p1);
    }

    @Test
    public void setPosition() {
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        i1.setPosition(p2);
        i2.setPosition(p1);

        Assert.assertNotEquals(i1.getPosition(),p1);
        Assert.assertNotEquals(i2.getPosition(),p2);

        Assert.assertEquals(i2.getPosition(),p1);
        Assert.assertEquals(i1.getPosition(),p2);
    }

    @Test
    public void isFilled() {
        Assert.assertEquals(i1.isFilled(),true);
        Assert.assertEquals(i2.isFilled(),true);
    }
}