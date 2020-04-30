package com.noclue;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.character.TimeListener;

import java.io.IOException;

public class Game implements TimeListener {
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
        Timer.addListener(field);
        new Timer(50).start();
        Timer.addListener(this);
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
            while (running) {
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character) {
                    //System.out.println(key.getCharacter());
                    if (key.getCharacter() == 'q') {
                        running = false;
                        //System.out.println(key);
                        screen.close();
                    } else if (key.getKeyType() == KeyType.EOF) {
                        running = false;
                    } else {
                        //System.out.println(key.getCharacter());
                        field.updateOnKeyboard(key);
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateOnTime() {
        try {
            draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}