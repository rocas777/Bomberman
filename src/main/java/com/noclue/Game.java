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
import com.noclue.keyboard.KeyBoard;
import com.noclue.keyboard.KeyboardListener;
import com.noclue.model.FieldModel;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.timer.Timer;
import com.noclue.view.LivesView;
import com.noclue.view.field.FieldView;
import com.noclue.view.field.GameOverView;
import com.noclue.view.field.WinView;

import java.io.IOException;
import java.lang.reflect.Field;

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

        Timer t=new Timer(20);
        t.start();

        KeyBoard k= new KeyBoard((TerminalScreen) screen);
        k.addListener(this);
        k.start();

        fieldModel.setkServer(k);
        fieldModel.settServer(t);

    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        Game.screen = screen;
    }

    public void run(){
        FieldView fieldView = new FieldView(screen,textGraphics,fieldModel);
        LivesModel livesModel = new LivesModel(3,new Position(146,45,138,2));
        fieldView.setLivesView(new LivesView(livesModel,textGraphics));
        new FieldController(fieldModel,fieldView,new GameOverView(screen,textGraphics),new WinView(screen,textGraphics),textGraphics,livesModel).setup();
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