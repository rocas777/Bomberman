package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IBombInterface;
import com.noclue.IView;
import com.noclue.Movement;
import com.noclue.model.*;
import com.noclue.model.character.MonsterModel;
import com.noclue.timer.Timer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class FieldControllerTest {

    @Test
    public void getTimeLeft() {

    }

    @Test
    public void setup() {
    }

    @Test
    public void createTile() {

    }

    @Test
    public void setRemovableBlocks() {

    }

    @Test
    public void setIndestructibleBlocks() {

    }

    @Test
    public void setMonsters() {

    }

    @Test
    public void setHeroPos() {

    }

    @Test
    public void setDoorPos() {

    }

    @Test
    public void setHero() {

    }

    @Test
    public void setDoor() {

    }

    @Test
    public void updateOnKeyboard() {

    }

    @Test
    public void updateOnTime() {

    }

    @Test
    public void updateMonsterPosition() {
        HeroController heroController = Mockito.mock(HeroController.class);
        FieldModel model = Mockito.mock(FieldModel.class);
        IView iView = Mockito.mock(IView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = new FieldController(model,iView,iView,iView,textGraphics,timeLeft);
        Position p = new Position(10,10,1,1);
        Position l = p.getLeft();
        Position ll = p.getLeft().getLeft();
        Position r = p.getRight();
        Position rr = p.getRight().getRight();

        Grid grid = new Grid();
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();
        tiles.add(new CopyOnWriteArrayList<>());
        tiles.add(new CopyOnWriteArrayList<>());
        tiles.add(new CopyOnWriteArrayList<>());
        when(model.getTiles()).thenReturn(grid);

        TileController t00 = Mockito.mock(TileController.class);
        Filler f00 = Mockito.mock(Filler.class);
        when(t00.getFiller()).thenReturn(f00);
        when(f00.deactivate()).thenReturn(false);

        TileController t01 = Mockito.mock(TileController.class);
        Filler f01 = Mockito.mock(Filler.class);
        when(t01.getFiller()).thenReturn(f01);
        when(f01.deactivate()).thenReturn(false);

        TileController t02 = Mockito.mock(TileController.class);
        Filler f02 = Mockito.mock(Filler.class);
        when(t02.getFiller()).thenReturn(f02);
        when(f02.deactivate()).thenReturn(false);

        TileController t10 = Mockito.mock(TileController.class);
        Filler f10 = Mockito.mock(Filler.class);
        when(t10.getFiller()).thenReturn(f10);
        when(f10.deactivate()).thenReturn(false);

        TileController t11 = Mockito.mock(TileController.class);
        Filler f11 = Mockito.mock(Filler.class);
        when(t11.getFiller()).thenReturn(f11);
        when(f11.deactivate()).thenReturn(false);

        TileController t12 = Mockito.mock(TileController.class);
        Filler f12 = Mockito.mock(Filler.class);
        when(t12.getFiller()).thenReturn(f12);
        when(f12.deactivate()).thenReturn(false);

        TileController t20 = Mockito.mock(TileController.class);
        Filler f20 = Mockito.mock(Filler.class);
        when(t20.getFiller()).thenReturn(f20);
        when(f20.deactivate()).thenReturn(false);

        TileController t21 = Mockito.mock(TileController.class);
        Filler f21 = Mockito.mock(Filler.class);
        when(t21.getFiller()).thenReturn(f21);
        when(f21.deactivate()).thenReturn(false);

        TileController t22 = Mockito.mock(TileController.class);
        Filler f22 = Mockito.mock(Filler.class);
        when(t22.getFiller()).thenReturn(f22);
        when(f22.deactivate()).thenReturn(false);


        tiles.get(0).add(t00);
        tiles.get(0).add(t01);
        tiles.get(0).add(t02);

        tiles.get(1).add(t10);
        tiles.get(1).add(t11);
        tiles.get(1).add(t12);

        tiles.get(2).add(t20);
        tiles.get(2).add(t21);
        tiles.get(2).add(t22);

        grid.setTiles(tiles);

        Movement ml = Movement.left;
        Movement mr = Movement.right;
        Movement mu = Movement.up;
        Movement md = Movement.down;
        Movement ms = Movement.stay;

        ArrayList<Movement> movements = new ArrayList<>();
        movements.add(ml);
        movements.add(mr);
        movements.add(md);
        movements.add(mu);


        when(model.getHero()).thenReturn(heroController);
        when(heroController.getPosition()).thenReturn(new Position(10,10,5,5));

        when(model.checkPos(p,ml)).thenReturn(false);
        when(model.checkPos(p,mr)).thenReturn(false);
        when(model.checkPos(p,mu)).thenReturn(false);
        when(model.checkPos(p,md)).thenReturn(false);
        when(model.checkPos(p,ms)).thenReturn(false);

        MonsterModel monsterModel = Mockito.mock(MonsterModel.class);
        when(monsterModel.nextMove(heroController.getPosition(),null)).thenReturn(movements);
        when(monsterModel.getPosition()).thenReturn(p);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel,times(0))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(0))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(0))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);


        when(model.checkPos(p,ml)).thenReturn(true);
        when(model.checkPos(p,mr)).thenReturn(false);
        when(model.checkPos(p,mu)).thenReturn(false);
        when(model.checkPos(p,md)).thenReturn(false);
        when(model.checkPos(p,ms)).thenReturn(false);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(0))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(0))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);

        when(model.checkPos(p,ml)).thenReturn(false);
        when(model.checkPos(p,mr)).thenReturn(true);
        when(model.checkPos(p,mu)).thenReturn(false);
        when(model.checkPos(p,md)).thenReturn(false);
        when(model.checkPos(p,ms)).thenReturn(false);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(0))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);

        when(model.checkPos(p,ml)).thenReturn(false);
        when(model.checkPos(p,mr)).thenReturn(false);
        when(model.checkPos(p,mu)).thenReturn(true);
        when(model.checkPos(p,md)).thenReturn(false);
        when(model.checkPos(p,ms)).thenReturn(false);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(1))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);

        when(model.checkPos(p,ml)).thenReturn(false);
        when(model.checkPos(p,mr)).thenReturn(false);
        when(model.checkPos(p,mu)).thenReturn(false);
        when(model.checkPos(p,md)).thenReturn(true);
        when(model.checkPos(p,ms)).thenReturn(false);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(1))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(1))
                .moveDown(grid);


        when(heroController.getPosition()).thenReturn(new Position(10,10,1,2));

        when(model.checkPos(p,ml)).thenReturn(false);
        when(model.checkPos(p,mr)).thenReturn(false);
        when(model.checkPos(p,mu)).thenReturn(false);
        when(model.checkPos(p,md)).thenReturn(true);
        when(model.checkPos(p,ms)).thenReturn(false);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(1))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(1))
                .moveDown(grid);
    }

    @Test
    public void timeIsUp() {
        FieldModel model = Mockito.mock(FieldModel.class);
        IView iView = Mockito.mock(IView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = new FieldController(model,iView,iView,iView,textGraphics,timeLeft);
        Timer timer = Mockito.mock(Timer.class);
        Timer.setSeconds(20);
        when(model.gettServer()).thenReturn(timer);

        fieldController.setTimerSum(0);
        Assert.assertFalse(fieldController.timeIsUp());
        Assert.assertFalse(fieldController.ended);

        fieldController.setEnded(false);

        fieldController.setTimerSum(50);
        Assert.assertTrue(fieldController.timeIsUp());

        fieldController.setEnded(false);

        fieldController.setTimerSum(51);
        Assert.assertTrue(fieldController.timeIsUp());

        Mockito.verify(iView,times(4))
                .draw();
        Mockito.verify(timer,times(2))
                .removeListener(fieldController);
        Assert.assertTrue(fieldController.ended);
        Mockito.verify(timeLeft,times(2))
                .minusSecond();

        when(timeLeft.getSeconds()).thenReturn(1);
        fieldController.timeIsUp();
        Assert.assertEquals(0,fieldController.timerSum);

    }

    @Test
    public void explode() {
        FieldModel fieldModel = new FieldModel(100,100,1);
        IView iView = Mockito.mock(IView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = new FieldController(fieldModel,iView,iView,iView,textGraphics,timeLeft);


        TileController t1 = Mockito.mock(TileController.class);
        Filler f1 = Mockito.mock(Filler.class);
        when(t1.getFiller()).thenReturn(f1);
        when(t1.getFiller().deactivate()).thenReturn(true);

        TileController t2 = Mockito.mock(TileController.class);
        Filler f2 = Mockito.mock(Filler.class);
        when(t2.getFiller()).thenReturn(f2);
        when(t2.getFiller().deactivate()).thenReturn(false);

        TileController t3 = Mockito.mock(TileController.class);
        Filler f3 = Mockito.mock(Filler.class);
        when(t3.getFiller()).thenReturn(f3);
        when(t3.getFiller().deactivate()).thenReturn(true);

        TileController t4 = Mockito.mock(TileController.class);
        Filler f4 = Mockito.mock(Filler.class);
        when(t4.getFiller()).thenReturn(f4);
        when(t4.getFiller().deactivate()).thenReturn(false);

        TileController t5 = Mockito.mock(TileController.class);
        Filler f5= Mockito.mock(Filler.class);
        when(t5.getFiller()).thenReturn(f5);
        when(t5.getFiller().deactivate()).thenReturn(true);

        Position position = new Position(10,10,2,0);
        ArrayList<Position> positions = new ArrayList<>();
        Position pl = position.getLeft();
        Position pll = position.getLeft().getLeft();
        Position pr = position.getRight();
        Position prr = position.getRight().getRight();
        positions.add(pl);
        positions.add(pll);
        positions.add(pr);
        positions.add(prr);
        positions.add(position);

        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();
        tiles.add(new CopyOnWriteArrayList<>());
        tiles.get(tiles.size()-1).add(t1);
        tiles.get(tiles.size()-1).add(t2);
        tiles.get(tiles.size()-1).add(t3);
        tiles.get(tiles.size()-1).add(t4);
        tiles.get(tiles.size()-1).add(t5);
        Grid grid = new Grid();
        grid.setTiles(tiles);

        fieldModel.setTiles(grid);
        IBombInterface iBombInterface = Mockito.mock(IBombInterface.class);
        BombModel bombModel = Mockito.mock(BombModel.class);
        ArrayList a = new ArrayList<Position>();
        a.add(position);
        fieldModel.setBombModel(iBombInterface);
        when(iBombInterface.getModel()).thenReturn(bombModel);




        when(t1.getFiller().deactivate()).thenReturn(true);
        when(t2.getFiller().deactivate()).thenReturn(false);
        when(t3.getFiller().deactivate()).thenReturn(true);
        when(t4.getFiller().deactivate()).thenReturn(true);
        when(t5.getFiller().deactivate()).thenReturn(true);

        fieldController.explode(positions);

        a.clear();
        a.add(pr);
        a.add(prr);
        a.add(position);

        Mockito.verify(bombModel,times(1))
                .setExplosionList(a);

        when(t1.getFiller().deactivate()).thenReturn(true);
        when(t2.getFiller().deactivate()).thenReturn(true);
        when(t3.getFiller().deactivate()).thenReturn(true);
        when(t4.getFiller().deactivate()).thenReturn(true);
        when(t5.getFiller().deactivate()).thenReturn(true);

        fieldController.explode(positions);

        a.clear();
        a.add(pl);
        a.add(pll);
        a.add(pr);
        a.add(prr);
        a.add(position);

        Mockito.verify(bombModel,times(1))
                .setExplosionList(a);

        when(t1.getFiller().deactivate()).thenReturn(false);
        when(t2.getFiller().deactivate()).thenReturn(false);
        when(t3.getFiller().deactivate()).thenReturn(false);
        when(t4.getFiller().deactivate()).thenReturn(false);
        when(t5.getFiller().deactivate()).thenReturn(false);

        fieldController.explode(positions);

        a.clear();

        Mockito.verify(bombModel,times(1))
                .setExplosionList(a);

        when(t1.getFiller().deactivate()).thenReturn(true);
        when(t2.getFiller().deactivate()).thenReturn(false);
        when(t3.getFiller().deactivate()).thenReturn(false);
        when(t4.getFiller().deactivate()).thenReturn(false);
        when(t5.getFiller().deactivate()).thenReturn(true);

        fieldController.explode(positions);

        a.clear();

        Mockito.verify(bombModel,times(2))
                .setExplosionList(a);


    }

    @Test
    public void purge() {
        FieldModel fieldModel = new FieldModel(100,100,1);
        IView iView = Mockito.mock(IView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = new FieldController(fieldModel,iView,iView,iView,textGraphics,timeLeft);

        TileController tileController = Mockito.mock(TileController.class);
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();
        tiles.add(new CopyOnWriteArrayList<>());
        tiles.get(tiles.size()-1).add(tileController);
        Filler filler = Mockito.mock(Filler.class);

        CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList();
        MonsterModel m1 =Mockito.mock(MonsterModel.class);
        MonsterModel m2 =Mockito.mock(MonsterModel.class);
        MonsterModel m3 =Mockito.mock(MonsterModel.class);
        MonsterModel m4 =Mockito.mock(MonsterModel.class);
        MonsterModel m5 =Mockito.mock(MonsterModel.class);
        monsterModels.add(m1);
        monsterModels.add(m2);
        monsterModels.add(m3);
        monsterModels.add(m4);
        monsterModels.add(m5);

        when(tileController.getFiller()).thenReturn(filler);
        when(filler.isActive()).thenReturn(false);
        fieldModel.setMonsters(monsterModels);
        when(m1.isActive()).thenReturn(false);
        when(m2.isActive()).thenReturn(true);
        when(m3.isActive()).thenReturn(false);
        when(m4.isActive()).thenReturn(false);
        when(m5.isActive()).thenReturn(false);

        Grid grid = Mockito.mock(Grid.class);
        when(grid.getTiles()).thenReturn(tiles);
        fieldModel.setTiles(grid);

        fieldController.purge();
        Assert.assertEquals(1,fieldModel.getMonsters().size());
        Assert.assertEquals(m2,fieldModel.getMonsters().get(0));

        when(m2.isActive()).thenReturn(false);

        fieldController.purge();
        Assert.assertEquals(0,fieldModel.getMonsters().size());
        try {
            Assert.assertEquals(null,fieldModel.getMonsters().get(0));
            Assert.fail();
        }
        catch (Exception e){

        }
        Mockito.verify(tileController,times(2))
                .blankTile();


        Timer timer = Mockito.mock(Timer.class);
        HeroController heroController = Mockito.mock(HeroController.class);
        when(heroController.isActive()).thenReturn(false);
        fieldModel.settServer(timer);
        fieldModel.setHero(heroController);
        fieldController.purge();
        Mockito.verify(iView,times(2))
                .draw();
        Assert.assertEquals(true,fieldController.ended);

        fieldController.setEnded(false);
        when(heroController.isActive()).thenReturn(true);
        fieldModel.settServer(timer);
        fieldModel.setHero(heroController);
        fieldController.purge();
        Mockito.verify(iView,times(2))
                .draw();
        Mockito.verify(timer,times(1))
                .removeListener(fieldController);
        Assert.assertEquals(false,fieldController.ended);
    }

    @Test
    public void fireDone() {
        FieldModel fieldModel = new FieldModel(100,100,1);
        IView iView = Mockito.mock(IView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = new FieldController(fieldModel,iView,iView,iView,textGraphics,timeLeft);

        IBombInterface iBombInterface = Mockito.mock(IBombInterface.class);
        fieldModel.setBombModel(iBombInterface);

        Assert.assertEquals(iBombInterface,fieldModel.getBomb());
        fieldController.fireDone();
        Assert.assertEquals(null,fieldModel.getBomb());
    }
}