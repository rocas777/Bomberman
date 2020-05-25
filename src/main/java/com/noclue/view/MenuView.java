package com.noclue.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.MenuModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class MenuView implements IView {
    MenuModel menuModel;

    public MenuView(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void draw(TextGraphics textGraphics){
        menuModel.getTextGraphics().setBackgroundColor(TextColor.Factory.fromString("#ffffff"));

        if(menuModel.getOption()==1){
            textGraphics.putString(70,20,"Start Game",BOLD);
        }
        else{
            textGraphics.putString(70,20,"Start Game");
        }
        if(menuModel.getOption()==2){
            textGraphics.putString(70,22,"Choose Difficulty",BOLD);
        }
        else{
            textGraphics.putString(70,22,"Choose Difficulty");
        }
        if(menuModel.getOption()==3){
            textGraphics.putString(70,24,"Exit",BOLD);
        }
        else{
            textGraphics.putString(70,24,"Exit");
        }

    }

    public void draw() {
        draw(menuModel.getTextGraphics());
    }
}
