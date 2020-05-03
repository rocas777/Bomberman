package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.view.BombViewTicking;
import com.noclue.view.IView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Mockito.when;

public class BombControllerTest {
    private class mockTimer implements TimerInterface{
        CopyOnWriteArrayList<TimeListener> timeListeners=new CopyOnWriteArrayList<TimeListener>();

        @Override
        public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
            for(TimeListener i:timeListeners){
                i.updateOnTime();
            }
        }

        public CopyOnWriteArrayList<TimeListener> getTimeListeners() {
            return timeListeners;
        }

        @Override
        public boolean addListener(TimeListener timeListener) {
            timeListeners.add(timeListener);
            return false;
        }

        @Override
        public boolean removeListener(TimeListener timeListener) {
            timeListeners.remove(timeListener);
            return false;
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

    mockTimer timer;
    BombModel bombModel;
    BombViewTicking bombViewTicking;
    BombController bombController;
    FieldController fieldController;
    Position position;
    int i=0;
    @Before
    public void setup(){
        timer = new mockTimer();
        bombModel = Mockito.mock(BombModel.class);
        bombViewTicking = Mockito.mock(BombViewTicking.class);
        fieldController = Mockito.mock(FieldController.class);
        position = Mockito.mock(Position.class);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        when(bombModel.getMseconds()).thenReturn(100);
        when(bombModel.getExplosionListener()).thenReturn(fieldController);
        when(bombModel.getSum()).thenReturn(i);
        when(bombModel.getPosition()).thenReturn(position);

        bombController = new BombController(bombModel,textGraphics);
    }

    @Test
    public void updateOnTime() {
        IView view = bombController.getView();

        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;
        when(bombModel.getSum()).thenReturn(i);
        timer.updateListeners(timer.getTimeListeners());
        i++;

        System.out.println(i);
        System.out.println(bombModel.getSum()*20);
        when(bombModel.getSum()).thenReturn(i);

        Mockito.verify(bombModel, Mockito.times(1))
                .getExplosionListener().explode(bombModel.getPosition());
        Mockito.verify(bombViewTicking,Mockito.times(6))
                .draw(bombViewTicking.getTextGraphics(),bombModel);

        Assert.assertNotEquals(view,bombController.getView());
    }
}