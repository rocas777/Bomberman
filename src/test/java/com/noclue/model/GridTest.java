package com.noclue.model;

import com.noclue.controller.TileController;
import com.noclue.controller.TileControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GridTest {

    @Test
    public void add_collumn() {
        Grid grid = new Grid();
        Assert.assertEquals(0,grid.getTiles().size());
        grid.add_collumn();
        Assert.assertEquals(1,grid.getTiles().size());
        grid.add_collumn();
        Assert.assertEquals(2,grid.getTiles().size());
    }

    @Test
    public void getTile() {
        Grid grid = new Grid();
        Position position = Mockito.mock(Position.class);
        when(position.getX()).thenReturn(1);
        when(position.getY()).thenReturn(1);
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tilecontrollers = new CopyOnWriteArrayList<>();
        tilecontrollers.add(new CopyOnWriteArrayList<>());
        tilecontrollers.add(new CopyOnWriteArrayList<>());
        tilecontrollers.get(1).add(null);
        TileController tileController = Mockito.mock(TileController.class);
        tilecontrollers.get(1).add(tileController);
        grid.setTiles(tilecontrollers);
        Assert.assertEquals(tileController,grid.getTile(position));
    }

    @Test
    public void setTiles() {
        Grid grid = new Grid();
        Position position = Mockito.mock(Position.class);
        when(position.getX()).thenReturn(1);
        when(position.getY()).thenReturn(1);
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tilecontrollers = new CopyOnWriteArrayList<>();
        tilecontrollers.add(new CopyOnWriteArrayList<>());
        tilecontrollers.add(new CopyOnWriteArrayList<>());
        tilecontrollers.get(1).add(null);
        TileController tileController = Mockito.mock(TileController.class);
        tilecontrollers.get(1).add(tileController);
        grid.setTiles(tilecontrollers);
        Assert.assertEquals(tileController,grid.getTile(position));

        grid.setTiles(null,position);
        Assert.assertEquals(null,grid.getTile(position));
    }
}