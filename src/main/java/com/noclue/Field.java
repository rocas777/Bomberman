package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.noclue.block.IndestructableBlock;
import com.noclue.block.NoBlock;
import com.noclue.block.RemovableBlock;
import com.noclue.character.Character;
import com.noclue.character.Hero;
import com.noclue.character.Monster;
import com.noclue.collectible.Coin;
import com.noclue.collectible.Door;
import com.noclue.collectible.NoCollectible;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

enum Movement {
    left,
    right,
    up,
    down
}

public class Field implements KeyboardListener{
    private final int width;
    private final int height;
    private ArrayList<ArrayList<Tile>> tiles;
    private Tile heroTile;

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new ArrayList<>();
    }

    private void setRemovableBlocks(Position door, Position hero,int numerOfBlocks){
        Random random=new Random();
        for(int i=0;i<numerOfBlocks;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            Position nblock=new Position(23*6+20,15*3+6,block.getX()*6,block.getY()*3);

            while(nblock.equals(hero)|| nblock.equals(door) || (block.getY()%2==0 && block.getX()%2==0) ||(block.getX()<4 && block.getY()<4)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
                nblock=new Position(23*6+20,15*3+6,block.getX()*6,block.getY()*3);
            }

            boolean coin=random.nextBoolean();
            if(coin) {
                tiles.get(block.getY()).set(block.getX(),new Tile(nblock, new Coin(), new RemovableBlock(nblock)));
            }
            else {tiles.get(block.getY()).set(block.getX(),new Tile(nblock, new NoCollectible(), new RemovableBlock(nblock)));
            }
        }
    }

    private void setIndestructableBlocks(Position door, Position hero){
        for(int y=0;y<15;y++){
            tiles.add(new ArrayList<>());
            for(int x=0;x<23;x++){
                Position p=new Position(23 * 6 + 20, 15 * 3 + 6, x * 6, y * 3);
                //System.out.println(door.getX()+" "+door.getY());
                if(x==0 || x==22 || y==0 || y==14) {
                    tiles.get(y).add(new Tile(p, new Coin(),new IndestructableBlock(p)));
                }
                else if(y%2==0) {
                    if (x % 2 == 0) {
                        tiles.get(y).add(new Tile(p, new Coin(), new IndestructableBlock(p)));
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
            Position nblock=new Position(23*6+20,15*3+6,block.getX()*6,block.getY()*3);

            while(nblock.equals(hero)|| nblock.equals(door) || (block.getY()%2==0 && block.getX()%2==0)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
                nblock=new Position(23*6+20,15*3+6,block.getX()*6,block.getY()*3);
            }
            tiles.get(block.getY()).set(block.getX(),new Tile(nblock, new NoCollectible(), new Monster()));
        }
    }

    private Position setHeroPos(){
        Random random=new Random();
        Position hero=new Position(23*6+20,15*6+20,6,3);
        while (hero.getX()%2==0 && hero.getY()%2==0) {
            hero = new Position(23 * 6 + 20, 15 * 6 + 20, (random.nextInt(23)) * 6, (random.nextInt(13) * 3));
        }
        return hero;
    }

    private Position setDoorPos(Position hero){
        Random random=new Random();
        Position door=new Position(23*6+20,15*6+20,(random.nextInt(12)+10)*6,(random.nextInt(6)+6)*3);
        while ((door.getX()%2==0 && door.getY()%2==0) || door.equals(hero)){
            door=new Position(23*6+20,15*6+20,(random.nextInt(23))*6,(random.nextInt(13)*3));
        }
        return door;
    }


    private void setHero(Position position){
        tiles.get((position.getY())/3).get((position.getX())/6).setFiller(new Hero());
        tiles.get((position.getY())/3).get((position.getX())/6).setCollectible(new NoCollectible());

        heroTile = tiles.get((position.getY())/3).get((position.getX())/6);
    }

    private void setDoor(Position position){
        tiles.get((position.getY())/3).set((position.getX())/6,new Tile(position, new Door(),new RemovableBlock(position)));
    }

    public void setLayout() {
        Position hero=setHeroPos();
        Position door=setDoorPos(hero);

        setIndestructableBlocks(door,hero);
        setRemovableBlocks(door,hero,150);
        setMonsters(door,hero,3);

        setHero(hero);
        setDoor(door);
    }
    public void draw(TextGraphics textGraphics){
        Random random = new Random();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(width-8, 0), new TerminalSize(width-8, height), ' ');

        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(width-8-6-6, height-3-3), ' ');
        for (int y=0;y<15;y++){
            for (Tile t : tiles.get(y))
                t.draw(textGraphics);
        }
    }

    private void moveHeroLeft(Position position){
        //Tile tile_tmp = (tiles.get(position.getY()).get(position.getX()));
        //Hero hero_tmp=(Hero)(tile_tmp.getFiller());
        tiles.get(position.getY()).get(position.getX()).setFiller(new NoBlock());
        tiles.get(position.getY()).get(position.getX()-1).setFiller(tiles.get(position.getY()).get(position.getX()).getFiller());
        System.out.println("left");
    }

    private void moveHeroRight(Position position){
        System.out.println(position.getX()+" "+position.getY());
        Tile tile_tmp = (tiles.get(position.getY()).get(position.getX()));
        Filler hero_tmp=(tile_tmp.getFiller());
        tile_tmp.setFiller(new NoBlock());
        tiles.get(position.getY()).get(position.getX()+1).setFiller(hero_tmp);
        System.out.println("right ole");
    }

    private void moveHeroUp(Position position){
        Tile tile_tmp = (tiles.get(position.getY()).get(position.getX()));
        Hero hero_tmp=(Hero)(tile_tmp.getFiller());
        tile_tmp.setFiller(new NoBlock());
        tiles.get(position.getY()-1).get(position.getX()).setFiller(hero_tmp);
        System.out.println("up");
    }

    private void moveHeroDown(Position position){
        Tile tile_tmp = (tiles.get(position.getY()).get(position.getX()));
        Hero hero_tmp=(Hero)(tile_tmp.getFiller());
        tile_tmp.setFiller(new NoBlock());
        tiles.get(position.getY()+1).get(position.getX()).setFiller(hero_tmp);
        System.out.println("down");
    }

    private  boolean checkPos(Position position, Movement movement){
        System.out.println(position.getX()+" "+position.getY());
        if (movement==Movement.left) {
            boolean a= !(tiles.get(position.getY()).get(position.getX()-6).getFiller().isFilled());
            System.out.println(a);
            return a;
        }
        else if (movement==Movement.right) {
            boolean a=!(tiles.get(position.getY()).get(position.getX()+6).getFiller().isFilled());
            System.out.println(tiles.get(position.getY()).get(position.getX()+6).getFiller().getClass());
            System.out.println(a);
            return a;
        }
        else if (movement==Movement.up) {
            boolean a= !(tiles.get(position.getY()-3).get(position.getX()).getFiller().isFilled());
            System.out.println(a);
            return a;
        }
        else if (movement==Movement.down) {
            boolean a= !(tiles.get(position.getY()+3).get(position.getX()).getFiller().isFilled());
            System.out.println(a);
            return a;
        }
        return false;
    }


    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if(keyPressed.getCharacter()=='a'){
            if(checkPos(heroTile.getPosition(),Movement.left)) {
                moveHeroLeft(heroTile.getPosition());
            }
        }
        else if(keyPressed.getCharacter()=='d'){
            if(checkPos(heroTile.getPosition(),Movement.right))
                moveHeroRight(heroTile.getPosition());
        }
        else if(keyPressed.getCharacter()=='w'){
            if(checkPos(heroTile.getPosition(),Movement.up))
                moveHeroUp(heroTile.getPosition());
        }
        else if(keyPressed.getCharacter()=='s'){
            if(checkPos(heroTile.getPosition(),Movement.down))
                moveHeroDown(heroTile.getPosition());
        }
    }
}
