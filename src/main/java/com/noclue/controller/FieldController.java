package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.noclue.ExplosionListener;
import com.noclue.IView;
import com.noclue.model.FieldModel;
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
import com.noclue.view.NoView;
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
    boolean ended=false;

    public FieldController(FieldModel model, IView gameView, IView gameoverView, IView winView, TextGraphics textGraphics){
        this.model=model;
        this.view = gameView;
        this.gamoverView = gameoverView;
        this.winView = winView;
        this.gameView = gameView;
        this.textGraphics=textGraphics;

        model.gettServer().addListener(this);
        model.getkServer().addListener(this);
        //setup();
    }

    private void setRemovableBlocks(Position door, Position hero, int numberBlocks){
        Random random=new Random();
        for(int i=0;i<numberBlocks;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0) ||(block.getX()<4 && block.getY()<4)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            }

            boolean coin=random.nextBoolean();
            RemovableBlockModel tmp_rm = new RemovableBlockModel((Position) block.clone());
            if(false) {
                CoinModel tmp_coin = new CoinModel((Position) block.clone());
                model.getViews().get(block.getY()).set(block.getX(),(new CoinView(tmp_coin,textGraphics)));
                model.getViews().get(block.getY()).set(block.getX(),(new RemovableBlockView(tmp_rm,textGraphics)));

                model.getTiles().get(block.getY()).set(block.getX(),new Tile(block,tmp_coin,tmp_rm));
            }
            else {
                NoCollectibleModel tmp_no = new NoCollectibleModel();
                model.getViews().get(block.getY()).set(block.getX(),(new RemovableBlockView(tmp_rm,textGraphics)));

                model.getTiles().get(block.getY()).set(block.getX(),new Tile(block, tmp_no, tmp_rm));
            }
        }
    }

    private void setIndestructibleBlocks(){
        for(int y=0;y<15;y++){
            model.getTiles().add(new CopyOnWriteArrayList<>());
            model.getViews().add(new CopyOnWriteArrayList<>());
            for(int x=0;x<23;x++){
                Position p=new Position(23, 15, x, y);
                //System.out.println(door.getX()+" "+door.getY());
                if(x==0 || x==22 || y==0 || y==14) {
                    IndestructibleBlockModel tmp_ind = new IndestructibleBlockModel((Position) p.clone());
                    model.getViews().get(p.getY()).add((new IndestructibleBlockView(tmp_ind,textGraphics)));
                    model.getTiles().get(y).add(new Tile(p, new NoCollectibleModel(), tmp_ind));
                }
                else if(y%2==0) {
                    if (x % 2 == 0) {
                        IndestructibleBlockModel tmp_ind = new IndestructibleBlockModel((Position) p.clone());
                        model.getViews().get(p.getY()).add((new IndestructibleBlockView(tmp_ind,textGraphics)));
                        model.getTiles().get(y).add(new Tile(p,new NoCollectibleModel(), tmp_ind));
                    }
                    else {
                        model.getViews().get(p.getY()).add((new NoView()));
                        model.getTiles().get(y).add(new Tile(p, new NoCollectibleModel(), new NoBlockModel()));
                    }
                }
                else {
                    model.getViews().get(p.getY()).add((new NoView()));
                    model.getTiles().get(y).add(new Tile(p, new NoCollectibleModel(), new NoBlockModel()));
                }
            }
        }
    }

    private void setMonsters(Position door,Position hero,int numberOfMonsters){
        Random random=new Random();
        for(int i=0;i<numberOfMonsters;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0) || block.equals(door)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            }
            MonsterModel tmp_monster = new MonsterModel(new Easy(), (Position) block.clone());
            model.getViews().get(block.getY()).set(block.getX(),(new MonsterView(tmp_monster,textGraphics)));

            model.getTiles().get(block.getY()).get(block.getX()).setFiller(tmp_monster);
            model.getTiles().get(block.getY()).get(block.getX()).setCollectible(new NoCollectibleModel());
            model.getMonsters().add((Position) block.clone());
        }
    }

    private Position setHeroPos(){
        Random random=new Random();
        Position hero=new Position(23,15,1,1);
        while (hero.getX()%2==0 && hero.getY()%2==0) {
            hero = new Position(23, 15, (random.nextInt(23)), (random.nextInt(13)));
        }
        return hero;
    }

    private Position setDoorPos(Position hero){
        Random random=new Random();
        Position door=new Position(23,15,(random.nextInt(12)+10),(random.nextInt(6)+6));
        while ((door.getX()%2==0 && door.getY()%2==0) || door.equals(hero)){
            door=new Position(23,15,(random.nextInt(23)),(random.nextInt(13)));
        }
        System.out.println(door.getX()+" "+door.getY());
        return door;
    }


    private void setHero(Position position){
        HeroModel tmp_hero = new HeroModel((Position) position.clone());
        model.getTiles().get(position.getY()).get(position.getX()).setFiller(tmp_hero);
        model.getTiles().get(position.getY()).get(position.getX()).setCollectible(new NoCollectibleModel());

        model.getViews().get(position.getY()).set(position.getX(),(new HeroView(tmp_hero,textGraphics)));

        model.setHero_pos(position);
    }

    private void setDoor(Position position){
        model.getTiles().get(position.getY()).set(position.getX(),new Tile(position, new DoorModel((Position) position.clone()),new RemovableBlockModel(position)));
        model.getViews().get(position.getY()).set(position.getX(),new RemovableBlockView((RemovableBlockModel) model.getTiles().get(position.getY()).get(position.getX()).getFiller(),textGraphics));
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

    private void moveLeft(Position position, Character character){
        model.getTiles().get(position.getY()).get(position.getX()-1).setFiller(character);
        model.getTiles().get(position.getY()).get(position.getX()).setFiller(new NoBlockModel());

        model.getViews().get(position.getY())
                .set(position.getX()-1,model.getViews().get(position.getY()).get(position.getX()));
        model.getViews().get(position.getY())
                .set(position.getX(),new NoView());

        position.setX(position.getX()-1);
        character.setPosition((Position) position.clone());

    }

    private void moveRight(Position position, Character character){
        model.getTiles().get(position.getY()).get(position.getX()+1).setFiller(character);
        model.getTiles().get(position.getY()).get(position.getX()).setFiller(new NoBlockModel());

        model.getViews().get(position.getY())
                .set(position.getX()+1,model.getViews().get(position.getY()).get(position.getX()));
        model.getViews().get(position.getY())
                .set(position.getX(),new NoView());

        position.setX(position.getX()+1);
        character.setPosition((Position) position.clone());
    }

    private void moveUp(Position position, Character character){
        model.getTiles().get(position.getY()-1).get(position.getX()).setFiller(character);
        model.getTiles().get(position.getY()).get(position.getX()).setFiller(new NoBlockModel());

        model.getViews().get(position.getY()-1)
                .set(position.getX(),model.getViews().get(position.getY()).get(position.getX()));
        model.getViews().get(position.getY())
                .set(position.getX(),new NoView());

        position.setY(position.getY()-1);
        character.setPosition((Position) position.clone());
    }

    private void moveDown(Position position, Character character){
        model.getTiles().get(position.getY()+1).get(position.getX()).setFiller(character);
        model.getTiles().get(position.getY()).get(position.getX()).setFiller(new NoBlockModel());

        model.getViews().get(position.getY()+1)
                .set(position.getX(),model.getViews().get(position.getY()).get(position.getX()));
        model.getViews().get(position.getY())
                .set(position.getX(),new NoView());

        position.setY(position.getY()+1);
        character.setPosition((Position) position.clone());
    }

    public boolean checkForHero(Position position, Movement movement){
        //System.out.println(position.getX()+" "+position.getY());
        if (movement==Movement.left) {
            return (model.getTiles().get(position.getY()).get(position.getX()-1).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.right) {
            return (model.getTiles().get(position.getY()).get(position.getX()+1).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.up) {
            return (model.getTiles().get(position.getY()-1).get(position.getX()).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.down) {
            return (model.getTiles().get(position.getY()+1).get(position.getX()).getFiller() instanceof HeroModel);
        }
        else if (movement==Movement.stay) {
            return (model.getTiles().get(position.getY()).get(position.getX()).getFiller() instanceof HeroModel);
        }
        return false;
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if(!ended) {
            if (keyPressed.getCharacter() == 'a') {
                if (model.checkPos(model.getHero_pos(), Movement.left)) {
                    moveLeft(model.getHero_pos(), (Character) model.getTiles().get(model.getHero_pos().getY()).get(model.getHero_pos().getX()).getFiller());
                }
            } else if (keyPressed.getCharacter() == 'd') {
                if (model.checkPos(model.getHero_pos(), Movement.right)) {
                    moveRight(model.getHero_pos(), (Character) model.getTiles().get(model.getHero_pos().getY()).get(model.getHero_pos().getX()).getFiller());
                }
            } else if (keyPressed.getCharacter() == 'w') {
                if (model.checkPos(model.getHero_pos(), Movement.up)) {
                    moveUp(model.getHero_pos(), (Character) model.getTiles().get(model.getHero_pos().getY()).get(model.getHero_pos().getX()).getFiller());
                }
            } else if (keyPressed.getCharacter() == 's') {
                if (model.checkPos(model.getHero_pos(), Movement.down)) {
                    moveDown(model.getHero_pos(), (Character) model.getTiles().get(model.getHero_pos().getY()).get(model.getHero_pos().getX()).getFiller());
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
        if(model.getTiles().get(model.getHero_pos().getY()).get(model.getHero_pos().getX()).getCollectible() instanceof DoorModel) {
            model.gettServer().removeListener(this);
            view.draw();
            view = winView;
            view.draw();
            ended=true;
        }
    }


    @Override
    public void updateOnTime() {
        timerSum=timerSum+1;
        if(timerSum==25) {
            for (Position pos : model.getMonsters()) {
                try {
                    MonsterModel tmp_monsterModel = (MonsterModel) model.getTiles().get(pos.getY()).get(pos.getX()).getFiller();
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
                                moveLeft(pos, (MonsterModel) model.getTiles().get(pos.getY()).get(pos.getX()).getFiller());
                            else if (m == Movement.right)
                                moveRight(pos, (MonsterModel) model.getTiles().get(pos.getY()).get(pos.getX()).getFiller());
                            else if (m == Movement.up)
                                moveUp(pos, (MonsterModel) model.getTiles().get(pos.getY()).get(pos.getX()).getFiller());
                            else if (m == Movement.down)
                                moveDown(pos, (MonsterModel) model.getTiles().get(pos.getY()).get(pos.getX()).getFiller());
                            break;
                        }
                    }
                }
                catch (Exception e){

                }
            }
            timerSum=0;
        }
        view.draw();
    }

    @Override
    public void explode(Position position) {
        ArrayList<Position> tmp = new ArrayList<>();
        Position p1 = (Position) position.clone();
        p1.setX(p1.getX()+1);
        Position p2 = (Position) position.clone();
        Position p3 = (Position) position.clone();
        p3.setX(p3.getX()-1);
        Position p4 = (Position) position.clone();
        Position p5 = (Position) position.clone();
        p5.setY(p5.getY()+1);
        Position p6 = (Position) position.clone();
        Position p7 = (Position) position.clone();
        p7.setY(p7.getY()-1);
        Position p8 = (Position) position.clone();

        if(position.getX()>=2){
            p4.setX(p4.getX()-2);
        }
        if(position.getX()<=21){
            p2.setX(p2.getX()+2);

        }

        if(position.getY()>=2){
            p6.setY(p6.getY()+2);
        }
        if(position.getY()<=13){
            p8.setY(p8.getY()-2);
        }
        ArrayList<Position> arrayList = new ArrayList();
        arrayList.add(p1);
        arrayList.add(p2);
        arrayList.add(p3);
        arrayList.add(p4);
        arrayList.add(p5);
        arrayList.add(p6);
        arrayList.add(p7);
        arrayList.add(p8);
        arrayList.add(position);

        for (int i = 0;i<arrayList.size();i++){
            if(!(model.getTiles().get(arrayList.get(i).getY()).get(arrayList.get(i).getX()).getFiller() instanceof IndestructibleBlockModel)) {
                remove(arrayList.get(i));
                tmp.add(arrayList.get(i));
                if (i+1<arrayList.size() && !(model.getTiles().get(arrayList.get(i+1).getY()).get(arrayList.get(i+1).getX()).getFiller() instanceof IndestructibleBlockModel)) {
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
        if(model.getTiles().get(position.getY()).get(position.getX()).getFiller() instanceof MonsterModel)
            model.getMonsters().remove(model.getTiles().get(position.getY()).get(position.getX()).getFiller());
        else if(model.getTiles().get(position.getY()).get(position.getX()).getFiller() instanceof HeroModel) {
            model.gettServer().removeListener(this);
            view.draw();
            view = gamoverView;
            view.draw();
            ended=true;
        }
        model.getTiles().get(position.getY()).get(position.getX()).setFiller(new NoBlockModel());
        System.out.println(position.getY()+" "+position.getX());
        System.out.println(model.getViews().get(position.getY()).get(position.getX()).getClass());
        model.getViews().get(position.getY()).set(position.getX(),new NoView());

        if(model.getTiles().get(position.getY()).get(position.getX()).getCollectible() instanceof DoorModel)
            model.getViews().get(position.getY()).set(
                    position.getX(),
                    new DoorView((DoorModel)model.getTiles().get(position.getY()).get(position.getX()).getCollectible(),textGraphics)
            );
        if(model.getTiles().get(position.getY()).get(position.getX()).getCollectible() instanceof CoinModel)
            model.getViews().get(position.getY()).set(
                    position.getX(),
                    new CoinView((CoinModel) model.getTiles().get(position.getY()).get(position.getX()).getCollectible(),textGraphics)
            );
    }

}
