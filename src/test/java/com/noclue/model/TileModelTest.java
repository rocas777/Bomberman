package com.noclue.model;

import com.noclue.model.collectible.Collectible;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TileModelTest {
    TileModel tileModel;
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
        tileModel = new TileModel(position,collectible,filler);
        when(filler.isFilled()).thenReturn(true);
    }

    @Test
    public void getFiller() {
        Assert.assertEquals(tileModel.getFiller(),filler);
    }

    @Test
    public void getCollectible() {
        Assert.assertEquals(tileModel.getCollectible(),collectible);
    }

    @Test
    public void setCollectible() {
        tileModel.setCollectible(c2);
        Assert.assertEquals(tileModel.getCollectible(),c2);
        tileModel.setCollectible(null);
        Assert.assertEquals(tileModel.getCollectible(),null);
    }

    @Test
    public void setFiller() {
        tileModel.setFiller(f2);
        Assert.assertEquals(tileModel.getFiller(),f2);
        tileModel.setFiller(null);
        Assert.assertEquals(tileModel.getFiller(),null);
    }

    @Test
    public void isFilled() {
        Assert.assertEquals(true, tileModel.isFilled());
    }
}