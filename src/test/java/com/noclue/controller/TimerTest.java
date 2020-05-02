package com.noclue.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

public class TimerTest {
    Timer timer;
    @Before
    public void setup(){
        timer = new Timer(20);
    }

    @Test
    public void getMSeconds() {
        Assert.assertEquals(20,timer.getMSeconds());

        timer = new Timer(20);
        Assert.assertEquals(20,timer.getMSeconds());

        timer = new Timer(10);
        Assert.assertEquals(10,timer.getMSeconds());

        timer = new Timer(0);
        Assert.assertEquals(1,timer.getMSeconds());

        timer = new Timer(-20);
        Assert.assertEquals(1,timer.getMSeconds());
    }

    @Test
    public void addListener() {
        FieldController f1 = Mockito.mock(FieldController.class);
        timer.addListener(f1);

        FieldController f2 = Mockito.mock(FieldController.class);
        timer.addListener(f2);

        FieldController f3 = Mockito.mock(FieldController.class);
        timer.addListener(f3);

        ArrayList tmp = new ArrayList();
        tmp.add(f1);
        tmp.add(f2);
        tmp.add(f3);

        Assert.assertEquals(tmp,timer.getTimeListeners());

        tmp = new ArrayList();
        tmp.add(f1);
        tmp.add(f1);
        tmp.add(f3);

        Assert.assertNotEquals(tmp,timer.getTimeListeners());

        tmp = new ArrayList();
        tmp.add(f1);
        tmp.add(f1);

        Assert.assertNotEquals(tmp,timer.getTimeListeners());
    }

    @Test
    public void removeListener() {
        FieldController f1 = Mockito.mock(FieldController.class);
        timer.addListener(f1);

        FieldController f2 = Mockito.mock(FieldController.class);
        timer.addListener(f2);

        FieldController f3 = Mockito.mock(FieldController.class);
        timer.addListener(f3);

        ArrayList tmp = new ArrayList();
        tmp.add(f1);
        tmp.add(f2);
        tmp.add(f3);

        Assert.assertEquals(tmp,timer.getTimeListeners());

        timer.removeListener(f3);

        Assert.assertNotEquals(tmp,timer.getTimeListeners());
    }

    @Test
    public void start() {
        FieldController fieldController = Mockito.mock(FieldController.class);

        int i=0;
        int u=0;

        // Execute
        Timer t=new Timer(1000);
        t.addListener(fieldController);
        t.start();

        while(i<5) {
            try {
                doThrow(Exception.class).when(fieldController).updateOnTime();
            } catch (Exception e) {
                u++;
            }
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

        Assert.assertEquals(u,5);
    }

    @Test
    public void stop() {
    }

    @Test
    public void updateListeners() {

    }
}