package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.noclue.ExplosionListener;
import com.noclue.IView;
import com.noclue.Movement;
import com.noclue.controller.bomb.BombController;
import com.noclue.keyboard.KeyboardListener;
import com.noclue.model.*;
import com.noclue.model.block.IndestructibleBlockModel;
import com.noclue.model.block.NoBlockModel;
import com.noclue.model.block.RemovableBlockModel;
import com.noclue.model.character.HeroModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.collectible.*;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.timer.TimeListener;
import com.noclue.timer.Timer;
import com.noclue.view.NoView;
import com.noclue.view.TileView;
import com.noclue.view.block.IndestructibleBlockView;
import com.noclue.view.block.RemovableBlockView;
import com.noclue.view.bomb.BombViewFire;
import com.noclue.view.character.HeroView;
import com.noclue.view.character.MonsterView;
import com.noclue.view.collectible.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

public class FieldController implements KeyboardListener, TimeListener, ExplosionListener {
    int timerSum = 0;
    FieldModel model;
    IView view;
    IView gameView;
    IView gamoverView;
    IView winView;
    TextGraphics textGraphics;
    TimeLeft timeLeft;
    CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
    boolean ended = false;


    public FieldController(FieldModel model, IView gameView, IView gameoverView, IView winView, TextGraphics textGraphics, TimeLeft timeLeft) {
        this.model = model;
        this.view = gameView;
        this.gamoverView = gameoverView;
        this.winView = winView;
        this.gameView = gameView;
        this.textGraphics = textGraphics;
        this.timeLeft = timeLeft;
    }

    public void setDifficulty(ArrayList<Difficulty> difficulties) {
        this.model.setDifficulties(difficulties);
    }

    public int getTimerSum() {
        return timerSum;
    }

    public void setTimerSum(int timerSum) {
        this.timerSum = timerSum;
    }

    public FieldModel getModel() {
        return model;
    }

    public void setModel(FieldModel model) {
        this.model = model;
    }

    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public IView getWinView() {
        return winView;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public TimeLeft getTimeLeft() {
        return timeLeft;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public void setup() {

        Position hero = setHeroPos();
        Position door = setDoorPos(hero);

        setIndestructibleBlocks();
        setRemovableBlocks(door, hero, 150);
        setMonsters(door, hero);

        setHero(hero);
        setDoor(door);
    }

    public void createTile(IView collectible, IView filler, TileModel model, Position position) {
        TileView tmp_view = new TileView(model);
        if(collectible != null)
            tmp_view.setCollectible(collectible);
        if(filler != null)
            tmp_view.setFiller(filler);

        TileController tileController = new TileController(model, tmp_view);
        this.model.getTiles().setTiles(tileController, position);
    }

    public void setRemovableBlocks(Position door, Position hero, int numberBlocks) {
        Random random = new Random();
        for (int i = 0; i < numberBlocks; i++) {
            Position position = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);

            while (position.equals(hero) || position.equals(door) || (position.getY() % 2 == 0 && position.getX() % 2 == 0) || (position.getX() < 4 && position.getY() < 4)) {
                position = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);
            }
            RemovableBlockModel tmp_rm = new RemovableBlockModel((Position) position.clone());
            int drop = random.nextInt(21);
            if (drop > 19) {

                Invencible tmp_life = new Invencible((Position) position.clone());
                createTile(new InvencibleView(tmp_life, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_life, tmp_rm), position);
            } else if (drop == 19) {

                AddLife tmp_life = new AddLife((Position) position.clone());
                createTile(new AddLifeView(tmp_life, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_life, tmp_rm), position);
            } else if (drop == 18) {

                AddTime tmp_time = new AddTime((Position) position.clone());
                createTile(new AddTimeView(tmp_time, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_time, tmp_rm), position);

            } else if (drop >= 4) {

                CoinModel tmp_coin = new CoinModel((Position) position.clone());
                createTile(new CoinView(tmp_coin, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_coin, tmp_rm), position);

            } else {
                createTile(null, new RemovableBlockView(tmp_rm, textGraphics), new TileModel(new NoCollectibleModel(), tmp_rm), position);
            }
        }
    }

