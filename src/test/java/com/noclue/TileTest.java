package com.noclue;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TileTest {
    @Test
    public void Tile(){   // really a stub
        int x=0;
        int y=0;
        Tile a=new Tile(x,y);
        Assert.assertEquals(a.getX(),0);
        Assert.assertEquals(a.getY(),0);

    }
}
