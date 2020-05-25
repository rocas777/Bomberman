package com.noclue;

import com.noclue.controller.MenuController;
import com.noclue.model.MenuModel;
import com.noclue.view.MenuView;

import java.io.IOException;


public class Bomberman {
    public static void main(String[] args) {
        //Game game = new Game(146,45);
        //game.run();

        MenuModel menuModel = new MenuModel(1);
        MenuController menuController = null;
        try{
            menuController = new MenuController(menuModel,new MenuView(menuModel));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(menuController!=null)    menuController.run();
    }
}
