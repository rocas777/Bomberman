package com.noclue.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class MenuModel {
    int option;
    int subOption;
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

    public void subOptUp(){
        if(subOption!=1){
            subOption--;
        }
        else{
            subOption=3;
        }
    }

    public void subOptDown(){
        if(subOption!=3){
            subOption++;
        }
        else{
            subOption=1;
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

    public MenuModel(int option,int subOption) {
        this.option = option;
        this.subOption = subOption;

    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
