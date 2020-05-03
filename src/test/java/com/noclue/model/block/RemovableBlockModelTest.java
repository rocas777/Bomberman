package com.noclue.model.block;

import com.noclue.model.Position;
import com.noclue.model.RemovableBlockModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RemovableBlockModelTest {
    RemovableBlockModel r1;
    RemovableBlockModel r2;
    Position p1;
    Position p2;

    @Before
    public void setup(){
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        r1 = new RemovableBlockModel(p1);
        r2 = new RemovableBlockModel(p2);
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(r1.getPosition(),p1);
        Assert.assertEquals(r2.getPosition(),p2);

        Assert.assertNotEquals(r1.getPosition(),p2);
        Assert.assertNotEquals(r2.getPosition(),p1);
    }

    @Test
    public void setPosition() {
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        r1.setPosition(p2);
        r2.setPosition(p1);

        Assert.assertNotEquals(r1.getPosition(),p1);
        Assert.assertNotEquals(r2.getPosition(),p2);

        Assert.assertEquals(r2.getPosition(),p1);
        Assert.assertEquals(r1.getPosition(),p2);
    }

    @Test
    public void isFilled() {
        Assert.assertEquals(r1.isFilled(),true);
        Assert.assertEquals(r2.isFilled(),true);
    }
}