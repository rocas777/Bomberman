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

public class FieldController implements KeyboardListener, TimeListener, ExplosionListener {
    private int timerSum = 0;
    private FieldModel model;
    private IView view;
    private IView gamoverView;
    private IView winView;
    private TextGraphics textGraphics;
    private TimeLeft timeLeft;
    private CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
    private boolean ended = false;


    public FieldController(FieldModel model, IView gameView, IView gameoverView, IView winView, TextGraphics textGraphics, TimeLeft timeLeft) {
        this.model = model;
        this.view = gameView;
        this.gamoverView = gameoverView;
        this.winView = winView;
        this.textGraphics = textGraphics;
        this.timeLeft = timeLeft;
    }

    public void setKeyStrokes(CopyOnWriteArrayList<KeyStroke> keyStrokes) {
        this.keyStrokes = keyStrokes;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
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

    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public IView getWinView() {
        return winView;
    }

    public TimeLeft getTimeLeft() {
        return timeLeft;
    }

    public void setup() {

        setIndestructibleBlocks();

        Position hero = setHeroPos();
        Position door = setDoorPos(hero);
        setRemovableBlocks(door, hero, 110);
        setMonsters(door, hero);

        setHero(hero);
        setDoor(door);
    }

    //creates a tile with a filler, a view and a collectible on a certain position
    public void createTile(IView collectible, IView filler, TileModel model, Position position) {
        TileView tmp_view = new TileView(model);
        if (collectible != null)
            tmp_view.setCollectible(collectible);
        if (filler != null)
            tmp_view.setFiller(filler);

        TileController tileController = new TileController(model, tmp_view);
        this.model.getTiles().setTiles(tileController, position);
    }

    //set numberBlocks number of removable blocks randomly with a random change of having a certain drop
    public void setRemovableBlocks(Position door, Position hero, int numberBlocks) {
        Random random = new Random();
        for (int i = 0; i < numberBlocks; i++) {    //create a removable block
            Position position = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);

            while (position.equals(hero) || position.equals(door) || (position.getX() < 4 && position.getY() < 4) || model.getTiles().getTile(position).getFiller().isFilled()) {
                position = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);
            }
            RemovableBlockModel tmp_rm = new RemovableBlockModel(position.clone());
            int drop = random.nextInt(21);  //generate random number to assign a collectible
            if (drop > 19) {    // invincible
                Invencible tmp_life = new Invencible(position.clone());
                createTile(new InvencibleView(tmp_life, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_life, tmp_rm), position);
            } else if (drop == 19) {    //life
                AddLife tmp_life = new AddLife(position.clone());
                createTile(new AddLifeView(tmp_life, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_life, tmp_rm), position);
            } else if (drop == 18) {    //time
                AddTime tmp_time = new AddTime(position.clone());
                createTile(new AddTimeView(tmp_time, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_time, tmp_rm), position);
            } else if (drop >= 4) { //coin
                CoinModel tmp_coin = new CoinModel(position.clone());
                createTile(new CoinView(tmp_coin, textGraphics), new RemovableBlockView(tmp_rm, textGraphics), new TileModel(tmp_coin, tmp_rm), position);
            } else {    //no collectible
                createTile(null, new RemovableBlockView(tmp_rm, textGraphics), new TileModel(new NoCollectibleModel(), tmp_rm), position);
            }
        }
    }

    //from tile (2,2) to tile(22,14) jumping a position horinzontally and vertically everytime create an indestructible block
    public void setIndestructibleBlocks() {
        for (int y = 0; y < 15; y++) {
            model.getTiles().add_column();
            for (int x = 0; x < 23; x++) {
                model.getTiles().addTile();
                Position p = new Position(23, 15, x, y);
                if (x == 0 || x == 22 || y == 0 || y == 14 || (y % 2 == 0 && x % 2 == 0)) {
                    IndestructibleBlockModel tmp_rm = new IndestructibleBlockModel(new Position(23, 15, x, y));
                    createTile(new NoView(), new IndestructibleBlockView(tmp_rm, textGraphics), new TileModel(new NoCollectibleModel(), tmp_rm), p);
                } else {
                    NoBlockModel tmp_rm = new NoBlockModel();
                    createTile(new NoView(), new NoView(), new TileModel(new NoCollectibleModel(), tmp_rm), p);
                }
            }
        }
    }

    //create monsters according to the difficulty on the fieldmodel and put in random places on the map
    public void setMonsters(Position door, Position hero) {
        Random random = new Random();
        for (int i = 0; i < model.getDifficulties().size(); i++) {
            Position block = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);

            float distToHero = abs(hero.getX() - block.getX()) + abs(hero.getY() - block.getY());

            //if the monster is close to the hero or is on the door, generate another random position
            while (block.equals(hero) || block.equals(door) || model.getTiles().getTile(block).getFiller().isFilled() || distToHero < 4) {
                block = new Position(23, 15, random.nextInt(21) + 1, random.nextInt(13) + 1);
                distToHero = abs(hero.getX() - block.getX()) + abs(hero.getY() - block.getY());
            }
            MonsterModel tmp_monster = new MonsterModel(model.getDifficulties().get(i), block.clone());
            createTile(null, new MonsterView(tmp_monster, textGraphics), new TileModel(new NoCollectibleModel(), tmp_monster), block);

            model.getMonsters().add(tmp_monster);
        }
    }

    public Position setHeroPos() {
        Position hero = new Position(23, 15, 1, 1);
        return hero;
    }

    public Position setDoorPos(Position hero) {
        Random random = new Random();
        Position door = new Position(23, 15, (random.nextInt(12) + 10), (random.nextInt(6) + 6));
        while (door.equals(hero) || model.getTiles().getTile(door).getFiller().isFilled()) {
            door = new Position(23, 15, (random.nextInt(23)), (random.nextInt(13)));
        }
        return door;
    }


    public void setHero(Position position) {    //creates hero on top left
        HeroModel modelh = new HeroModel(position.clone());
        HeroController tmp_hero = new HeroController(modelh, null);
        tmp_hero.setLivesModel(new LivesModel(3, new Position(146, 45, 138, 2)));
        createTile(null, new HeroView(modelh, textGraphics), new TileModel(new NoCollectibleModel(), tmp_hero), position);
        model.setHero(tmp_hero);
    }

    public void setDoor(Position position) {
        RemovableBlockModel tmp_hero = new RemovableBlockModel(position.clone());
        DoorModel doorModel = new DoorModel(position.clone());
        createTile(new DoorView(doorModel, textGraphics), new RemovableBlockView(tmp_hero, textGraphics), new TileModel(new DoorModel(position.clone()), tmp_hero), position);
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if (keyPressed.getCharacter() == 'p' && model.getBomb() == null) {  //creates a bomb if there isn't one already
            BombModel bombModel = new BombModel(1000, this, model.getHero().getPosition().clone(), model.gettServer());
            model.setBombModel(new BombController(bombModel, textGraphics));
            model.gettServer().addListener(model.getBomb());
        } else if (!ended) {
            keyStrokes.add(keyPressed);
        }


    }

    public void handleKeyboard() {
        KeyStroke keyPressed = keyStrokes.get(keyStrokes.size() - 1);   //only the last input is processed
        keyStrokes.clear();
        if (!ended) {
            if (model.getHero().isActive()) {
                try {
                    switch (keyPressed.getCharacter()) {
                        // for every case checks if movement is possible before doing it
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
        //obtain and visit the collectible in the new hero's position
        model.getTiles().getTile(model.getHero().getPosition()).getCollectible().visit(this);
    }

    @Override
    public void updateOnTime() {
        timerSum = timerSum + 1;
        if (keyStrokes.size() > 0)
            handleKeyboard();   //move hero

        float wait = (float) 500.0;
        if ((timerSum % (int) (wait / Timer.getSeconds())) == 0) {  //monsters
            ArrayList<Position> bomb = null;
            if (model.getBomb() != null) {
                bomb = model.getBomb().getExplosionList();
            }
            for (MonsterModel monster : model.getMonsters()) {  //move monsters
                updateMonsterPosition(monster, bomb);
            }
        }
        if (timeIsUp())
            return;
        purge();    //update activated/deactivated information before drawing
        view.draw();
    }

    public void updateMonsterPosition(MonsterModel monster, ArrayList<Position> bomb) {
        for (Movement m : monster.nextMove(model.getHero().getPosition(), bomb)) {
            if (model.checkPos(monster.getPosition(), m)) {
                Position tmp = monster.getPosition().getPositionByMovement(m);
                model.getTiles().getTile(tmp).getFiller().deactivate();     //interaction
                if (!model.getHero().getPosition().equals(tmp)) //moves monster if the hero isn't on the target tile
                    switch (m) {
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
    }

    public boolean timeIsUp() {
        if (timerSum >= 1000.0 / Timer.getSeconds()) {   //game clock
            timeLeft.minusSecond();
            if (timeLeft.getSeconds() == 0) {   //if it reaches 0 the game ends and player loses
                model.gettServer().removeListener(this);
                view = gamoverView;
                view.draw();
                ended = true;
                return true;
            }
            timerSum = 0;
        }
        return false;
    }

    @Override
    public void explode(ArrayList<Position> positions) {    //handle bomb explosion
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
        //every deactivated block is set to be a blank tile
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

        //end game if hero reaches 0 lifes and is deactivated
        if (model.getHero() != null && !model.getHero().isActive()) {
            model.gettServer().removeListener(this);
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
