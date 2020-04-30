package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.noclue.block.IndestructibleBlock;
import com.noclue.block.NoBlock;
import com.noclue.block.RemovableBlock;
import com.noclue.character.Character;
import com.noclue.character.Hero;
import com.noclue.character.Monster;
import com.noclue.character.TimeListener;
import com.noclue.collectible.Coin;
import com.noclue.collectible.Door;
import com.noclue.collectible.NoCollectible;
import com.noclue.difficulty.Easy;

import java.util.ArrayList;
import java.util.Random;

public class Field implements KeyboardListener, TimeListener, ExplosionListener {
    private final int width;
    private final int height;
    final private ArrayList<ArrayList<Tile>> tiles;
    private Position hero_pos;
    private ArrayList<Position> monsters=new ArrayList<>();

    private int timerSum=0;

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new ArrayList<>();
    }

    private void setRemovableBlocks(Position door, Position hero,int numberBlocks){
        Random random=new Random();
        for(int i=0;i<numberBlocks;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0) ||(block.getX()<4 && block.getY()<4)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            }

            boolean coin=random.nextBoolean();
            if(coin) {
                tiles.get(block.getY()).set(block.getX(),new Tile(block, new Coin(), new RemovableBlock()));
            }
            else {tiles.get(block.getY()).set(block.getX(),new Tile(block, new NoCollectible(), new RemovableBlock()));
            }
        }
    }

    private void setIndestructibleBlocks(){
        for(int y=0;y<15;y++){
            tiles.add(new ArrayList<>());
            for(int x=0;x<23;x++){
                Position p=new Position(23, 15, x, y);
                //System.out.println(door.getX()+" "+door.getY());
                if(x==0 || x==22 || y==0 || y==14) {
                    tiles.get(y).add(new Tile(p, new Coin(),new IndestructibleBlock()));
                }
                else if(y%2==0) {
                    if (x % 2 == 0) {
                        tiles.get(y).add(new Tile(p, new Coin(), new IndestructibleBlock()));
                    }
                    else {
                        tiles.get(y).add(new Tile(p, new NoCollectible(), new NoBlock()));
                    }
                }
                else {
                    tiles.get(y).add(new Tile(p, new NoCollectible(), new NoBlock()));
                }
            }
        }
    }

    private void setMonsters(Position door,Position hero,int numberOfMonsters){
        Random random=new Random();
        for(int i=0;i<numberOfMonsters;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);

            while(block.equals(hero)|| block.equals(door) || (block.getY()%2==0 && block.getX()%2==0)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            }
            tiles.get(block.getY()).get(block.getX()).setFiller(new Monster(new Easy()));
            tiles.get(block.getY()).get(block.getX()).setCollectible(new NoCollectible());
            monsters.add((Position) block.clone());
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
        return door;
    }


    private void setHero(Position position){
        tiles.get(position.getY()).get(position.getX()).setFiller(new Hero());
        tiles.get(position.getY()).get(position.getX()).setCollectible(new NoCollectible());

        hero_pos = position;
    }

    private void setDoor(Position position){
        tiles.get(position.getY()).set(position.getX(),new Tile(position, new Door(),new RemovableBlock()));
    }

    public void setLayout() {

        Position hero=setHeroPos();
        Position door=setDoorPos(hero);

        setIndestructibleBlocks();
        setRemovableBlocks(door,hero,150);
        setMonsters(door,hero,3);

        setHero(hero);
        setDoor(door);

        KeyBoard.addListener(this);
    }
    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(width-8, 0), new TerminalSize(width-8, height), ' ');

        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(width-8-6-6, height-3-3), ' ');
        for (int y=0;y<15;y++){
            for (Tile t : tiles.get(y)) {
                t.draw(textGraphics);
            }
        }
    }

    private void moveLeft(Position position, Character character){
        tiles.get(position.getY()).get(position.getX()-1).setFiller(character);
        tiles.get(position.getY()).get(position.getX()).setFiller(new NoBlock());
        position.setX(position.getX()-1);
    }

    private void moveRight(Position position, Character character){
        tiles.get(position.getY()).get(position.getX()+1).setFiller(character);
        tiles.get(position.getY()).get(position.getX()).setFiller(new NoBlock());
        position.setX(position.getX()+1);
    }

    private void moveUp(Position position, Character character){
        tiles.get(position.getY()-1).get(position.getX()).setFiller(character);
        tiles.get(position.getY()).get(position.getX()).setFiller(new NoBlock());
        position.setY(position.getY()-1);
    }

    private void moveDown(Position position, Character character){
        tiles.get(position.getY()+1).get(position.getX()).setFiller(character);
        tiles.get(position.getY()).get(position.getX()).setFiller(new NoBlock());
        position.setY(position.getY()+1);
    }

    private  boolean checkPos(Position position, Movement movement){
        System.out.println(position.getX()+" "+position.getY());
        if (movement==Movement.left) {
            return !(tiles.get(position.getY()).get(position.getX()-1).isFilled());
        }
        else if (movement==Movement.right) {
            return !(tiles.get(position.getY()).get(position.getX()+1).isFilled());
        }
        else if (movement==Movement.up) {
            return !(tiles.get(position.getY()-1).get(position.getX()).isFilled());
        }
        else if (movement==Movement.down) {
            return !(tiles.get(position.getY()+1).get(position.getX()).isFilled());
        }
        return false;
    }


    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if(keyPressed.getCharacter()=='a'){
            if(checkPos(hero_pos,Movement.left)) {
                moveLeft(hero_pos, (Character) tiles.get(hero_pos.getY()).get(hero_pos.getX()).getFiller());
            }
        }
        else if(keyPressed.getCharacter()=='d'){
            if(checkPos(hero_pos,Movement.right)) {
                moveRight(hero_pos, (Character) tiles.get(hero_pos.getY()).get(hero_pos.getX()).getFiller());
            }
        }
        else if(keyPressed.getCharacter()=='w'){
            if(checkPos(hero_pos,Movement.up)) {
                moveUp(hero_pos, (Character) tiles.get(hero_pos.getY()).get(hero_pos.getX()).getFiller());
            }
        }
        else if(keyPressed.getCharacter()=='s'){
            if(checkPos(hero_pos,Movement.down)) {
                moveDown(hero_pos, (Character) tiles.get(hero_pos.getY()).get(hero_pos.getX()).getFiller());
            }
        }
    }


    @Override
    public void updateOnTime() {
        timerSum=timerSum+1;
        if(timerSum==25) {
            for (Position pos : monsters) {
                Monster tmp_monster = (Monster) tiles.get(pos.getY()).get(pos.getX()).getFiller();
                for (Movement m : tmp_monster.nextMove(pos)) {
                    if (checkPos(pos, m)) {
                        if (m == Movement.left)
                            moveLeft(pos, (Monster) tiles.get(pos.getY()).get(pos.getX()).getFiller());
                        else if (m == Movement.right)
                            moveRight(pos, (Monster) tiles.get(pos.getY()).get(pos.getX()).getFiller());
                        else if (m == Movement.up)
                            moveUp(pos, (Monster) tiles.get(pos.getY()).get(pos.getX()).getFiller());
                        else if (m == Movement.down)
                            moveDown(pos, (Monster) tiles.get(pos.getY()).get(pos.getX()).getFiller());
                        break;
                    }
                }
            }
            timerSum=0;
        }
    }

    @Override
    public void explode(Position position) {

    }
}
