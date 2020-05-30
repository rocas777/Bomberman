package com.noclue.model.collectible;

import com.noclue.IView;
import com.noclue.controller.FieldController;
import com.noclue.controller.HeroController;
import com.noclue.controller.TileController;
import com.noclue.model.FieldModel;
import com.noclue.model.Grid;
import com.noclue.model.Position;
import com.noclue.model.TimeLeft;
import com.noclue.timer.Timer;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class VisitorTest {
    FieldController fieldController;
    FieldModel fieldModel;
    HeroController heroController;
    Position position;
    TileController tileController;
    Grid grid;
    @Before
    public void setup(){
        fieldController = mock(FieldController.class);
        fieldModel = mock(FieldModel.class);
        position = mock(Position.class);
        tileController = mock(TileController.class);
        grid = mock(Grid.class);
        heroController = mock(HeroController.class);
        when(fieldController.getModel()).thenReturn(fieldModel);
        when(fieldModel.getTiles()).thenReturn(grid);
        when(fieldModel.getHero()).thenReturn(heroController);
        when(grid.getTile(position)).thenReturn(tileController);
        when(heroController.getPosition()).thenReturn(position);

    }

    @Test
    public void visitDoor() {
        Timer timer = mock(Timer.class);
        IView iView = mock(IView.class);
        when(fieldModel.gettServer()).thenReturn(timer);
        when(fieldController.getWinView()).thenReturn(iView);
        when(fieldController.getView()).thenReturn(iView);

        Visitor.visitDoor(fieldController);

        verify(fieldModel,times(1)).setWon(true);
        verify(timer,times(1)).removeListener(fieldController);
        verify(iView,times(2)).draw();
        verify(fieldController,times(1)).setView(iView);
        verify(fieldController,times(1)).setEnded(true);

    }

    @Test
    public void visitCoin() {

        Visitor.visitCoin(fieldController);

        verify(fieldModel,times(1)).addPoint();
        verify(grid,times(1)).getTile(position);
        verify(tileController,times(1)).blankCollectible();
    }

    @Test
    public void visitTime() {
        TimeLeft timeLeft=mock(TimeLeft.class);
        when(fieldController.getTimeLeft()).thenReturn(timeLeft);
        Visitor.visitTime(fieldController);

        verify(timeLeft,times(1)).addTime();
        verify(grid,times(1)).getTile(position);
        verify(tileController,times(1)).blankCollectible();
    }

    @Test
    public void visitLife() {

        Visitor.visitLife(fieldController);

        verify(heroController,times(1)).addLife();
        verify(grid,times(1)).getTile(position);
        verify(tileController,times(1)).blankCollectible();
    }

    @Test
    public void visitInvencible() {
        Visitor.visitInvencible(fieldController);

        verify(heroController,times(1)).ActivateInvencible();
        verify(grid,times(1)).getTile(position);
        verify(tileController,times(1)).blankCollectible();
    }
}