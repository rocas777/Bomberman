package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.noclue.IBombInterface;
import com.noclue.IView;
import com.noclue.Movement;
import com.noclue.controller.bomb.BombController;
import com.noclue.model.*;
import com.noclue.model.block.IndestructibleBlockModel;
import com.noclue.model.block.NoBlockModel;
import com.noclue.model.block.RemovableBlockModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.collectible.Collectible;
import com.noclue.model.collectible.NoCollectibleModel;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.timer.Timer;
import com.noclue.view.NoView;
import com.noclue.view.block.IndestructibleBlockView;
import com.noclue.view.block.RemovableBlockView;
import com.noclue.view.bomb.BombViewFire;
import com.noclue.view.character.HeroView;
import com.noclue.view.character.MonsterView;
import com.noclue.view.collectible.DoorView;
import com.noclue.view.field.FieldView;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Mockito.*;

public class FieldControllerTest {

    @Test
    public void getTimeLeft() {

    }

    @Test
    public void setup() {
    }

    @Test
    public void createTile() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        Grid grid = new Grid();
        grid.add_column();
        grid.add_column();
        grid.addTile();
        grid.addTile();
        when(fieldModel.getTiles()).thenReturn(grid);

        Position position = new Position(23,15,1,1);
        IView a = Mockito.mock(IView.class);
        IView b = Mockito.mock(IView.class);
        TileModel tileModel = new TileModel(Mockito.mock(Collectible.class),Mockito.mock(Filler.class));
        fieldController.createTile(a,b,tileModel,position);

