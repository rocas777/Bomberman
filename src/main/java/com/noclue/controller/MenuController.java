package com.noclue.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.Game;
import com.noclue.keyboard.KeyBoard;
import com.noclue.model.FieldModel;
import com.noclue.model.MenuModel;
import com.noclue.model.Position;
import com.noclue.model.TimeLeft;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.model.difficulty.Easy;
import com.noclue.model.difficulty.Hard;
import com.noclue.model.difficulty.Medium;
import com.noclue.timer.Timer;
import com.noclue.view.MenuView;
import com.noclue.view.TimeLeftView;
import com.noclue.view.field.FieldView;
import com.noclue.view.field.GameOverView;
import com.noclue.view.field.WinView;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {
    MenuModel menuModel;
    MenuView menuView;
    FieldModel fieldModel;
    ArrayList<Difficulty> difficulties =  new ArrayList<>();


    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public MenuController(MenuModel menuModel, MenuView menuView) throws IOException {
        this.menuModel = menuModel;
        this.menuView = menuView;


        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(146, 45)).createTerminal();
        MenuModel.setScreen(new TerminalScreen(terminal));
        MenuModel.getScreen().setCursorPosition(null);   // we don't need a cursor
        MenuModel.getScreen().startScreen();             // screens must be started
        MenuModel.getScreen().doResizeIfNecessary();     // resize screen if necessary

        menuModel.setTextGraphics(MenuModel.getScreen().newTextGraphics());


        difficulties.add(new Easy());
        difficulties.add(new Medium());
        difficulties.add(new Hard());
    }

    public void run(){
        KeyStroke key;
        while(true){
            menuView.draw();
            try {
                key = MenuModel.getScreen().readInput();
                if(key!=null && (key.getKeyType()==KeyType.Character || key.getKeyType() == KeyType.Enter)) {
                    if(!menuModel.getOnSubMenu()){
                        if (key.getCharacter() == 'w') {
                            menuModel.optUp();
                        } else if (key.getCharacter() == 's') {
                            menuModel.optDown();
                        } else if (key.getKeyType() == KeyType.Enter) {
                            if (menuModel.getOption() == 3) {
                                System.exit(0);
                            }
                            else if(menuModel.getOption()==2){
                                menuModel.setOnSubMenu(true);
                            }
                            else if(menuModel.getOption()==1){
                                startNewGame();
                                break;
                            }
                        }
                    }
                    else{
                        if (key.getCharacter() == 'w') {
                            menuModel.subOptUp();
                        } else if (key.getCharacter() == 's') {
                            menuModel.subOptDown();
                        } else if (key.getKeyType() == KeyType.Enter) {
                            menuModel.setOnSubMenu(false);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void startNewGame(){
        fieldModel = new FieldModel(146,45);

        Timer t=new Timer(40);
        t.start();

        KeyBoard k= new KeyBoard((TerminalScreen) MenuModel.getScreen());
        k.start();

        fieldModel.setkServer(k);
        fieldModel.settServer(t);

        FieldView fieldView = new FieldView(MenuModel.getScreen(),menuModel.getTextGraphics(),fieldModel);
        TimeLeft timeLeft = new TimeLeft(120, new Position(146,45,138,30));
        fieldView.setTimeLeftView(new TimeLeftView(timeLeft,menuModel.getTextGraphics()));
        FieldController fieldController= new FieldController(fieldModel,fieldView,new GameOverView(MenuModel.getScreen(),menuModel.getTextGraphics()),new WinView(MenuModel.getScreen(),menuModel.getTextGraphics()),menuModel.getTextGraphics(), timeLeft);


        fieldController.setup();
        fieldModel.gettServer().addListener(fieldController);
        fieldModel.getkServer().addListener(fieldController);

        fieldController.setDifficulty(difficulties.get(menuModel.getSubOption()));
    }
}
