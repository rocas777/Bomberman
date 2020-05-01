package com.noclue;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.noclue.character.TimeListener;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static java.lang.Thread.sleep;
import static org.mockito.Mockito.*;

public class BombTest {
    private class mockListener implements ExplosionListener{

        @Override
        public void explode(Position position) {

        }
    }
    private class timerMock implements TimerInterface{
        CopyOnWriteArrayList<TimeListener> listeners = new CopyOnWriteArrayList<>();

        @Override
        public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
            for(TimeListener t:listeners)
                t.updateOnTime();
        }

        @Override
        public boolean addListener(TimeListener timeListener) {
            listeners.add(timeListener);
            return true;
        }

        @Override
        public boolean removeListener(TimeListener timeListener) {
            listeners.remove(timeListener);
            return true;
        }

        @Override
        public void stop() {

        }

        @Override
        public void start() {

        }

        @Override
        public int getMSeconds() {
            return 20;
        }
    }


    @Test
    public void testConstructor(){
        Position p1 = Mockito.mock(Position.class);
        timerMock t1 = new timerMock();
        mockListener m1 = new mockListener();

        Bomb b=new Bomb(20,m1,p1,t1);
        try {
            Field tmp = Bomb.class.getDeclaredField("mseconds");
            tmp.setAccessible(true);
            Assert.assertEquals(tmp.getInt(b),20);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field tmp = Bomb.class.getDeclaredField("explosionListener");
            tmp.setAccessible(true);
            Assert.assertEquals((mockListener) tmp.get(b),m1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field tmp = Bomb.class.getDeclaredField("position");
            tmp.setAccessible(true);
            Assert.assertEquals(tmp.get(b),p1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field tmp = Bomb.class.getDeclaredField("timer");
            tmp.setAccessible(true);
            Assert.assertEquals(tmp.get(b),t1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateOnTime(){


        Position p1 = Mockito.mock(Position.class);
        mockListener m1;
        timerMock t1 = new timerMock();
        m1 = Mockito.mock(mockListener.class);

        Bomb b=new Bomb(100,m1,p1,t1);

        t1.updateListeners(t1.listeners);
        t1.updateListeners(t1.listeners);
        t1.updateListeners(t1.listeners);
        t1.updateListeners(t1.listeners);
        t1.updateListeners(t1.listeners);
        t1.updateListeners(t1.listeners);

        Mockito.verify(m1,Mockito.times(1)).explode(p1);

    }

    @Test
    public void testDraw(){

        Position p1 = Mockito.mock(Position.class);
        when(p1.getRealPosition()).thenReturn(new Position(20,20,10,10));
        mockListener m1;
        timerMock t1 = new timerMock();
        m1 = Mockito.mock(mockListener.class);

        Bomb b=new Bomb(100,m1,p1,t1);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        b.draw(textGraphics);

        Mockito.verify(textGraphics, Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(p1.getRealPosition().getX()+2,p1.getRealPosition().getY()+1,"++",BOLD);


    }

}
