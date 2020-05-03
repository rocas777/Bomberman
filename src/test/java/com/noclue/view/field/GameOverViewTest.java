package com.noclue.view.field;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.IView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class GameOverViewTest {
    GameOverView view;
    Screen screen = Mockito.mock(Screen.class);
    TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
    @Before
    public void setup(){
        view= new GameOverView(screen, textGraphics);
    }

    @Test
    public void getTextGraphics() {
        Assert.assertEquals(view.getTextGraphics(),textGraphics);
    }

    @Test
    public void setTextGraphics() {
        Assert.assertEquals(view.getTextGraphics(),textGraphics);
        TextGraphics t1 = Mockito.mock(TextGraphics.class);
        view.setTextGraphics(t1);
        Assert.assertEquals(view.getTextGraphics(),t1);
    }

    @Test
    public void getScreen() {
        Assert.assertEquals(view.getScreen(),screen);
    }

    @Test
    public void draw() {
        view.draw();

        Mockito.verify(textGraphics,Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(textGraphics,Mockito.times(1))
                .fillRectangle(new TerminalPosition(view.getWidth()/2-34, view.getHeight()/2-12), new TerminalSize(68, 24), ' ');

        Mockito.verify(textGraphics,Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ffffff"));

        Mockito.verify(textGraphics,Mockito.times(1))
                .fillRectangle(new TerminalPosition(view.getWidth()/2-30, view.getHeight()/2-10), new TerminalSize(60, 20), ' ');

        Mockito.verify(textGraphics,Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ff00ff"));


        Mockito.verify(textGraphics,Mockito.times(1))
                .putString(46,22,"YOU HAVE LOST!!! I didn't know that was even possible!");

    }
}