    public void setIndestructibleBlocks() {
        for (int y = 0; y < 15; y++) {
            model.getTiles().add_collumn();
            for (int x = 0; x < 23; x++) {
                model.getTiles().addTile();
                Position p = new Position(23, 15, x, y);
                if (x == 0 || x == 22 || y == 0 || y == 14 || (y % 2 == 0 && x % 2 == 0)) {
                    IndestructibleBlockModel tmp_rm = new IndestructibleBlockModel(new Position(23, 15, x, y));
                    createTile(new NoView(),new IndestructibleBlockView(tmp_rm,textGraphics),new TileModel(new NoCollectibleModel(),tmp_rm),p);
                } else {
                    NoBlockModel tmp_rm = new NoBlockModel();
                    createTile(new NoView(),new NoView(),new TileModel(new NoCollectibleModel(),tmp_rm),p);
                }
            }
        }
    }

    public void setMonsters(Position door, Position hero) {
        Random random = new Random();
        for (int i = 0; i < model.getDifficulties().size(); i++) {
            Position block = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);

            float distToHero = abs(hero.getX() - block.getX()) + abs(hero.getY() - block.getY());

            while (block.equals(hero) || block.equals(door) || (block.getY() % 2 == 0 && block.getX() % 2 == 0) || block.equals(door) || distToHero < 4) {
                block = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);
                distToHero = abs(hero.getX() - block.getX()) + abs(hero.getY() - block.getY());
            }
            MonsterModel tmp_monster = new MonsterModel(model.getDifficulties().get(i), (Position) block.clone());
            createTile(null,new MonsterView(tmp_monster, textGraphics),new TileModel(new NoCollectibleModel(), tmp_monster),block);

