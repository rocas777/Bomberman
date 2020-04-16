package com.noclue;
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

    public Game(){
        field=new Field();
        try{
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
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
        //call field draw func
        screen.refresh();
    }

    private void processKey(KeyStroke key){
        //call field process key
    }

    public void run(){
        try{
            while(true){
                draw();
                KeyStroke key = screen.readInput();
                if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                    System.out.println(key);
                    screen.close();
                }
                else if(key.getKeyType()==KeyType.EOF){
                    break;
                }
                else{
                    processKey(key);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
