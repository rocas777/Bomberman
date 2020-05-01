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

public class Game implements TimeListener, KeyboardListener {
    private static Screen screen;
    private Field field;
    boolean running;

    public Game(int width, int height){
        field=new Field(width,height);
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

        Timer t=new Timer(20);
        t.addListener(field);
        t.addListener(this);
        t.start();

        KeyBoard k= new KeyBoard((TerminalScreen) screen);
        k.addListener(this);
        k.start();

        field.setkServer(k);
        field.settServer(t);
    }

    private void draw() throws IOException {
        screen.clear();
        field.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run(){
        field.setup();
    }

    @Override
    public void updateOnTime() {
        try {
            draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if (keyPressed.getCharacter() == 'q' || keyPressed.getKeyType() == KeyType.EOF) {
            try {
                screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}