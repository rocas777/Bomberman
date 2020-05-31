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
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class MenuControllerTest {
    MenuModel menuModel = mock(MenuModel.class);
    MenuController menuController = spy(new MenuController(menuModel,mock(MenuView.class)));
    @Before
    public void setUp(){
    }

    @Test
    public void readDifficulties(){
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
    public void run() throws Exception{
        Screen screen = mock(Screen.class);
        MenuModel.setScreen(screen);
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(screen.readInput()).thenReturn(keyStroke);


        when(menuModel.getOnSubMenu()).thenReturn(false);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        when(menuModel.getLevel()).thenReturn(1);
        when(menuModel.getLevels()).thenReturn("");
        doNothing().when(menuController).killProgram();

        //testar se o movimento para cima funciona
        when(keyStroke.getCharacter()).thenReturn('w');
        menuController.run();
        verify(menuModel,times(1)).optUp();
        verify(menuModel,times(0)).optDown();
        verify(menuController,times(1)).killProgram();

        //testar se o movimento para baixo funciona
        when(keyStroke.getCharacter()).thenReturn('s');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        menuController.run();
        verify(menuModel,times(1)).optUp();
        verify(menuModel,times(1)).optDown();
        verify(menuController,times(2)).killProgram();

        //testar se o enter funciona
        when(keyStroke.getCharacter()).thenReturn(' ');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        menuController.run();
        verify(menuModel,times(1)).optUp();
        verify(menuModel,times(1)).optDown();
        verify(menuController,times(1)).chooseOption();
        verify(menuController,times(3)).killProgram();



        when(menuModel.getOnSubMenu()).thenReturn(true);

        //testar se o movimento para cima funciona no submenu
        when(keyStroke.getCharacter()).thenReturn('w');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        menuController.run();
        verify(menuModel,times(1)).subOptUp();
        verify(menuModel,times(0)).subOptDown();
        verify(menuController,times(4)).killProgram();

        //testar se o movimento para baixo funciona submenu
        when(keyStroke.getCharacter()).thenReturn('s');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character).thenReturn(KeyType.EOF);
        menuController.run();
        verify(menuModel,times(1)).subOptUp();
        verify(menuModel,times(1)).subOptDown();
        verify(menuController,times(5)).killProgram();

        //testar se o enter funciona no submenu
        when(keyStroke.getCharacter()).thenReturn(' ');
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        menuController.run();
        verify(menuModel,times(1)).subOptUp();
        verify(menuModel,times(1)).subOptDown();
        verify(menuModel,times(1)).setOnSubMenu(false);
        verify(menuController,times(6)).killProgram();

        //verificar se o jogo começa em medio com as opçoes 1:2
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

        //verificar se o jogo começa em facil com as opçoes 1:1
        when(menuModel.getSubOption()).thenReturn(1);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        doNothing().when(menuController).startNewGame();
        menuController.run();
        Assert.assertEquals(menuController.difficulties.size(),6);
        for(Difficulty d:menuController.difficulties){
            Assert.assertEquals(d.getClass(), Easy.class);
        }

        //verificar se o jogo começa em dificil com as opçoes 1:3

        when(menuModel.getSubOption()).thenReturn(3);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter).thenReturn(KeyType.EOF);
        doNothing().when(menuController).startNewGame();
        menuController.run();
        Assert.assertEquals(menuController.difficulties.size(),8);
        for(Difficulty d:menuController.difficulties){
            Assert.assertEquals(d.getClass(), Hard.class);
        }

        //verificar se o jogo acaba com as opcoes 3:any
        when(menuModel.getOnSubMenu()).thenReturn(false);
        when(menuModel.getOption()).thenReturn(3);
        when(menuModel.getSubOption()).thenReturn(2);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        doNothing().when(menuController).killProgram();
        when(menuController.chooseOption()).thenReturn(true);
        menuController.difficulties.add(null);
        menuController.run();
        verify(menuController,times(6)).killProgram();


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