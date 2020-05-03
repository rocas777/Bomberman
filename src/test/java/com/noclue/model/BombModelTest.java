package com.noclue.model;

import com.noclue.ExplosionListener;
import com.noclue.timer.TimerInterface;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BombModelTest {
    TimerInterface timerInterface = Mockito.mock(TimerInterface.class);
    Position position = Mockito.mock(Position.class);
    ExplosionListener explosionListener = Mockito.mock(ExplosionListener.class);
    BombModel bombModel = new BombModel(20,explosionListener,position,timerInterface);
    ArrayList<Position> explosionListeners = new ArrayList<>();

    @Test
    public void getTimerInterface() {
        Assert.assertEquals(bombModel.getTimerInterface(),timerInterface);
    }

    @Test
    public void setExplosionList() {
        explosionListeners.add(position);
        bombModel.setExplosionList(explosionListeners);
        Assert.assertEquals(bombModel.getExplosionList(),explosionListeners);
    }

    @Test
    public void getExplosionList() {
        explosionListeners.add(position);
        bombModel.setExplosionList(explosionListeners);
        Assert.assertEquals(bombModel.getExplosionList(),explosionListeners);
    }

    @Test
    public void getSum() {
        bombModel.setSum(20);
        Assert.assertEquals(bombModel.getSum(),20);
    }

    @Test
    public void setSum() {
        bombModel.setSum(20);
        Assert.assertEquals(bombModel.getSum(),20);
    }

    @Test
    public void getMseconds() {
        Assert.assertEquals(bombModel.getMseconds(),20);
    }

    @Test
    public void getExplosionListener() {
        Assert.assertEquals(bombModel.getExplosionListener(),explosionListener);
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(bombModel.getPosition(),position);
    }
}