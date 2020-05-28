package com.noclue.controller;

import com.noclue.model.Filler;
import com.noclue.model.Grid;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.character.HeroModel;
import com.noclue.model.state.DeactivateState;
import com.noclue.model.state.IsTouchingState;
import com.noclue.view.character.HeroView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HeroControllerTest {
    HeroController heroController;
    LivesModel livesModel = Mockito.mock(LivesModel.class);
    DeactivateState deactivateState = Mockito.mock(DeactivateState.class);
    IsTouchingState isTouchingState = Mockito.mock(IsTouchingState.class);
    HeroModel heroModel = Mockito.mock(HeroModel.class);

    @Before
    public void setup(){
        HeroView heroView = Mockito.mock(HeroView.class);
        heroController = new HeroController(heroModel,heroView);
        heroModel.setLivesModel(livesModel);
        heroModel.setDeactivateState(deactivateState);
        when(heroModel.getDeactivateState()).thenReturn(deactivateState);
        when(heroModel.getIsTouchingState()).thenReturn(isTouchingState);
        when(heroModel.getLivesModel()).thenReturn(livesModel);
    }
    @Test
    public void deactivate() {
        when(deactivateState.deactivate(any(LivesModel.class))).thenReturn(true);
        Assert.assertTrue(heroController.deactivate());
        Mockito.verify(deactivateState,Mockito.times(1))
                .deactivate(livesModel);


        when(deactivateState.deactivate(any(LivesModel.class))).thenReturn(false);
        Assert.assertFalse(heroController.deactivate());
        Mockito.verify(deactivateState,Mockito.times(2))
                .deactivate(livesModel);
    }

    @Test
    public void addLife() {

        when(livesModel.getLives()).thenReturn(6);

        heroController.addLife();
        verify(livesModel,times(0))
                .setLives(7);
        verify(livesModel,times(0))
                .setLives(anyInt());

        when(livesModel.getLives()).thenReturn(1);

        heroController.addLife();
        verify(livesModel,times(1))
                .setLives(2);
        verify(livesModel,times(1))
                .setLives(anyInt());

        when(livesModel.getLives()).thenReturn(5);

        heroController.addLife();
        verify(livesModel,times(1))
                .setLives(anyInt());

        when(livesModel.getLives()).thenReturn(4);

        heroController.addLife();
        verify(livesModel,times(1))
                .setLives(5);
        verify(livesModel,times(2))
                .setLives(anyInt());
    }

    @Test
    public void isTouching() {
        when(isTouchingState.isTouching(any(Filler.class))).thenReturn(true);

        Filler filler = Mockito.mock(Filler.class);
        Assert.assertTrue(heroController.isTouching(filler));
        Mockito.verify(isTouchingState,Mockito.times(1))
                .isTouching(filler);

        when(isTouchingState.isTouching(any(Filler.class))).thenReturn(false);

        Assert.assertFalse(heroController.isTouching(filler));
        Mockito.verify(isTouchingState,Mockito.times(2))
                .isTouching(filler);
    }

    Grid grid = Mockito.mock(Grid.class);
    Position position = Mockito.mock(Position.class);
    TileController tileController = Mockito.mock(TileController.class);

    @Test
    public void moveLeft() {
        when(heroModel.getPosition()).thenReturn(position);
        when(grid.getTile(position)).thenReturn(tileController);

        heroController.moveLeft(grid);

        verify(tileController,times(1))
                .moveTile(any());
        verify(tileController,times(1))
                .moveTile(grid.getTile(heroController.model.getPosition().getLeft()));

        verify(position,times(1)).setLeft();
    }

    @Test
    public void moveRight() {
        when(heroModel.getPosition()).thenReturn(position);
        when(grid.getTile(position)).thenReturn(tileController);

        heroController.moveRight(grid);

        verify(tileController,times(1))
                .moveTile(any());
        verify(tileController,times(1))
                .moveTile(grid.getTile(heroController.model.getPosition().getRight()));

        verify(position,times(1)).setRight();
    }

    @Test
    public void moveUp() {
        when(heroModel.getPosition()).thenReturn(position);
        when(grid.getTile(position)).thenReturn(tileController);

        heroController.moveUp(grid);

        verify(tileController,times(1))
                .moveTile(any());
        verify(tileController,times(1))
                .moveTile(grid.getTile(heroController.model.getPosition().getUp()));

        verify(position,times(1)).setUp();
    }

    @Test
    public void moveDown() {
        when(heroModel.getPosition()).thenReturn(position);
        when(grid.getTile(position)).thenReturn(tileController);

        heroController.moveDown(grid);

        verify(tileController,times(1))
                .moveTile(any());
        verify(tileController,times(1))
                .moveTile(grid.getTile(heroController.model.getPosition().getDown()));

        verify(position,times(1)).setDown();
    }
}