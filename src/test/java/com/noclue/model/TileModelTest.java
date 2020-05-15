package com.noclue.model;

import com.noclue.model.collectible.Collectible;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TileTest {
    Tile tile;
    Position position;
    Filler filler;
    Filler f2;
    Collectible collectible;
    Collectible c2;
    @Before
    public void setup(){
        position = Mockito.mock(Position.class);
        filler = Mockito.mock(Filler.class);
        f2 = Mockito.mock(Filler.class);
        collectible = Mockito.mock(Collectible.class);
        c2 =  Mockito.mock(Collectible.class);
        tile = new Tile(position,collectible,filler);
        when(filler.isFilled()).thenReturn(true);
    }

    @Test
    public void getFiller() {
        Assert.assertEquals(tile.getFiller(),filler);
    }

    @Test
    public void getCollectible() {
        Assert.assertEquals(tile.getCollectible(),collectible);
    }

    @Test
    public void setCollectible() {
        tile.setCollectible(c2);
        Assert.assertEquals(tile.getCollectible(),c2);
        tile.setCollectible(null);
        Assert.assertEquals(tile.getCollectible(),null);
    }

    @Test
    public void setFiller() {
        tile.setFiller(f2);
        Assert.assertEquals(tile.getFiller(),f2);
        tile.setFiller(null);
        Assert.assertEquals(tile.getFiller(),null);
    }

    @Test
    public void isFilled() {
        Assert.assertEquals(true,tile.isFilled());
    }
}