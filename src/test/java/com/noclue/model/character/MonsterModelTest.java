package com.noclue.model.character;

import com.noclue.Movement;
import com.noclue.controller.TileController;
import com.noclue.controller.TileControllerTest;
import com.noclue.model.Filler;
import com.noclue.model.Grid;
import com.noclue.model.Position;
import com.noclue.model.difficulty.Difficulty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MonsterModelTest {
    MonsterModel monsterModel = null;
    Position position = Mockito.mock(Position.class);
    Difficulty difficulty = Mockito.mock(Difficulty.class);
    @Before
    public void setup(){
        monsterModel = new MonsterModel(difficulty,position);
    }

    @Test
    public void nextMove() {
        Position p1 = Mockito.mock(Position.class);
        ArrayList<Movement> movements = monsterModel.nextMove(p1,null);
        Assert.assertEquals(ArrayList.class,movements.getClass());
        Mockito.verify(difficulty,Mockito.times(1)).nextMove(position,p1,null);
    }

    @Test
    public void isTouching() {
        Filler filler =Mockito.mock(Filler.class);
        when(filler.isFilled()).thenReturn(true);
        Assert.assertFalse(monsterModel.isTouching(filler));

        when(filler.isFilled()).thenReturn(false);
        Assert.assertTrue(monsterModel.isTouching(filler));
        Mockito.verify(filler,times(1)).deactivate();
    }

    @Test
    public void moveLeft() {
        Grid grid = Mockito.mock(Grid.class);
        Position position = Mockito.mock(Position.class);
        Position pl = Mockito.mock(Position.class);
        when(position.getLeft()).thenReturn(pl);
        monsterModel.setPosition(position);

        TileController t = Mockito.mock(TileController.class);
        TileController tl = Mockito.mock(TileController.class);
        when(grid.getTile(position)).thenReturn(t);
        when(grid.getTile(pl)).thenReturn(tl);

        monsterModel.moveLeft(grid);
        verify(t,times(1)).moveTile(tl);
        verify(position,times(1)).setLeft();

    }

    @Test
    public void moveRight() {
        Grid grid = Mockito.mock(Grid.class);
        Position position = Mockito.mock(Position.class);
        Position pl = Mockito.mock(Position.class);
        when(position.getRight()).thenReturn(pl);
        monsterModel.setPosition(position);

        TileController t = Mockito.mock(TileController.class);
        TileController tl = Mockito.mock(TileController.class);
        when(grid.getTile(position)).thenReturn(t);
        when(grid.getTile(pl)).thenReturn(tl);

        monsterModel.moveRight(grid);
        verify(t,times(1)).moveTile(tl);
        verify(position,times(1)).setRight();
    }

    @Test
    public void moveUp() {
        Grid grid = Mockito.mock(Grid.class);
        Position position = Mockito.mock(Position.class);
        Position pl = Mockito.mock(Position.class);
        when(position.getUp()).thenReturn(pl);
        monsterModel.setPosition(position);

        TileController t = Mockito.mock(TileController.class);
        TileController tl = Mockito.mock(TileController.class);
        when(grid.getTile(position)).thenReturn(t);
        when(grid.getTile(pl)).thenReturn(tl);

        monsterModel.moveUp(grid);
        verify(t,times(1)).moveTile(tl);
        verify(position,times(1)).setUp();
    }

    @Test
    public void moveDown() {
        Grid grid = Mockito.mock(Grid.class);
        Position position = Mockito.mock(Position.class);
        Position pl = Mockito.mock(Position.class);
        when(position.getDown()).thenReturn(pl);
        monsterModel.setPosition(position);

        TileController t = Mockito.mock(TileController.class);
        TileController tl = Mockito.mock(TileController.class);
        when(grid.getTile(position)).thenReturn(t);
        when(grid.getTile(pl)).thenReturn(tl);

        monsterModel.moveDown(grid);
        verify(t,times(1)).moveTile(tl);
        verify(position,times(1)).setDown();
    }
}