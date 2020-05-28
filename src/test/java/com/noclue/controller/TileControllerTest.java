package com.noclue.controller;

import com.noclue.model.TileModel;
import com.noclue.model.block.NoBlockModel;
import com.noclue.model.collectible.Collectible;
import com.noclue.view.NoView;
import com.noclue.view.TileView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class TileControllerTest {
    TileController tileController;
    TileModel tileModel = Mockito.mock(TileModel.class);
    TileView tileView = Mockito.mock(TileView.class);

    @Before
    public void setUp() throws Exception {
        tileModel = Mockito.mock(TileModel.class);
        tileView = Mockito.mock(TileView.class);
        tileController = new TileController(tileModel,tileView);
    }

    @Test
    public void blankTile() {
        tileController.blankTile();
        Mockito.verify(tileModel,Mockito.times(1))
                .setFiller(any(NoBlockModel.class));
        Mockito.verify(tileView,Mockito.times(1))
                .setFiller(any(NoView.class));
    }

    @Test
    public void moveTile() {
        TileModel tm1 = Mockito.mock(TileModel.class);
        TileView tv1 = Mockito.mock(TileView.class);
        TileController t1 = new TileController(tm1,tv1);

        tileController.moveTile(t1);

        Mockito.verify(tileModel,Mockito.times(1))
                .setFiller(any(NoBlockModel.class));
        Mockito.verify(tileView,Mockito.times(1))
                .setFiller(any(NoView.class));


        Assert.assertEquals(tm1.getFiller(),tileModel.getFiller());
        Assert.assertEquals(tv1.getFiller(),tileView.getFiller());
    }

    @Test
    public void blankCollectible() {
        tileController.blankCollectible();
        Mockito.verify(tileModel,Mockito.times(1))
                .setCollectible(any(Collectible.class));
        Mockito.verify(tileView,Mockito.times(1))
                .setCollectible(any(NoView.class));
    }
}