            model.getMonsters().add(tmp_monster);
        }
    }

    public Position setHeroPos() {
        Random random = new Random();
        Position hero = new Position(23, 15, 1, 1);
        while (hero.getX() % 2 == 0 && hero.getY() % 2 == 0) {
            hero = new Position(23, 15, (random.nextInt(23)), (random.nextInt(13)));
        }
        return hero;
    }

    public Position setDoorPos(Position hero) {
        Random random = new Random();
        Position door = new Position(23, 15, (random.nextInt(12) + 10), (random.nextInt(6) + 6));
        while ((door.getX() % 2 == 0 && door.getY() % 2 == 0) || door.equals(hero) || door.getX() == 0 || door.getY() == 0 || door.getX() == 22 || door.getY() == 14) {
            door = new Position(23, 15, (random.nextInt(23)), (random.nextInt(13)));
        }
        System.out.println(door.getX() + " " + door.getY());
        return door;
    }


    public void setHero(Position position) {
        HeroModel modelh = new HeroModel((Position) position.clone());
        HeroController tmp_hero = new HeroController(modelh, null);
        tmp_hero.setLivesModel(new LivesModel(3, new Position(146, 45, 138, 2)));

        createTile(null,new HeroView(modelh, textGraphics),new TileModel(new NoCollectibleModel(), tmp_hero),position);
        model.setHero(tmp_hero);
    }

    public void setDoor(Position position) {
        RemovableBlockModel tmp_hero = new RemovableBlockModel((Position) position.clone());
        DoorModel doorModel = new DoorModel((Position) position.clone());
        createTile(new DoorView(doorModel, textGraphics),new RemovableBlockView(tmp_hero, textGraphics),new TileModel(new DoorModel((Position) position.clone()), tmp_hero),position);
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if (keyPressed.getCharacter() == 'p' && model.getBomb() == null) {
            BombModel bombModel = new BombModel(1000, this, (Position) model.getHero().getPosition().clone(), model.gettServer());
            BombViewFire viewFire = new BombViewFire(textGraphics, bombModel);
            model.setBombModel(new BombController(bombModel, textGraphics));
            model.gettServer().addListener(model.getBomb());
        } else if (!ended) {
            keyStrokes.add(keyPressed);
        }


    }

    private void handleKeyboard() {
        KeyStroke keyPressed = keyStrokes.get(keyStrokes.size() - 1);
        keyStrokes.clear();
        if (!ended) {
            if (model.getHero().isActive()) {
                try {
                    switch (keyPressed.getCharacter()){
                        case 'a':
                            model.getHero().isTouching(model.getTiles().getTile(model.getHero().getPosition().getLeft()).getFiller());
                            if (model.checkPos(model.getHero().getPosition(), Movement.left)) {
                                model.getHero().moveLeft(model.getTiles());
                            }
                            break;
                        case 'd':
                            model.getHero().isTouching(model.getTiles().getTile(model.getHero().getPosition().getRight()).getFiller());
                            if (model.checkPos(model.getHero().getPosition(), Movement.right)) {
                                model.getHero().moveRight(model.getTiles());
                            }
                            break;
                        case 'w':
                            model.getHero().isTouching(model.getTiles().getTile(model.getHero().getPosition().getUp()).getFiller());
                            if (model.checkPos(model.getHero().getPosition(), Movement.up)) {
                                model.getHero().moveUp(model.getTiles());
                            }
                            break;
                        case 's':
                            model.getHero().isTouching(model.getTiles().getTile(model.getHero().getPosition().getDown()).getFiller());
                            if (model.checkPos(model.getHero().getPosition(), Movement.down)) {
                                model.getHero().moveDown(model.getTiles());
                            }
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.getTiles().getTile(model.getHero().getPosition()).getCollectible().visit(this);
    }

    @Override
    public void updateOnTime() {
        timerSum = timerSum + 1;
        if (keyStrokes.size() > 0)
            handleKeyboard();

        float wait = (float) 500.0;
        if (model.getBomb() != null) {
            wait = (float) 500;
        }
        if ((timerSum % (int) (wait / Timer.getSeconds())) == 0) {  //monstros
            for (MonsterModel monster : model.getMonsters()) {
                try {
                    ArrayList<Position> bomb = null;
                    if (model.getBomb() != null) {
                        bomb = model.getBomb().getExplosionList();
                    }
                    for (Movement m : monster.nextMove(model.getHero().getPosition(), bomb)) {
                        if (model.checkPos(monster.getPosition(), m)) {
                            Position tmp = monster.getPosition().getPositionByMovement(m);
                            model.getTiles().getTile(tmp).getFiller().deactivate();
                            if(!model.getHero().getPosition().equals(tmp))
                                switch (m){
                                    case up:
                                        monster.moveUp(model.getTiles());
                                        break;
                                    case down:
                                        monster.moveDown(model.getTiles());
                                        break;
                                    case right:
                                        monster.moveRight(model.getTiles());
                                        break;
                                    case left:
                                        monster.moveLeft(model.getTiles());
                                        break;
                                }
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (timerSum >= 1000.0 / Timer.getSeconds()) {   //relogio
            timeLeft.minusSecond();
            if (timeLeft.getSeconds() == 0) {
                model.gettServer().removeListener(this);
                view.draw();
                view = gamoverView;
                view.draw();
                ended = true;
                return;
            }
            timerSum = 0;
        }
        purge();
        view.draw();
    }

    @Override
    public void explode(ArrayList<Position> positions) {
        ArrayList<Position> tmp = new ArrayList();
        for (int i = 0; i < positions.size(); i++) {
            if ((model.getTiles().getTile(positions.get(i)).getFiller().deactivate())) {
                tmp.add(positions.get(i));
                if (i + 1 < positions.size() && model.getTiles().getTile(positions.get(i + 1)).getFiller().deactivate()) {
                    tmp.add(positions.get(i + 1));
                }
            }
            i++;
        }
        model.getBomb().getModel().setExplosionList(tmp);
    }

    void purge() {
        for (CopyOnWriteArrayList<TileController> at : model.getTiles().getTiles()) {
            for (TileController t : at) {
                if (!t.getFiller().isActive()) {
                    t.blankTile();
                }
            }
        }
        CopyOnWriteArrayList<MonsterModel> tmp = new CopyOnWriteArrayList<>();
        for (MonsterModel m : model.getMonsters()) {
            if (m.isActive())
                tmp.add(m);
        }
        model.setMonsters(tmp);
        if (model.getHero() != null && !model.getHero().isActive()) {
            model.gettServer().removeListener(this);
            view.draw();
            view = gamoverView;
            view.draw();
            ended = true;
        }
    }

    @Override
    public void fireDone() {
        model.setBombModel(null);
    }
}
