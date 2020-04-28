package com.noclue;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private static Screen screen;
    private Field field;
    boolean running;

    public Game(int width, int height){
        field=new Field(width,height);
        field.setLayout();
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
    }

    private void draw() throws IOException {
        screen.clear();
        field.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key){

    }

    public void run(){
        try{
            new Thread() {
                @Override
                public void run() {
                    int i=0;
                    while (running) {
                        try {
                            sleep(500);    //updates field every 2s (for now)
                            System.out.println(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }.start();

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