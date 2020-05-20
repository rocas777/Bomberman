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
import com.noclue.model.collectible.NoCollectibleModel;
import com.noclue.model.block.RemovableBlockModel;
import com.noclue.model.character.Character;
import com.noclue.model.character.HeroModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.collectible.CoinModel;
import com.noclue.model.collectible.DoorModel;
import com.noclue.model.difficulty.Easy;
import com.noclue.model.difficulty.Hard;
import com.noclue.model.difficulty.Medium;
import com.noclue.timer.TimeListener;
import com.noclue.timer.Timer;
import com.noclue.view.LivesView;
import com.noclue.view.NoView;
import com.noclue.view.TileView;
import com.noclue.view.block.IndestructibleBlockView;
import com.noclue.view.block.RemovableBlockView;
import com.noclue.view.bomb.BombViewFire;
import com.noclue.view.character.HeroView;
import com.noclue.view.character.MonsterView;
import com.noclue.view.collectible.CoinView;
import com.noclue.view.collectible.DoorView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Math.abs;

public class FieldController implements KeyboardListener, TimeListener, ExplosionListener {
    int timerSum=0;
    FieldModel model;
    IView view;
    IView gameView;
    IView gamoverView;
    IView winView;
    TextGraphics textGraphics;
    TimeLeft timeLeft;
    CopyOnWriteArrayList<KeyStroke> keyStrokes = new CopyOnWriteArrayList<>();
    boolean ended=false;

    public FieldController(FieldModel model, IView gameView, IView gameoverView, IView winView, TextGraphics textGraphics, TimeLeft timeLeft){
        this.model=model;
        this.view = gameView;
        this.gamoverView = gameoverView;
        this.winView = winView;
        this.gameView = gameView;
        this.textGraphics=textGraphics;
        this.timeLeft = timeLeft;
    }

    public void setup() {

        Position hero=setHeroPos();
        Position door=setDoorPos(hero);

        setIndestructibleBlocks();
        setRemovableBlocks(door,hero,150);
        setMonsters(door,hero,10);

        setHero(hero);
        setDoor(door);
    }


    public void setRemovableBlocks(Position door, Position hero, int numberBlocks){
        Random random=new Random();
        for(int i=0;i<numberBlocks;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0) ||(block.getX()<4 && block.getY()<4)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            }

