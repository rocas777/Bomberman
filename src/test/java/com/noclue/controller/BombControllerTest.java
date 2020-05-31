package com.noclue.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.ExplosionListener;
import com.noclue.controller.bomb.BombController;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;
import com.noclue.timer.Timer;
import com.noclue.timer.TimerInterface;
import com.noclue.view.bomb.BombViewTicking;
import com.noclue.IView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.mockito.Mockito.*;

public class BombControllerTest {
    BombModel bombModel;
    BombViewTicking bombViewTicking;
    BombController bombController;
    FieldController fieldController;
    Position position;
    int i=0;
    @Before
    public void setup(){
        bombModel = Mockito.mock(BombModel.class);
        bombViewTicking = Mockito.mock(BombViewTicking.class);
        fieldController = Mockito.mock(FieldController.class);
        position = new Position(10,10,5,5);
        TimerInterface ti = mock(TimerInterface.class);

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        when(bombModel.getMseconds()).thenReturn(1000);
        when(bombModel.getExplosionListener()).thenReturn(fieldController);
        when(bombModel.getSum()).thenReturn(i);
        when(bombModel.getPosition()).thenReturn(position);
        when(bombModel.getTimerInterface()).thenReturn(ti);


        bombController = new BombController(bombModel,textGraphics);
    }

    @Test
    public void updateOnTime() {
        Timer.setSeconds(50);
        IView tview = bombController.getViewTicking();
        System.out.println(bombModel.getMseconds());
        ExplosionListener explosionListener = Mockito.mock(ExplosionListener.class);
        when(bombModel.getExplosionListener()).thenReturn(explosionListener);

        //atualizar até à atualização anterior ao update explosion listener
        for(int i=0;i<19;i++){
            bombController.updateOnTime();
        }
        Mockito.verify(explosionListener, Mockito.times(0))
                .explode(any());
        Assert.assertEquals(tview,bombController.getView());

        //atualizar explosion lintener
        bombController.updateOnTime();
        Mockito.verify(explosionListener, Mockito.times(1))
                .explode(any());
        Assert.assertNotEquals(tview,bombController.getView());


        //atualizar até fire done
        for(int i=0;i<5;i++){
            Mockito.verify(explosionListener, Mockito.times(0))
                    .fireDone();
            bombController.updateOnTime();
        }
        Mockito.verify(explosionListener, Mockito.times(1))
                .fireDone();
    }

    @Test
    public void draw(){
        bombController.setView(bombViewTicking);
        bombController.draw();
        Mockito.verify(bombController.getView(),times(1))
                .draw();
    }
}