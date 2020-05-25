package com.noclue.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.model.MenuModel;
import com.noclue.view.MenuView;

import java.io.IOException;

public class MenuController {
    MenuModel menuModel;
    MenuView menuView;


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


        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(160, 50)).createTerminal();
        MenuModel.setScreen(new TerminalScreen(terminal));
        MenuModel.getScreen().setCursorPosition(null);   // we don't need a cursor
        MenuModel.getScreen().startScreen();             // screens must be started
        MenuModel.getScreen().doResizeIfNecessary();     // resize screen if necessary

        menuModel.setTextGraphics(MenuModel.getScreen().newTextGraphics());
    }

    public void run(){
        KeyStroke key;
        while(true){
            menuView.draw();
            try {
                key = MenuModel.getScreen().readInput();
                System.out.println(key.getCharacter().charValue());
                if(key!=null) {
                    if (key.getCharacter() == 'w') {
                        menuModel.optUp();
                    } else if (key.getCharacter() == 's') {
                        menuModel.optDown();
                    } else if (key.getKeyType() == KeyType.Enter) {
                        if (menuModel.getOption() == 3) {
                            System.exit(0);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
