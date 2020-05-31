package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.noclue.IBombInterface;
import com.noclue.IView;
import com.noclue.Movement;
import com.noclue.controller.bomb.BombController;
import com.noclue.model.*;
import com.noclue.model.block.RemovableBlockModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.collectible.Collectible;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.timer.Timer;
import com.noclue.view.NoView;
import com.noclue.view.block.IndestructibleBlockView;
import com.noclue.view.block.RemovableBlockView;
import com.noclue.view.character.HeroView;
import com.noclue.view.collectible.DoorView;
import com.noclue.view.field.FieldView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Math.abs;
import static org.mockito.Mockito.*;

public class FieldControllerTest {
    FieldModel fieldModel ;
    IView iView ;
    TextGraphics textGraphics;
    TimeLeft timeLeft ;
    FieldController fieldController ;
    Grid grid;

    @Before
    public void setUp() {
        fieldModel = spy(new FieldModel(100,100,1));
        iView = Mockito.mock(FieldView.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        timeLeft = Mockito.mock(TimeLeft.class);
        fieldController = spy(new FieldController(fieldModel,iView,iView,iView,textGraphics,timeLeft));
        grid = new Grid();
        when(fieldModel.getTiles()).thenReturn(grid);
    }

    @Test
    public void createTile() {
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
        for(int i = 0;i<20;i++) {
            setUp();

            fieldController.setIndestructibleBlocks();
            Position positionh = new Position(23, 15, 1, 1);
            fieldController.setRemovableBlocks(positionh, new Position(23, 15, 2, 1), 140);
            verify(fieldController, times(140)).createTile(any(), any(RemovableBlockView.class), any(TileModel.class), any(Position.class));

            int u = 0;

            for (int y = 0; y < grid.getTiles().size(); y++) {
                for (int x = 0; x < grid.getTiles().get(y).size(); x++) {
                    if (grid.getTiles().get(y).get(x).getFiller() instanceof RemovableBlockModel) {
                        u++;
                        Assert.assertTrue(x >= 4 || y >= 4);
                    }
                }
            }
            Assert.assertEquals(140, u);
        }

    }

    @Test
    public void setIndestructibleBlocks() {
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
        for(int i = 0;i<30;i++) {
            setUp();
            fieldController.setIndestructibleBlocks();

            Random random = new Random();
            Position positionh = new Position(23, 15, 1, 1);
            Position door = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);
            ArrayList<Difficulty> difficulties = new ArrayList<>();
            CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList<>();
            for(int d=0;d<30;d++)
                difficulties.add(Mockito.mock(Difficulty.class));

            fieldController.setDifficulty(difficulties);
            when(fieldModel.getDifficulties()).thenReturn(difficulties);
            when(fieldModel.getMonsters()).thenReturn(monsterModels);
            doNothing().when(fieldController).createTile(any(), any(), any(), any());

            fieldController.setMonsters(door, positionh);
            for (MonsterModel m : monsterModels) {
                Assert.assertNotEquals(positionh, m.getPosition());
                Assert.assertNotEquals(door, m.getPosition());
                Assert.assertTrue(abs(positionh.getX() - m.getPosition().getX()) + abs(positionh.getY() - m.getPosition().getY()) >= 4);
            }
            Assert.assertEquals(monsterModels.size(), difficulties.size());
        }

    }

    @Test
    public void setHeroPos() {
        Position position = new Position(23,15,1,1);
        Position positionh = fieldController.setHeroPos();
        Assert.assertEquals(position,positionh);

    }

    @Test
    public void setDoorPos() {
        for(int i = 0;i<20;i++) {
            setUp();
            fieldController.setIndestructibleBlocks();
            Position position = new Position(15, 15, 5, 5);
            Assert.assertNotEquals(position, fieldController.setDoorPos(position));
        }
    }

    @Test
    public void setHero() {
        doNothing().when(fieldController).createTile(any(),any(),any(),any());
        Position position = Mockito.mock(Position.class);

        fieldController.setHero(position);
        verify(fieldController,times(1)).createTile(any(),any(HeroView.class),any(TileModel.class),any(Position.class));
        verify(fieldModel,times(1)).setHero(any(HeroController.class));
    }

