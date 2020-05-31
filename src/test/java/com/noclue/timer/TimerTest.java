package com.noclue.timer;

import com.noclue.controller.FieldController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        timer.setOn(false);

        timer = new Timer(10);
        Assert.assertEquals(10,timer.getMSeconds());
        timer.setOn(false);

        timer = new Timer(0);
        Assert.assertEquals(1,timer.getMSeconds());
        timer.setOn(false);

        timer = new Timer(-20);
        Assert.assertEquals(1,timer.getMSeconds());
        timer.setOn(false);
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
        timer.setOn(false);
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
        timer.setOn(false);
    }

    @Test
    public void start() {
        FieldController fieldController = Mockito.mock(FieldController.class);

        int i=0;
        int u=0;

        // Execute
        Timer t=spy(new Timer(1000));
        doNothing().when(t).updateListeners(any());
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
        Assert.assertEquals(t.isOn(),true);
        t.stop();
        Assert.assertEquals(u,5);
        Assert.assertEquals(t.isOn(),false);
    }

    @Test
    public void updateListeners() {
        CopyOnWriteArrayList<TimeListener> listener = new CopyOnWriteArrayList();
        TimeListener timeListener = Mockito.mock(TimeListener.class);
        listener.add(timeListener);
        timer.updateListeners(listener);
        Mockito.verify(timeListener,times(1))
                .updateOnTime();
    }
    @Test
    public void constructor(){
        Timer timer = new Timer(0);
        Assert.assertEquals(timer.getMSeconds(),1);
        timer = new Timer(-3);
        Assert.assertEquals(timer.getMSeconds(),1);
        timer.setOn(false);
    }
}