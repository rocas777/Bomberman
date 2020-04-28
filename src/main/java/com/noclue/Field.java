package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.block.IndestructableBlock;
import com.noclue.block.NoBlock;
import com.noclue.block.RemovableBlock;
import com.noclue.character.Hero;
import com.noclue.character.Monster;
import com.noclue.collectible.Coin;
import com.noclue.collectible.Door;
import com.noclue.collectible.NoCollectible;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Field {
    private final int width;
    private final int height;
    private ArrayList<ArrayList<Tile>> tiles;

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new ArrayList<ArrayList<Tile>>();
    }

    private void setRemovableBlocks(Position door, Position hero){
        Random random=new Random();
        for(int i=0;i<100;i++){
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
                if (p.equals(door)){
                    System.out.println(p.getX()+" "+p.getY()+" "+door.getX()+" "+door.getY());
                    tiles.get(y).add(new Tile(p, new Door(),new RemovableBlock(p)));
                }
                else if(p.equals(hero)){
                    tiles.get(y).add(new Tile(p, new NoCollectible(),new Hero()));
                }
                else if(x==0 || x==22 || y==0 || y==14) {
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

    private void setMonsters(Position door,Position hero){
        Random random=new Random();
        for(int i=0;i<3;i++){
            Position block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
            Position nblock=new Position(23*6+20,15*3+6,block.getX()*6,block.getY()*3);

            while(nblock.equals(hero)|| nblock.equals(door) || (block.getY()%2==0 && block.getX()%2==0)) {
                block=new Position(23,15,random.nextInt(21)+1,random.nextInt(13)+1);
                nblock=new Position(23*6+20,15*3+6,block.getX()*6,block.getY()*3);
            }
            tiles.get(block.getY()).set(block.getX(),new Tile(nblock, new NoCollectible(), new Monster()));
        }
    }

    private Position setHero(){
        Random random=new Random();
        Position hero=new Position(23*6+20,15*6+20,6,3);
        while (hero.getX()%2==0 && hero.getY()%2==0) {
            hero = new Position(23 * 6 + 20, 15 * 6 + 20, (random.nextInt(23)) * 6, (random.nextInt(13) * 3));
        }
        return hero;
    }

    private Position setDoor(Position hero){
        Random random=new Random();
        Position door=new Position(23*6+20,15*6+20,(random.nextInt(12)+10)*6,(random.nextInt(6)+6)*3);
        while ((door.getX()%2==0 && door.getY()%2==0) || door.equals(hero)){
            door=new Position(23*6+20,15*6+20,(random.nextInt(23))*6,(random.nextInt(13)*3));
        }
        return door;
    }

    public void setLayout() {
        System.out.println("olkj");

        Position hero=setHero();
        Position door=setDoor(hero);

        setIndestructableBlocks(door,hero);
        setRemovableBlocks(door,hero);
        setMonsters(door,hero);
    }
    public void draw(TextGraphics textGraphics) throws IOException {
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
}