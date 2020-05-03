package com.noclue.model.character;

import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroModelTest {
    HeroModel h1;
    HeroModel h2;
    Position p1;
    Position p2;
    @Before
    public void setup(){
        p1 = Mockito.mock(Position.class);
        p2 = Mockito.mock(Position.class);
        h1 = new HeroModel(p1);
        h2 = new HeroModel(p2);
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
    }
}