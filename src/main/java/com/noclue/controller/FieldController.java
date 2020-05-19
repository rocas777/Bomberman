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
import com.noclue.model.block.NoCollectibleModel;
import com.noclue.model.character.Character;
import com.noclue.model.character.HeroModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.collectible.CoinModel;
import com.noclue.model.collectible.DoorModel;
import com.noclue.model.difficulty.Easy;
import com.noclue.timer.TimeListener;
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

public class FieldController implements KeyboardListener, TimeListener, ExplosionListener {
    int timerSum=0;
    FieldModel model;
    IView view;
    IView gameView;
    IView gamoverView;
    IView winView;
    TextGraphics textGraphics;
    TimeLeft timeLeft;
    LivesModel livesModel;
    boolean ended=false;

    public FieldController(FieldModel model, IView gameView, IView gameoverView, IView winView, TextGraphics textGraphics, LivesModel livesModel, TimeLeft timeLeft){
        this.model=model;
        this.view = gameView;
        this.gamoverView = gameoverView;
        this.winView = winView;
        this.gameView = gameView;
        this.textGraphics=textGraphics;
        this.livesModel = livesModel;
        this.timeLeft = timeLeft;


        model.gettServer().addListener(this);
        model.getkServer().addListener(this);
    }

    public void setup() {

        Position hero=setHeroPos();
        Position door=setDoorPos(hero);

        setIndestructibleBlocks();
        setRemovableBlocks(door,hero,150);
        setMonsters(door,hero,3);

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

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0) || block.equals(door)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            }
            MonsterModel tmp_monster = new MonsterModel(new Easy(), (Position) block.clone());
            TileModel tmp_model = new TileModel(block,new NoCollectibleModel(),tmp_monster);
            TileView tmp_view = new TileView(tmp_model);
            tmp_view.setFiller(new MonsterView(tmp_monster,textGraphics));

            TileController tileController= new TileController(tmp_model,tmp_view);
            model.getTiles().setTiles(tileController,block);

            model.getMonsters().add((Position) block.clone());
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
        while ((door.getX()%2==0 && door.getY()%2==0) || door.equals(hero)){
            door=new Position(23,15,(random.nextInt(23)),(random.nextInt(13)));
        }
        door.setX(3);
        door.setY(3);
        System.out.println(door.getX()+" "+door.getY());
        return door;
    }


    public void setHero(Position position){
        HeroModel tmp_hero = new HeroModel((Position) position.clone());
        TileModel tmp_model = new TileModel(position,new NoCollectibleModel(),tmp_hero);
        TileView tmp_view = new TileView(tmp_model);
        tmp_view.setFiller(new HeroView(tmp_hero,textGraphics));

        TileController tileController= new TileController(tmp_model,tmp_view);
        model.getTiles().setTiles(tileController,position);

        model.setHero_pos(position);
    }

    public void setDoor(Position position){
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

    public boolean checkForHero(Position position, Movement movement){
        if (movement==Movement.left) {
            return (model.getTiles().getTile(position.getLeft()).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.right) {
            return (model.getTiles().getTile(position.getRight()).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.up) {
            return (model.getTiles().getTile(position.getUp()).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.down) {
            return (model.getTiles().getTile(position.getDown()).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.stay) {
            return (model.getTiles().getTile(position).getFiller() instanceof HeroModel);
        }
        return false;
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if(!ended) {
            if (keyPressed.getCharacter() == 'a') {
                if (model.checkPos(model.getHero_pos(), Movement.left)) {
                    moveLeft(model.getHero_pos(), (Character) model.getTiles().getTile(model.getHero_pos()).getFiller());
                }
            } else if (keyPressed.getCharacter() == 'd') {
                if (model.checkPos(model.getHero_pos(), Movement.right)) {
                    moveRight(model.getHero_pos(), (Character) model.getTiles().getTile(model.getHero_pos()).getFiller());
                }
            } else if (keyPressed.getCharacter() == 'w') {
                if (model.checkPos(model.getHero_pos(), Movement.up)) {
                    moveUp(model.getHero_pos(), (Character) model.getTiles().getTile(model.getHero_pos()).getFiller());
                }
            } else if (keyPressed.getCharacter() == 's') {
                if (model.checkPos(model.getHero_pos(), Movement.down)) {
                    moveDown(model.getHero_pos(), (Character) model.getTiles().getTile(model.getHero_pos()).getFiller());
                }
            }
        }
        if(keyPressed.getCharacter()=='p' && model.getBomb()==null){
            //System.out.println("ENTER");
            BombModel bombModel = new BombModel(750,this, (Position) model.getHero_pos().clone(),model.gettServer());
            BombViewFire viewFire = new BombViewFire(textGraphics,bombModel);
            model.setBombModel(new BombController(bombModel,textGraphics));
            model.gettServer().addListener(model.getBomb());
        }
        if(model.getTiles().getTile(model.getHero_pos()).getCollectible() instanceof DoorModel) {
            model.gettServer().removeListener(this);
            view.draw();
            view = winView;
            view.draw();
            ended=true;
        }
        if(model.getTiles().getTile(model.getHero_pos()).getCollectible() instanceof CoinModel) {
            model.addPoint();
            model.getTiles().getTile(model.getHero_pos()).blankCollectible();
        }
        System.out.println(model.getPoints());
    }


    @Override
    public void updateOnTime() {
        timerSum=timerSum+1;
        if((timerSum%25)==0) {  //monstros
            for (Position pos : model.getMonsters()) {
                MonsterModel tmp_monsterModel = (MonsterModel) model.getTiles().getTile(pos).getFiller();
                for (Movement m : tmp_monsterModel.nextMove(pos)) {
                    if (model.checkPos(pos, m)) {
                        if(checkForHero(pos, m)) {
                            model.gettServer().removeListener(this);
                            view.draw();
                            view = gamoverView;
                            view.draw();
                            ended=true;
                            return;
                        }
                        if (m == Movement.left)
                            moveLeft(pos, (MonsterModel) model.getTiles().getTile(pos).getFiller());
                        else if (m == Movement.right)
                            moveRight(pos, (MonsterModel) model.getTiles().getTile(pos).getFiller());
                        else if (m == Movement.up)
                            moveUp(pos, (MonsterModel) model.getTiles().getTile(pos).getFiller());
                        else if (m == Movement.down)
                            moveDown(pos, (MonsterModel) model.getTiles().getTile(pos).getFiller());
                        break;
                    }
                }
            }

        }
        if(timerSum==50){   //relogio
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
        view.draw();
    }

    @Override
    public void explode(Position position) {
        ArrayList<Position> tmp = new ArrayList<>();
        ArrayList<Position> arrayList = new ArrayList();
        arrayList.add(position.getRight());
        arrayList.add(position.getRight().getRight());
        arrayList.add(position.getLeft());
        arrayList.add(position.getLeft().getLeft());
        arrayList.add(position.getDown());
        arrayList.add(position.getDown().getDown());
        arrayList.add(position.getUp());
        arrayList.add(position.getUp().getUp());
        arrayList.add(position);

        for (int i = 0;i<arrayList.size();i++){
            if(!(model.getTiles().getTile(arrayList.get(i)).getFiller() instanceof IndestructibleBlockModel)) {
                remove(arrayList.get(i));
                tmp.add(arrayList.get(i));
                if (i+1<arrayList.size() && !(model.getTiles().getTile(arrayList.get(i+1)).getFiller() instanceof IndestructibleBlockModel)) {
                    remove(arrayList.get(i+1));
                    tmp.add(arrayList.get(i+1));
                }
            }
            i++;
        }
        model.getBomb().getModel().setExplosionList(tmp);
    }

    @Override
    public void fireDone(Position position) {
        model.setBombModel(null);
    }

    private void remove(Position position){
        if(model.getTiles().getTile(position).getFiller() instanceof MonsterModel)
            model.getMonsters().remove(model.getTiles().getTile(position).getFiller());
        else if(model.getTiles().getTile(position).getFiller() instanceof HeroModel) {
            model.gettServer().removeListener(this);
            view.draw();
            view = gamoverView;
            view.draw();
            ended=true;
        }
        model.getTiles().getTile(position).blankTile();
    }
}
