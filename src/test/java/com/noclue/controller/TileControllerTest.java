package com.noclue.controller;

import com.noclue.IView;
import com.noclue.model.Filler;
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
import static org.mockito.Mockito.when;

public class TileControllerTest {
    TileController tileController;
    TileModel tileModel = Mockito.mock(TileModel.class);
    TileView tileView = Mockito.mock(TileView.class);

    @Before
    public void setUp() throws Exception {
        tileModel = Mockito.mock(TileModel.class);
        tileView = Mockito.mock(TileView.class);
        tileController = new TileController(tileModel,tileView);
        Filler filler = Mockito.mock(Filler.class);
        IView iView = Mockito.mock(IView.class);
        when(tileModel.getFiller()).thenReturn(filler);
        when(tileView.getFiller()).thenReturn(iView);
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
        Filler filler = Mockito.mock(Filler.class);
        IView view = Mockito.mock(IView.class);
        when(tm1.getFiller()).thenReturn(filler);
        when(tv1.getFiller()).thenReturn(view);

        tileController.moveTile(t1);

        Mockito.verify(tm1,Mockito.times(1))
                .setFiller(tileModel.getFiller());
        Mockito.verify(tv1,Mockito.times(1))
                .setFiller(tileView.getFiller());


        Mockito.verify(tileModel,Mockito.times(1))
                .setFiller(any(NoBlockModel.class));
        Mockito.verify(tileView,Mockito.times(1))
                .setFiller(any(NoView.class));
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