    @Test
    public void setDoor() {

        for(int i = 0;i<20;i++) {
            setUp();
            doNothing().when(fieldController).createTile(any(), any(), any(), any());

            Position position = Mockito.mock(Position.class);
            fieldController.setDoor(position);
            verify(fieldController, times(1)).createTile(any(DoorView.class), any(RemovableBlockView.class), any(TileModel.class), any(Position.class));
        }
    }

    @Test
    public void updateOnKeyboard() {
        HeroController heroController = Mockito.mock(HeroController.class);
        Timer timer = Mockito.mock(Timer.class);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);

        when(fieldController.getModel().getBomb()).thenReturn(null);
        when(heroController.getPosition()).thenReturn(new Position(10,10,5,5));
        when(fieldController.getModel().getHero()).thenReturn(heroController);
        when(fieldController.getModel().gettServer()).thenReturn(timer);
        when(keyStroke.getCharacter()).thenReturn('p');

        //verificar drop bomb
        fieldController.updateOnKeyboard(keyStroke);
        verify(fieldModel,times(1)).setBombModel(any(BombController.class));
        verify(timer,times(1)).addListener(null);

        //verificar moveleft
        when(keyStroke.getCharacter()).thenReturn('a');
        CopyOnWriteArrayList<KeyStroke> keyStrokes = Mockito.mock(CopyOnWriteArrayList.class);
        fieldController.setKeyStrokes(keyStrokes);
        fieldController.updateOnKeyboard(keyStroke);
        verify(keyStrokes,times(1)).add(keyStroke);