        Assert.assertEquals(a,grid.getTile(position).getView().getCollectible());
        Assert.assertEquals(b,grid.getTile(position).getView().getFiller());

    }

    @Test
    public void setRemovableBlocks() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        Grid grid = new Grid();
        when(fieldModel.getTiles()).thenReturn(grid);

        fieldController.setIndestructibleBlocks();
        fieldController.setRemovableBlocks(new Position(23,15,1,1),new Position(23,15,2,1),40);
        verify(fieldController,times(40)).createTile(any(), any(RemovableBlockView.class), any(TileModel.class), any(Position.class));

        int u = 0;

        for(int y=0;y<grid.getTiles().size();y++){
            for(int x=0;x<grid.getTiles().get(y).size();x++){
                if(grid.getTiles().get(y).get(x).getFiller() instanceof RemovableBlockModel)
                    u++;
            }
        }
        Assert.assertEquals(40,u);

    }

    @Test
    public void setIndestructibleBlocks() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        Grid grid = new Grid();
        when(fieldModel.getTiles()).thenReturn(grid);

        fieldController.setIndestructibleBlocks();
        verify(fieldController,times(132)).createTile(any(NoView.class), any(IndestructibleBlockView.class), any(TileModel.class), any(Position.class));
        verify(fieldController,times(213)).createTile(any(NoView.class), any(NoView.class), any(TileModel.class), any(Position.class));

        Assert.assertEquals(15,grid.getTiles().size());
        for(CopyOnWriteArrayList ct:grid.getTiles()){
            Assert.assertEquals(23,ct.size());
        }

        for(int y=0;y<grid.getTiles().size();y++){
            for(int x=0;x<grid.getTiles().get(y).size();x++){
                if(x == 0 || x==22 || y==0 || y==14 || (x%2 == 0 && y%2==0)) {
                    Assert.assertTrue(grid.getTiles().get(y).get(x).getFiller().isFilled());
                }
                else
                    Assert.assertFalse(grid.getTiles().get(y).get(x).getFiller().isFilled());
            }
        }

    }

    @Test
    public void setMonsters() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        Grid grid = new Grid();
        when(fieldModel.getTiles()).thenReturn(grid);
        fieldController.setIndestructibleBlocks();

        Random random = new Random();
        Position positionh = new Position(23,15,1,1);
        Position door = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);
        ArrayList<Difficulty> difficulties = new ArrayList<>();
        CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList<>();
        difficulties.add(Mockito.mock(Difficulty.class));
        difficulties.add(Mockito.mock(Difficulty.class));
        difficulties.add(Mockito.mock(Difficulty.class));
        difficulties.add(Mockito.mock(Difficulty.class));
        difficulties.add(Mockito.mock(Difficulty.class));
        difficulties.add(Mockito.mock(Difficulty.class));
        fieldController.setDifficulty(difficulties);
        when(fieldModel.getDifficulties()).thenReturn(difficulties);
        when(fieldModel.getMonsters()).thenReturn(monsterModels);
        doNothing().when(fieldController).createTile(any(),any(),any(),any());

        fieldController.setMonsters(positionh,door);
        for(MonsterModel m:monsterModels){
            Assert.assertNotEquals(positionh,m.getPosition());
            Assert.assertNotEquals(door,m.getPosition());
        }
        Assert.assertEquals(monsterModels.size(),difficulties.size());


    }

    @Test
    public void setHeroPos() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));

        Position position = new Position(23,15,1,1);
        Position positionh = fieldController.setHeroPos();
        Assert.assertEquals(position,positionh);

    }

    @Test
    public void setDoorPos() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        Grid grid = new Grid();
        when(fieldModel.getTiles()).thenReturn(grid);
        fieldController.setIndestructibleBlocks();

        Position position = new Position(15,15,5,5);
        Assert.assertNotEquals(position,fieldController.setDoorPos(position));
    }

    @Test
    public void setHero() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        doNothing().when(fieldController).createTile(any(),any(),any(),any());

        Position position = Mockito.mock(Position.class);

        fieldController.setHero(position);
        verify(fieldController,times(1)).createTile(any(),any(HeroView.class),any(TileModel.class),any(Position.class));
        verify(fieldModel,times(1)).setHero(any(HeroController.class));
    }

    @Test
    public void setDoor() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        doNothing().when(fieldController).createTile(any(),any(),any(),any());

        Position position = Mockito.mock(Position.class);

        fieldController.setDoor(position);
        verify(fieldController,times(1)).createTile(any(DoorView.class),any(RemovableBlockView.class),any(TileModel.class),any(Position.class));
    }

    @Test
    public void updateOnKeyboard() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        when(fieldController.model.getBomb()).thenReturn(null);
        HeroController heroController = Mockito.mock(HeroController.class);
        when(heroController.getPosition()).thenReturn(new Position(10,10,5,5));
        when(fieldController.model.getHero()).thenReturn(heroController);
        Timer timer = Mockito.mock(Timer.class);
        when(fieldController.model.gettServer()).thenReturn(timer);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        when(keyStroke.getCharacter()).thenReturn('p');

        fieldController.updateOnKeyboard(keyStroke);

        verify(fieldModel,times(1)).setBombModel(any(BombController.class));
        verify(timer,times(1)).addListener(null);

        when(keyStroke.getCharacter()).thenReturn('a');
        CopyOnWriteArrayList<KeyStroke> keyStrokes = Mockito.mock(CopyOnWriteArrayList.class);
        fieldController.keyStrokes = keyStrokes;
        fieldController.updateOnKeyboard(keyStroke);
        verify(keyStrokes,times(1)).add(keyStroke);


        fieldController.ended = true;
        fieldController.updateOnKeyboard(keyStroke);
        verify(fieldModel,times(1)).setBombModel(any(BombController.class));
        verify(timer,times(1)).addListener(null);
        verify(keyStrokes,times(1)).add(keyStroke);

    }


    @Test
    public void handleKeyboard() {
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        HeroController heroController = Mockito.mock(HeroController.class);
        when(fieldModel.getHero()).thenReturn(heroController);
        when(fieldModel.checkPos(any(),any())).thenReturn(true);

        CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        keyStrokes.add(keyStroke);
        when(keyStroke.getCharacter()).thenReturn('a');
        fieldController.keyStrokes = keyStrokes;


        Grid grid = Mockito.mock(Grid.class);
        when(fieldModel.getTiles()).thenReturn(grid);
        when(heroController.getPosition()).thenReturn(new Position(10,10,5,5));
        TileController controller = Mockito.mock(TileController.class);
        when(grid.getTile(any(Position.class))).thenReturn(controller);
        Collectible collectible = Mockito.mock(Collectible.class);
        when(controller.getCollectible()).thenReturn(collectible);
        Filler filler = Mockito.mock(Filler.class);
        when(controller.getFiller()).thenReturn(filler);

        fieldController.ended = true;
        when(heroController.isActive()).thenReturn(false);
        fieldController.handleKeyboard();

        Assert.assertEquals(0,keyStrokes.size());
        keyStrokes.add(keyStroke);

        verify(heroController,times(0)).isTouching(any());
        verify(heroController,times(0)).moveLeft(any());

        fieldController.ended = false;
        when(heroController.isActive()).thenReturn(false);
        fieldController.handleKeyboard();

        verify(heroController,times(0)).isTouching(any());
        verify(heroController,times(0)).moveLeft(any());

        Assert.assertEquals(0,keyStrokes.size());
        keyStrokes.add(keyStroke);

        fieldController.ended = false;
        when(heroController.isActive()).thenReturn(true);
        fieldController.handleKeyboard();

        verify(heroController,times(1)).isTouching(any());
        verify(heroController,times(1)).moveLeft(any());
        keyStrokes.add(keyStroke);

        when(keyStroke.getCharacter()).thenReturn('s');
        fieldController.handleKeyboard();

        verify(heroController,times(2)).isTouching(any());
        verify(heroController,times(1)).moveDown(any());
        keyStrokes.add(keyStroke);

        when(keyStroke.getCharacter()).thenReturn('w');
        fieldController.handleKeyboard();

        verify(heroController,times(3)).isTouching(any());
        verify(heroController,times(1)).moveUp(any());
        keyStrokes.add(keyStroke);

        when(keyStroke.getCharacter()).thenReturn('d');
        fieldController.handleKeyboard();

        verify(heroController,times(4)).isTouching(any());
        verify(heroController,times(1)).moveRight(any());

        verify(collectible,times(6)).visit(any(FieldController.class));



    }

    @Test
    public void updateOnTime() {
        Timer.setSeconds(250);
        FieldModel fieldModel = Mockito.mock(FieldModel.class);
        FieldView fieldView = Mockito.mock(FieldView.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        TimeLeft timeLeft = Mockito.mock(TimeLeft.class);
        FieldController fieldController = spy(new FieldController(fieldModel,fieldView,fieldView,fieldView,textGraphics,timeLeft));
        doNothing().when(fieldController).purge();
        CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList<>();
        monsterModels.add(Mockito.mock(MonsterModel.class));
        when(fieldModel.getMonsters()).thenReturn(monsterModels);
        doNothing().when(fieldController).updateMonsterPosition(any(),any());
        doNothing().when(fieldController).handleKeyboard();

        BombController bombModel = Mockito.mock(BombController.class);
        when(fieldModel.getBomb()).thenReturn(bombModel);
        int bf = fieldController.getTimerSum();
        fieldController.updateOnTime();
        when(bombModel.getExplosionList()).thenReturn(new ArrayList<>());
        Assert.assertEquals(bf+1,fieldController.getTimerSum());
        verify(fieldModel,times(0)).getBomb();
        verify(bombModel,times(0)).getExplosionList();

        fieldController.updateOnTime();
        verify(fieldModel,times(2)).getBomb();
        verify(bombModel,times(1)).getExplosionList();
        verify(fieldController,times(1)).updateMonsterPosition(any(),any());

        verify(fieldController,times(2)).purge();
        verify(fieldController,times(2)).timeIsUp();
        verify(fieldView,times(2)).draw();

        CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
        com.googlecode.lanterna.input.KeyStroke keyStroke = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        keyStrokes.add(keyStroke);
        fieldController.keyStrokes = keyStrokes;

        fieldController.updateOnTime();
        verify(fieldModel,times(2)).getBomb();
        verify(bombModel,times(1)).getExplosionList();
        verify(fieldController,times(1)).updateMonsterPosition(any(),any());

        verify(fieldController,times(3)).purge();
        verify(fieldController,times(3)).timeIsUp();
        verify(fieldView,times(3)).draw();
        verify(fieldController,times(1)).handleKeyboard();

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