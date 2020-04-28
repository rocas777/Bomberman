package com.noclue;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;
import com.noclue.block.IndestructableBlock;
import com.noclue.block.RemovableBlock;
import com.noclue.collectible.Coin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.googlecode.lanterna.SGR.BOLD;

public class Field {
    private final int width;
    private final int height;
    private ArrayList<ArrayList<Tile>> tiles;

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        tiles=new ArrayList<ArrayList<Tile>>();
    }


    public void setLayout() {
        Random random=new Random();
        Position hero=new Position(23,15,random.nextInt(23),random.nextInt(15));
        Position door=new Position(23,15,random.nextInt(12)+11,random.nextInt(7)+8);

        for(int y=0;y<15;y++){
            tiles.add(new ArrayList<>());
            for(int x=0;x<23;x++){
                if(x==0 || x==22 || y==0 || y==14) {
                    Position p=new Position(23,15,x,y);
                    p = new Position(23 * 6 + 20, 15 * 3 + 6, x * 6, y * 3);
                    tiles.get(y).add(new Tile(p, new Coin(),new IndestructableBlock(p)));
                }
                else if(y%2==0){
                    if(x%2==0){
                        Position p=new Position(23,15,x,y);
                        //if(p==hero)
                        p=new Position(23*6+20,15*3+6,x*6,y*3);
                        tiles.get(y).add(new Tile(p, new Coin(),new IndestructableBlock(p)));
                    }
                    else {
                        Position p=new Position(23,15,x,y);
                        p=new Position(23*6+20,15*3+6,x*6,y*3);
                        tiles.get(y).add(new Tile(p, new Coin(),new RemovableBlock(p)));
                    }
                }
                else {
                        Position p=new Position(23,15,x,y);
                        p=new Position(23*6+20,15*3+6,x*6,y*3);
                        tiles.get(y).add(new Tile(p, new Coin(),new RemovableBlock(p)));
                    }


            }
        }
    }
    public void draw(TextGraphics textGraphics) throws IOException {
        Random random = new Random();
        int integer = random.nextInt(60);
        textGraphics.putString(integer,integer,"HERO",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.fillRectangle(new TerminalPosition(width-8, 0), new TerminalSize(width-8, height), ' ');
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(width-8-6-6, height-3-3), ' ');
        for (int y=0;y<13;y++){
            for (Tile t : tiles.get(y))
                t.draw(textGraphics);
        }
    }
}