            boolean coin=random.nextBoolean();
            RemovableBlockModel tmp_rm = new RemovableBlockModel((Position) block.clone());
            if(coin) {
                CoinModel tmp_coin = new CoinModel((Position) block.clone());
                TileModel tmp_model = new TileModel(block,tmp_coin,tmp_rm);
                TileView tmp_view = new TileView(tmp_model);
                tmp_view.setCollectible(new CoinView(tmp_coin,textGraphics));
                tmp_view.setFiller(new RemovableBlockView(tmp_rm,textGraphics));

                TileController tileController= new TileController(tmp_model,tmp_view);
                model.getTiles().setTiles(tileController,block);
            }
            else {
                TileModel tmp_model = new TileModel(block,new NoCollectibleModel(),tmp_rm);
                TileView tmp_view = new TileView(tmp_model);
                tmp_view.setFiller(new RemovableBlockView(tmp_rm,textGraphics));

                TileController tileController= new TileController(tmp_model,tmp_view);
                model.getTiles().setTiles(tileController,block);
            }
        }
    }

    public void setIndestructibleBlocks(){
        for(int y=0;y<15;y++){
            model.getTiles().add_collumn();
            for(int x=0;x<23;x++){
                Position p=new Position(23, 15, x, y);
                //System.out.println(door.getX()+" "+door.getY());
                if(x==0 || x==22 || y==0 || y==14) {
                    IndestructibleBlockModel tmp_rm = new IndestructibleBlockModel(p);
                    TileModel tmp_model = new TileModel(p,new NoCollectibleModel(),tmp_rm);
                    TileView tmp_view = new TileView(tmp_model);
                    tmp_view.setFiller(new IndestructibleBlockView(tmp_rm,textGraphics));

                    TileController tileController= new TileController(tmp_model,tmp_view);
                    model.getTiles().addTile(tileController,p);
                }
                else if(y%2==0) {
                    if (x % 2 == 0) {
                        IndestructibleBlockModel tmp_rm = new IndestructibleBlockModel(p);
                        TileModel tmp_model = new TileModel(p,new NoCollectibleModel(),tmp_rm);
                        TileView tmp_view = new TileView(tmp_model);
                        tmp_view.setFiller(new IndestructibleBlockView(tmp_rm,textGraphics));

                        TileController tileController= new TileController(tmp_model,tmp_view);
                        model.getTiles().addTile(tileController,p);
                    }
                    else {
                        NoBlockModel tmp_rm = new NoBlockModel();
                        TileModel tmp_model = new TileModel(p,new NoCollectibleModel(),tmp_rm);
                        TileView tmp_view = new TileView(tmp_model);
                        tmp_view.setFiller(new NoView());

                        TileController tileController= new TileController(tmp_model,tmp_view);
                        model.getTiles().addTile(tileController,p);
                    }
                }
                else {
                    NoBlockModel tmp_rm = new NoBlockModel();
                    TileModel tmp_model = new TileModel(p,new NoCollectibleModel(),tmp_rm);
                    TileView tmp_view = new TileView(tmp_model);
                    tmp_view.setFiller(new NoView());

                    TileController tileController= new TileController(tmp_model,tmp_view);
                    model.getTiles().addTile(tileController,p);
                }
            }
        }
    }

    public void setMonsters(Position door,Position hero,int numberOfMonsters){
        Random random=new Random();
        for(int i=0;i<numberOfMonsters;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);

            float distToHero = abs(hero.getX()-block.getX()) + abs(hero.getY()-block.getY());

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0) || block.equals(door) || distToHero<4) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
                distToHero = abs(hero.getX()-block.getX()) + abs(hero.getY()-block.getY());
            }
            MonsterModel tmp_monster = new MonsterModel(new Hard(), (Position) block.clone());
            TileModel tmp_model = new TileModel(block,new NoCollectibleModel(),tmp_monster);
            TileView tmp_view = new TileView(tmp_model);
            tmp_view.setFiller(new MonsterView(tmp_monster,textGraphics));

            TileController tileController= new TileController(tmp_model,tmp_view);
            model.getTiles().setTiles(tileController,block);

            model.getMonsters().add(tmp_monster);
        }
    }

    public Position setHeroPos(){
        Random random=new Random();
        Position hero=new Position(23,15,1,1);
        while (hero.getX()%2==0 && hero.getY()%2==0) {
            hero = new Position(23, 15, (random.nextInt(23)), (random.nextInt(13)));
        }
        return hero;
    }

    public Position setDoorPos(Position hero){
        Random random=new Random();
        Position door=new Position(23,15,(random.nextInt(12)+10),(random.nextInt(6)+6));
        while ((door.getX()%2==0 && door.getY()%2==0) || door.equals(hero) || door.getX()==0 || door.getY() == 0 || door.getX()==22 || door.getY() == 14){
            door=new Position(23,15,(random.nextInt(23)),(random.nextInt(13)));
        }
        System.out.println(door.getX()+" "+door.getY());
        return door;
    }


    public void setHero(Position position){
        HeroModel tmp_hero = new HeroModel((Position) position.clone());
        TileModel tmp_model = new TileModel(position,new NoCollectibleModel(),tmp_hero);
        TileView tmp_view = new TileView(tmp_model);
        LivesModel livesModel = new LivesModel(3,new Position(146,45,138,2));
        tmp_hero.setLivesModel(livesModel);
        tmp_view.setFiller(new HeroView(tmp_hero,textGraphics));

        TileController tileController= new TileController(tmp_model,tmp_view);
        model.getTiles().setTiles(tileController,position);

        model.setHero(tmp_hero);
    }

    public void setDoor(Position position) {
        if (true){
            position.setY(2);
            position.setX(2);
        }
        RemovableBlockModel tmp_hero = new RemovableBlockModel((Position) position.clone());
        DoorModel doorModel = new DoorModel((Position) position.clone());
        TileModel tmp_model = new TileModel(position,new DoorModel((Position) position.clone()),tmp_hero);
        TileView tmp_view = new TileView(tmp_model);
        tmp_view.setFiller(new RemovableBlockView(tmp_hero,textGraphics));
        tmp_view.setCollectible(new DoorView(doorModel,textGraphics));

        TileController tileController= new TileController(tmp_model,tmp_view);
        model.getTiles().setTiles(tileController,position);
    }

    public void moveLeft(Position position, Character character){
        model.getTiles().getTile(position).moveTile(model.getTiles().getTile(position.getLeft()));
        character.setPosition(position.getLeft());
        position.setLeft();
    }

    public void moveRight(Position position, Character character){
        model.getTiles().getTile(position).moveTile(model.getTiles().getTile(position.getRight()));
        character.setPosition(position.getRight());
        position.setRight();
    }

    public void moveUp(Position position, Character character){
        model.getTiles().getTile(position).moveTile(model.getTiles().getTile(position.getUp()));
        character.setPosition(position.getUp());
        position.setUp();
    }

    public void moveDown(Position position, Character character){
        model.getTiles().getTile(position).moveTile(model.getTiles().getTile(position.getDown()));
        character.setPosition(position.getDown());
        position.setDown();
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if(keyPressed.getCharacter()=='p' && model.getBomb()==null){
            //System.out.println("ENTER");
            BombModel bombModel = new BombModel(1000,this, (Position) model.getHero().getPosition().clone(),model.gettServer());
            BombViewFire viewFire = new BombViewFire(textGraphics,bombModel);
            model.setBombModel(new BombController(bombModel,textGraphics));
            model.gettServer().addListener(model.getBomb());
        }
        else if(!ended){
            keyStrokes.add(keyPressed);
        }


    }

    private void handleKeyboad(){
        KeyStroke keyPressed = keyStrokes.get(keyStrokes.size()-1);
        keyStrokes.clear();
        if(!ended) {
            if(model.getHero().isActive()) {
                try {
                    if (keyPressed.getCharacter() == 'a') {
                        if (model.checkPos(model.getHero().getPosition(), Movement.left)) {
                            moveLeft(model.getHero().getPosition(), (Character) model.getTiles().getTile(model.getHero().getPosition()).getFiller());
                        }
                    } else if (keyPressed.getCharacter() == 'd') {
                        if (model.checkPos(model.getHero().getPosition(), Movement.right)) {
                            moveRight(model.getHero().getPosition(), (Character) model.getTiles().getTile(model.getHero().getPosition()).getFiller());
                        }
                    } else if (keyPressed.getCharacter() == 'w') {
                        if (model.checkPos(model.getHero().getPosition(), Movement.up)) {
                            moveUp(model.getHero().getPosition(), (Character) model.getTiles().getTile(model.getHero().getPosition()).getFiller());
                        }
                    } else if (keyPressed.getCharacter() == 's') {
                        if (model.checkPos(model.getHero().getPosition(), Movement.down)) {
                            moveDown(model.getHero().getPosition(), (Character) model.getTiles().getTile(model.getHero().getPosition()).getFiller());
                        }
                    }
                }
                catch (Exception e){

                }
            }
        }
        if(model.getTiles().getTile(model.getHero().getPosition()).getCollectible() instanceof DoorModel) {
            model.gettServer().removeListener(this);
            view.draw();
            view = winView;
            view.draw();
            ended=true;
        }
        if(model.getTiles().getTile(model.getHero().getPosition()).getCollectible() instanceof CoinModel) {
            model.addPoint();
            model.getTiles().getTile(model.getHero().getPosition()).blankCollectible();
        }
    }

    @Override
    public void updateOnTime() {
        timerSum=timerSum+1;
        if(keyStrokes.size()>0)
            handleKeyboad();

        float wait = (float) 500.0;
        if(model.getBomb()!=null){
            wait = (float) 500;
        }
        if((timerSum%(int)(wait/Timer.getSeconds()))==0) {  //monstros
            for (MonsterModel pos : model.getMonsters()) {
                try {
                    MonsterModel tmp_monsterModel = (MonsterModel) model.getTiles().getTile(pos.getPosition()).getFiller();
                    ArrayList<Position> bomb = null;
                    if (model.getBomb() != null) {
                        bomb = model.getBomb().getExplosionList();
                    }
                    for (Movement m : tmp_monsterModel.nextMove(model.getHero().getPosition(), bomb)) {
                        if (model.checkPos(pos.getPosition(), m)) {
                            Position tmp = pos.getPosition();
                            if (m == Movement.left && !model.getTiles().getTile(tmp.getLeft()).isFilled()) {
                                model.getTiles().getTile(tmp.getLeft()).getFiller().deactivate();
                                if (!(model.getTiles().getTile(tmp.getLeft()).getFiller() instanceof HeroModel))
                                    moveLeft(pos.getPosition(), (MonsterModel) model.getTiles().getTile(pos.getPosition()).getFiller());
                            } else if (m == Movement.right && !model.getTiles().getTile(tmp.getRight()).isFilled()) {
                                model.getTiles().getTile(tmp.getRight()).getFiller().deactivate();
                                if (!(model.getTiles().getTile(tmp.getRight()).getFiller() instanceof HeroModel))
                                    moveRight(pos.getPosition(), (MonsterModel) model.getTiles().getTile(pos.getPosition()).getFiller());
                            } else if (m == Movement.up && !model.getTiles().getTile(tmp.getUp()).isFilled()) {
                                model.getTiles().getTile(tmp.getUp()).getFiller().deactivate();
                                if (!(model.getTiles().getTile(tmp.getUp()).getFiller() instanceof HeroModel))
                                    moveUp(pos.getPosition(), (MonsterModel) model.getTiles().getTile(pos.getPosition()).getFiller());
                            } else if (m == Movement.down && !model.getTiles().getTile(tmp.getDown()).isFilled()) {
                                model.getTiles().getTile(tmp.getDown()).getFiller().deactivate();
                                if (!(model.getTiles().getTile(tmp.getDown()).getFiller() instanceof HeroModel))
                                    moveDown(pos.getPosition(), (MonsterModel) model.getTiles().getTile(pos.getPosition()).getFiller());
                            }
                            break;
                        }
                    }
                }
                catch (Exception e){

                }
            }
        }
        if(timerSum>=1000.0/Timer.getSeconds()){   //relogio
            timeLeft.minusSecond();
            if(timeLeft.getSeconds()==0){
                model.gettServer().removeListener(this);
                view.draw();
                view = gamoverView;
                view.draw();
                ended=true;
                return;
            }
            timerSum=0;
        }
        purge();
        view.draw();
    }

    @Override
    public void explode(ArrayList<Position> positions) {
        ArrayList<Position> tmp = new ArrayList();
        for (int i = 0;i<positions.size();i++){
            if((model.getTiles().getTile(positions.get(i)).getFiller().deactivate())){
                tmp.add(positions.get(i));
                if (i+1<positions.size() && model.getTiles().getTile(positions.get(i+1)).getFiller().deactivate()) {
                    tmp.add(positions.get(i+1));
                }
            }
            i++;
        }
        model.getBomb().getModel().setExplosionList(tmp);
    }

    void purge(){
        for(CopyOnWriteArrayList<TileController> at:model.getTiles().getTiles()){
            for(TileController t:at){
                if(!t.getFiller().isActive()){
                    t.blankTile();
                }
            }
        }
        CopyOnWriteArrayList<MonsterModel> tmp = new CopyOnWriteArrayList<>();
        for(MonsterModel m:model.getMonsters()){
            if (m.isActive())
                tmp.add(m);
        }
        model.setMonsters(tmp);
        if(model.getHero() != null && !model.getHero().isActive()) {
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
