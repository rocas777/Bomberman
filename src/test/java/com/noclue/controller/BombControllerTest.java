package com.noclue.controller;

import com.noclue.model.BombModel;
import com.noclue.model.FieldModel;
import com.noclue.model.Position;
import com.noclue.view.BombView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
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
    BombView bombView;
    BombController bombController;
    FieldController fieldController;
    Position position;
    int i=0;
    @Before
    public void setup(){
        timer = new mockTimer();
        bombModel = Mockito.mock(BombModel.class);
        bombView = Mockito.mock(BombView.class);
        fieldController = Mockito.mock(FieldController.class);
        position = Mockito.mock(Position.class);

        when(bombModel.getMseconds()).thenReturn(100);
        when(bombModel.getTimer()).thenReturn(timer);
        when(bombModel.getExplosionListener()).thenReturn(fieldController);
        when(bombModel.getSum()).thenReturn(i);
        when(bombModel.getPosition()).thenReturn(position);

        bombController = new BombController(bombModel,bombView);

    }

    @Test
    public void updateOnTime() {

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

        Mockito.verify(bombModel, Mockito.times(1))
                .getExplosionListener();
        Mockito.verify(bombView,Mockito.times(6))
                .draw();
    }
}