        //verificar fim do jogo
        fieldController.setEnded(true);
        fieldController.updateOnKeyboard(keyStroke);
        verify(fieldModel,times(1)).setBombModel(any(BombController.class));
        verify(timer,times(1)).addListener(null);
        verify(keyStrokes,times(1)).add(keyStroke);

    }


    @Test
    public void handleKeyboard() {
        HeroController heroController = Mockito.mock(HeroController.class);
        TileController controller = Mockito.mock(TileController.class);
        Collectible collectible = Mockito.mock(Collectible.class);
        Filler filler = Mockito.mock(Filler.class);
        CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
        grid = Mockito.mock(Grid.class);

        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        keyStrokes.add(keyStroke);
        fieldController.setKeyStrokes(keyStrokes);

        when(controller.getFiller()).thenReturn(filler);
        when(keyStroke.getCharacter()).thenReturn('a');
        when(controller.getCollectible()).thenReturn(collectible);
        when(grid.getTile(any(Position.class))).thenReturn(controller);
        when(heroController.getPosition()).thenReturn(new Position(10,10,5,5));
        when(fieldModel.getTiles()).thenReturn(grid);
        when(fieldModel.getHero()).thenReturn(heroController);
        when(fieldModel.checkPos(any(),any())).thenReturn(true);


        //verificar fim do jogo
        fieldController.setEnded(true);
        when(heroController.isActive()).thenReturn(false);
        fieldController.handleKeyboard();

        Assert.assertEquals(0,keyStrokes.size());
        keyStrokes.add(keyStroke);

        verify(heroController,times(0)).isTouching(any());
        verify(heroController,times(0)).moveLeft(any());

        //verificar esquerda se o movimento for proibido
        fieldController.setEnded(false);
        when(heroController.isActive()).thenReturn(false);
        fieldController.handleKeyboard();

        verify(heroController,times(0)).isTouching(any());
        verify(heroController,times(0)).moveLeft(any());

        Assert.assertEquals(0,keyStrokes.size());
        keyStrokes.add(keyStroke);

        //verificar esquerda se o movimento for permitido
        fieldController.setEnded(false);
        when(heroController.isActive()).thenReturn(true);
        fieldController.handleKeyboard();

        verify(heroController,times(1)).isTouching(any());
        verify(heroController,times(1)).moveLeft(any());
        keyStrokes.add(keyStroke);

        //verificar baixo se o movimento for permitido
        when(keyStroke.getCharacter()).thenReturn('s');
        fieldController.handleKeyboard();

        verify(heroController,times(2)).isTouching(any());
        verify(heroController,times(1)).moveDown(any());
        keyStrokes.add(keyStroke);

        //verificar cima se o movimento for permitido
        when(keyStroke.getCharacter()).thenReturn('w');
        fieldController.handleKeyboard();

        verify(heroController,times(3)).isTouching(any());
        verify(heroController,times(1)).moveUp(any());
        keyStrokes.add(keyStroke);

        //verificar direita se o movimento for permitido
        when(keyStroke.getCharacter()).thenReturn('d');
        fieldController.handleKeyboard();

        verify(heroController,times(4)).isTouching(any());
        verify(heroController,times(1)).moveRight(any());

        //verificar se o numero de visitas a drops coincide com o numero de teclas premidas
        verify(collectible,times(6)).visit(any(FieldController.class));



    }

    @Test
    public void updateOnTime() {
        Timer.setSeconds(250);


        CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList<>();
        monsterModels.add(Mockito.mock(MonsterModel.class));
        BombController bombModel = Mockito.mock(BombController.class);
        int bf = fieldController.getTimerSum();
        CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        keyStrokes.add(keyStroke);

        when(fieldModel.getMonsters()).thenReturn(monsterModels);
        when(fieldModel.getBomb()).thenReturn(bombModel);
        when(bombModel.getExplosionList()).thenReturn(new ArrayList<>());

        doNothing().when(fieldController).updateMonsterPosition(any(),any());
        doNothing().when(fieldController).handleKeyboard();
        doNothing().when(fieldController).purge();

        //verificar se o somador funciona
        fieldController.updateOnTime();
        Assert.assertEquals(bf+1,fieldController.getTimerSum());
        verify(fieldModel,times(1)).getBomb();
        verify(bombModel,times(0)).getExplosionList();

        //verificar se a bomba explodiu passados os 500ms
        fieldController.updateOnTime();
        verify(fieldModel,times(3)).getBomb();
        verify(bombModel,times(1)).getExplosionList();
        verify(fieldController,times(1)).updateMonsterPosition(any(),any());
        verify(fieldController,times(2)).purge();
        verify(fieldController,times(2)).timeIsUp();
        verify(iView,times(2)).draw();


        //verificar se, havendo teclas premidas, elas são tratadas
        fieldController.setKeyStrokes(keyStrokes);
        fieldController.updateOnTime();
        verify(fieldModel,times(3)).getBomb();
        verify(bombModel,times(1)).getExplosionList();
        verify(fieldController,times(1)).updateMonsterPosition(any(),any());
        verify(fieldController,times(3)).purge();
        verify(fieldController,times(3)).timeIsUp();
        verify(iView,times(3)).draw();
        verify(fieldController,times(1)).handleKeyboard();

    }

    private CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> setTiles(){
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();

        tiles.add(new CopyOnWriteArrayList<>());
        tiles.add(new CopyOnWriteArrayList<>());
        tiles.add(new CopyOnWriteArrayList<>());

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

        return tiles;
    }

    @Test
    public void updateMonsterPosition() {
        HeroController heroController = Mockito.mock(HeroController.class);
        Position p = new Position(10,10,1,1);

        when(fieldModel.getTiles()).thenReturn(grid);
        doReturn(false).when(fieldModel).checkPos(any(),any());

        //inicializar a grid
        grid.setTiles(setTiles());

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

        when(fieldModel.getHero()).thenReturn(heroController);
        when(heroController.getPosition()).thenReturn(new Position(10,10,5,5));
        when(fieldModel.checkPos(p,ml)).thenReturn(false);
        when(fieldModel.checkPos(p,mr)).thenReturn(false);
        when(fieldModel.checkPos(p,mu)).thenReturn(false);
        when(fieldModel.checkPos(p,md)).thenReturn(false);
        when(fieldModel.checkPos(p,ms)).thenReturn(false);

        MonsterModel monsterModel = Mockito.mock(MonsterModel.class);
        when(monsterModel.nextMove(heroController.getPosition(),null)).thenReturn(movements);
        when(monsterModel.getPosition()).thenReturn(p);

        //update com caminho bloqueados
        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel,times(0))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(0))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(0))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);


        //updtae com esquerda livre
        when(fieldModel.checkPos(p,ml)).thenReturn(true);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(0))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(0))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);

        //update com direita livre
        when(fieldModel.checkPos(p,ml)).thenReturn(false);
        when(fieldModel.checkPos(p,mr)).thenReturn(true);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(0))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);

        //update com cima livre
        when(fieldModel.checkPos(p,mr)).thenReturn(false);
        when(fieldModel.checkPos(p,mu)).thenReturn(true);

        fieldController.updateMonsterPosition(monsterModel,null);

        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(1))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(0))
                .moveDown(grid);

        //update com baixo livre
        when(fieldModel.checkPos(p,mu)).thenReturn(false);
        when(fieldModel.checkPos(p,md)).thenReturn(true);

        fieldController.updateMonsterPosition(monsterModel,null);
        Mockito.verify(monsterModel, times(1))
                .moveLeft(grid);
        Mockito.verify(monsterModel,times(1))
                .moveRight(grid);
        Mockito.verify(monsterModel,times(1))
                .moveUp(grid);
        Mockito.verify(monsterModel,times(1))
                .moveDown(grid);


        //update com baixo livre mas com heroi nessa posição
        when(heroController.getPosition()).thenReturn(new Position(10,10,1,2));
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
        Timer timer = Mockito.mock(Timer.class);
        Timer.setSeconds(20);
        when(fieldModel.gettServer()).thenReturn(timer);

        //verificar se timeIsUp é falso quando o somador é 0
        fieldController.setTimerSum(0);
        Assert.assertFalse(fieldController.timeIsUp());
        Assert.assertFalse(fieldController.isEnded());

        //verificar se timeIsUp é verdadeiro quando o somador está no limite
        fieldController.setEnded(false);
        fieldController.setTimerSum(50);
        Assert.assertTrue(fieldController.timeIsUp());

        //verificar se timeIsUp é verdadeiro quando o somador está acima
        fieldController.setEnded(false);
        fieldController.setTimerSum(51);
        Assert.assertTrue(fieldController.timeIsUp());

        //verificar a correta chamada das funções pretendidas
        Mockito.verify(iView,times(2)).draw();
        Mockito.verify(timer,times(2)).removeListener(fieldController);
        Assert.assertTrue(fieldController.isEnded());
        Mockito.verify(timeLeft,times(2)).minusSecond();

        when(timeLeft.getSeconds()).thenReturn(1);
        fieldController.timeIsUp();
        Assert.assertEquals(0,fieldController.getTimerSum());

    }

    @Test
    public void explode() {
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

        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();
        tiles.add(new CopyOnWriteArrayList<>());
        tiles.get(tiles.size()-1).add(t1);
        tiles.get(tiles.size()-1).add(t2);
        tiles.get(tiles.size()-1).add(t3);
        tiles.get(tiles.size()-1).add(t4);
        tiles.get(tiles.size()-1).add(t5);

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
        grid = new Grid();
        grid.setTiles(tiles);

        when(fieldModel.getTiles()).thenReturn(grid);
        IBombInterface iBombInterface = Mockito.mock(IBombInterface.class);
        BombModel bombModel = Mockito.mock(BombModel.class);
        ArrayList a = new ArrayList<Position>();
        a.add(position);
        fieldModel.setBombModel(iBombInterface);
        when(iBombInterface.getModel()).thenReturn(bombModel);

        // verificar explosão impedida na esquerda
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

        //verificar explosão desimpedida
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

        //verificar explosão impedida em todas a sdireções
        when(t1.getFiller().deactivate()).thenReturn(false);
        when(t2.getFiller().deactivate()).thenReturn(false);
        when(t3.getFiller().deactivate()).thenReturn(false);
        when(t4.getFiller().deactivate()).thenReturn(false);
        when(t5.getFiller().deactivate()).thenReturn(false);

        fieldController.explode(positions);
        a.clear();
        Mockito.verify(bombModel,times(1))
                .setExplosionList(a);

        //verificar explosão impedida em todas as direções mas com blocos livres a um bloco de distância(deve comportar-se como a explosão impedidad normal)
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
    /*
    public void purge() {
        TileController tileController = Mockito.mock(TileController.class);
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();
        tiles.add(new CopyOnWriteArrayList<>());
        Filler filler = Mockito.mock(Filler.class);
        grid = Mockito.mock(Grid.class);
        CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList();
        Timer timer = Mockito.mock(Timer.class);
        HeroController heroController = Mockito.mock(HeroController.class);
        when(fieldModel.getTiles()).thenReturn(grid);

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
        when(m1.isActive()).thenReturn(false);
        when(m2.isActive()).thenReturn(true);
        when(m3.isActive()).thenReturn(false);
        when(m4.isActive()).thenReturn(false);
        when(m5.isActive()).thenReturn(false);
        when(grid.getTiles()).thenReturn(tiles);
        fieldModel.setTiles(grid);
        fieldModel.setMonsters(monsterModels);
        tiles.get(tiles.size()-1).add(tileController);

        //purge evry monster but the second
        fieldController.purge();
        Assert.assertEquals(1,fieldModel.getMonsters().size());
        Assert.assertEquals(m2,fieldModel.getMonsters().get(0));

        //purge every monster
        when(m2.isActive()).thenReturn(false);
        fieldController.purge();
        Assert.assertEquals(0,fieldModel.getMonsters().size());
        try {
            Assert.assertEquals(null,fieldModel.getMonsters().get(0));
            Assert.fail();
        }
        catch (Exception e){
        }
        Mockito.verify(tileController,times(2)).blankTile();

        //purge hero
        when(heroController.isActive()).thenReturn(false);
        fieldModel.settServer(timer);
        fieldModel.setHero(heroController);
        fieldController.purge();
        Mockito.verify(iView,times(2)).draw();
        Assert.assertEquals(true,fieldController.ended);

        //end game
        fieldController.setEnded(false);
        when(heroController.isActive()).thenReturn(true);
        fieldModel.settServer(timer);
        fieldModel.setHero(heroController);
        fieldController.purge();
        Mockito.verify(iView,times(2)).draw();
        Mockito.verify(timer,times(1)).removeListener(fieldController);
        Assert.assertEquals(false,fieldController.ended);
    }*/
    @Test
    public void purge() {
        TileController tileController = Mockito.mock(TileController.class);
        CopyOnWriteArrayList<CopyOnWriteArrayList<TileController>> tiles = new CopyOnWriteArrayList<>();
        tiles.add(new CopyOnWriteArrayList<>());
        Filler filler = Mockito.mock(Filler.class);
        grid = Mockito.mock(Grid.class);
        CopyOnWriteArrayList<MonsterModel> monsterModels = new CopyOnWriteArrayList();
        Timer timer = Mockito.mock(Timer.class);
        HeroController heroController = Mockito.mock(HeroController.class);
        when(fieldModel.getTiles()).thenReturn(grid);

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
        when(m1.isActive()).thenReturn(false);
        when(m2.isActive()).thenReturn(true);
        when(m3.isActive()).thenReturn(false);
        when(m4.isActive()).thenReturn(false);
        when(m5.isActive()).thenReturn(false);
        when(grid.getTiles()).thenReturn(tiles);
        fieldModel.setTiles(grid);
        fieldModel.setMonsters(monsterModels);
        tiles.get(tiles.size()-1).add(tileController);

        //purge evry monster but the second
        fieldController.purge();
        Assert.assertEquals(1,fieldModel.getMonsters().size());
        Assert.assertEquals(m2,fieldModel.getMonsters().get(0));

        //purge every monster
        when(m2.isActive()).thenReturn(false);
        fieldController.purge();
        Assert.assertEquals(0,fieldModel.getMonsters().size());
        try {
            Assert.assertEquals(null,fieldModel.getMonsters().get(0));
            Assert.fail();
        }
        catch (Exception e){
        }
        Mockito.verify(tileController,times(2)).blankTile();

        //purge hero
        when(heroController.isActive()).thenReturn(false);
        fieldModel.settServer(timer);
        fieldModel.setHero(heroController);
        fieldController.purge();
        Mockito.verify(iView,times(1)).draw();
        Assert.assertEquals(true,fieldController.isEnded());

        //end game
        fieldController.setEnded(false);
        when(heroController.isActive()).thenReturn(true);
        fieldModel.settServer(timer);
        fieldModel.setHero(heroController);
        fieldController.purge();
        Mockito.verify(iView,times(1)).draw();
        Mockito.verify(timer,times(1)).removeListener(fieldController);
        Assert.assertEquals(false,fieldController.isEnded());
    }

    @Test
    public void fireDone() {

        IBombInterface iBombInterface = Mockito.mock(IBombInterface.class);
        fieldModel.setBombModel(iBombInterface);

        Assert.assertEquals(iBombInterface,fieldModel.getBomb());
        fieldController.fireDone();
        Assert.assertEquals(null,fieldModel.getBomb());
    }


}