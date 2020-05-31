package com.noclue.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.model.MenuModel;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.model.difficulty.Easy;
import com.noclue.model.difficulty.Hard;
import com.noclue.model.difficulty.Medium;
import com.noclue.view.MenuView;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class MenuControllerTest {

    @Test
    public void readDifficulties(){
        MenuModel menuModel = mock(MenuModel.class);
        MenuController menuController = null;
        menuController = spy(new MenuController(menuModel,mock(MenuView.class)));
        ArrayList<ArrayList<Difficulty>> arrayLists = menuController.readDifficulties(menuModel);
        Assert.assertEquals(21,arrayLists.size());
        ArrayList<Difficulty> list = new ArrayList<>();
        list.add(new Easy());
        for(int i=0;i<arrayLists.get(0).size();i++){
            Assert.assertEquals(list.get(i).getClass(),arrayLists.get(0).get(i).getClass());
        }


        list.clear();
        list.add(new Easy());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        Assert.assertEquals(list.size(),arrayLists.get(9).size());
        for(int i=0;i<arrayLists.get(9).size();i++){
            Assert.assertEquals(list.get(i).getClass(),arrayLists.get(9).get(i).getClass());
        }

        list.clear();
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Medium());
        list.add(new Hard());
        Assert.assertEquals(list.size(),arrayLists.get(12).size());
        for(int i=0;i<arrayLists.get(12).size();i++){
            Assert.assertEquals(list.get(i).getClass(),arrayLists.get(12).get(i).getClass());
        }
        verify(menuModel,times(1)).setLevel(1);

    }

    @Test
    public void killProgram() {
    }

    @Test
    public void run() {
        MenuModel menuModel = mock(MenuModel.class);
        MenuController menuController = null;
        menuController = spy(new MenuController(menuModel,mock(MenuView.class)));

        Screen screen = mock(Screen.class);
        MenuModel.setScreen(screen);
        KeyStroke keyStroke = mock(KeyStroke.class);
        try {
            when(screen.readInput()).thenReturn(keyStroke);
        } catch (IOException e) {
            e.printStackTrace();
        }
        when(menuModel.getOnSubMenu()).thenReturn(false);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        when(menuModel.getLevel()).thenReturn(1);
        when(menuModel.getLevels()).thenReturn("");
        try {
            doNothing().when(menuController).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(keyStroke.getCharacter()).thenReturn('w');
        menuController.run();

        verify(menuModel,times(1)).optUp();
        verify(menuModel,times(0)).optDown();
        try {
            verify(menuController,times(1)).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(keyStroke.getCharacter()).thenReturn('s');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        menuController.run();

        verify(menuModel,times(1)).optUp();
        verify(menuModel,times(1)).optDown();
        try {
            verify(menuController,times(2)).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(keyStroke.getCharacter()).thenReturn(' ');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        menuController.run();

        verify(menuModel,times(1)).optUp();
        verify(menuModel,times(1)).optDown();
        verify(menuController,times(1)).chooseOption();
        try {
            verify(menuController,times(3)).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }



        when(menuModel.getOnSubMenu()).thenReturn(true);

        when(keyStroke.getCharacter()).thenReturn('w');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        menuController.run();

        verify(menuModel,times(1)).subOptUp();
        verify(menuModel,times(0)).subOptDown();
        try {
            verify(menuController,times(4)).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(keyStroke.getCharacter()).thenReturn('s');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        menuController.run();

        verify(menuModel,times(1)).subOptUp();
        verify(menuModel,times(1)).subOptDown();
        try {
            verify(menuController,times(5)).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(keyStroke.getCharacter()).thenReturn(' ');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        menuController.run();

        verify(menuModel,times(1)).subOptUp();
        verify(menuModel,times(1)).subOptDown();
        verify(menuModel,times(1)).setOnSubMenu(false);
        try {
            verify(menuController,times(6)).killProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }



        when(menuModel.getOnSubMenu()).thenReturn(false);
        when(menuModel.getOption()).thenReturn(1);
        when(menuModel.getSubOption()).thenReturn(2);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        doNothing().when(menuController).startNewGame();
        menuController.difficulties.add(null);
        menuController.run();

        Assert.assertEquals(menuController.difficulties.size(),7);
        for(Difficulty d:menuController.difficulties){
            Assert.assertEquals(d.getClass(), Medium.class);
        }

        when(menuModel.getSubOption()).thenReturn(1);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        doNothing().when(menuController).startNewGame();
        menuController.run();

        Assert.assertEquals(menuController.difficulties.size(),6);
        for(Difficulty d:menuController.difficulties){
            Assert.assertEquals(d.getClass(), Easy.class);
        }


        when(menuModel.getSubOption()).thenReturn(3);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        doNothing().when(menuController).startNewGame();
        menuController.run();

        Assert.assertEquals(menuController.difficulties.size(),8);
        for(Difficulty d:menuController.difficulties){
            Assert.assertEquals(d.getClass(), Hard.class);
        }


    }

    @Test
    public void chooseOption() {
    }

    @Test
    public void startNewGame() {
    }

    @Test
    public void updateOnKeyboard() {
    }
}