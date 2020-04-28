package com.noclue;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.Collectible.CollectibleView;
import com.noclue.Collectible.DoorView;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private static Screen screen;
    private Field field;
    boolean running;

    public Game(int width, int height){
        field=new Field(width,height);
        ArrayList<Tile> tiles=new ArrayList<>();
        for(int y=0;y<15;y++)
            for (int x=0;x<23;x++){
                Tile tmp=new Tile(x,y);
                tiles.add(tmp);
            }
        running=true;
        try{
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            screen = new TerminalScreen(terminal);


            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        }
        catch(IOException e){
            e.printStackTrace();
        }
        field.setLayout();
    }

    private void draw() throws IOException {
        screen.clear();
        field.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key){

    }

    public void run(){
        try {
            new Thread(() -> {
                int i = 0;
                while (running) {
                    try {
                        Thread.sleep(1000);    //updates field every 2s (for now)
                        i++;
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }).start();

            while (running) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    running = false;
                    System.out.println(key);
                    screen.close();
                }
                else if(key.getKeyType()==KeyType.EOF){
                    running=false;
                }
                else{
                    System.out.println(key);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
