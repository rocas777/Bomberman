package com.noclue;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.noclue.controller.MenuController;
import com.noclue.model.MenuModel;
import com.noclue.view.MenuView;

import java.io.IOException;


public class Bomberman {
    public static void main(String[] args) {

        MenuModel menuModel = new MenuModel(1, 1);
        MenuController menuController = null;

        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(146, 45)).createTerminal();
            MenuModel.setScreen(new TerminalScreen(terminal));
            MenuModel.getScreen().setCursorPosition(null);   // we don't need a cursor
            MenuModel.getScreen().startScreen();             // screens must be started
            MenuModel.getScreen().doResizeIfNecessary();     // resize screen if necessary

            menuModel.setTextGraphics(MenuModel.getScreen().newTextGraphics());

            menuController = new MenuController(menuModel, new MenuView(menuModel));
            menuController.setDifficulties();
            menuController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
