package com.noclue.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class MenuModel {
    int option;
    static Screen screen;
    TextGraphics textGraphics;

    public void optUp(){
        if(option!=1){
            option--;
        }
        else{
            option=3;
        }
    }

    public void optDown(){
        if(option!=3){
            option++;
        }
        else{
            option=1;
        }
    }

    public static Screen getScreen() {
        return screen;
    }

    public static void setScreen(Screen screen) {
        MenuModel.screen = screen;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public MenuModel(int option) {
        this.option = option;

    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
