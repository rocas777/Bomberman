package com.noclue;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.controller.FieldController;
import com.noclue.controller.KeyBoard;
import com.noclue.controller.KeyboardListener;
import com.noclue.controller.Timer;
import com.noclue.model.FieldModel;
import com.noclue.view.FieldView;

import java.io.IOException;

public class Game implements KeyboardListener {
    private static Screen screen;
    private FieldModel fieldModel;
    boolean running;
    private TextGraphics textGraphics;

    public Game(int width, int height){
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
        textGraphics=screen.newTextGraphics();
        fieldModel =new FieldModel(width,height);
        running=true;

        Timer t=new Timer(20,screen);
        t.start();

        KeyBoard k= new KeyBoard((TerminalScreen) screen);
        k.addListener(this);
        k.start();

        fieldModel.setkServer(k);
        fieldModel.settServer(t);

    }

    public void run(){
        new FieldController(fieldModel,new FieldView(screen,textGraphics,fieldModel),textGraphics).setup();
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