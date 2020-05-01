package com.noclue;

import com.noclue.character.TimeListener;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class TimerTest {
    private
    class mockListener implements TimeListener{
        @Override
        public void updateOnTime() {
        }
    }


    @Test
    public void testConstructor(){
        Timer t=new Timer(20);
        Assert.assertEquals(t.getMSeconds(),20);
        try {
            Field tmp = Timer.class.getDeclaredField("isOn");
            tmp.setAccessible(true);
            Assert.assertEquals(tmp.getBoolean(t),false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveListener(){
        Timer t=new Timer(20);
        Field tmp = null;

        try {
            tmp = Timer.class.getDeclaredField("timeListeners");

            tmp.setAccessible(true);
            List<TimeListener> tmp_list =new CopyOnWriteArrayList<TimeListener>();

            mockListener m1=new mockListener();
            mockListener m2=new mockListener();
            mockListener m3=new mockListener();
            tmp_list.add(m1);
            if(!t.addListener(tmp_list.get(0)))
                Assert.fail();
            tmp_list.add(m2);
            if(!t.addListener(tmp_list.get(1)))
                Assert.fail();
            tmp_list.add(m3);
            if(!t.addListener(tmp_list.get(2)))
                Assert.fail();

            tmp_list.remove(m1);
            if(!t.removeListener(m1))
                Assert.fail();

            Assert.assertEquals(tmp.get(t), tmp_list);

            tmp_list.remove(m1);
            if(!t.removeListener(m1))
                Assert.fail();

            Assert.assertEquals(tmp.get(t), tmp_list);


            if(!t.removeListener(m3))
                Assert.fail();
            Assert.assertNotEquals(tmp.get(t), tmp_list);


            if(!t.removeListener(m2))
                Assert.fail();



        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddListener(){
        Timer t=new Timer(20);
        Field tmp = null;
        try {
            tmp = Timer.class.getDeclaredField("timeListeners");
            tmp.setAccessible(true);
            List<TimeListener> tmp_list =new CopyOnWriteArrayList<TimeListener>();

            Assert.assertEquals(tmp.get(t), tmp_list);

            mockListener m1=new mockListener();
            mockListener m2=new mockListener();
            mockListener m3=new mockListener();
            mockListener m4=new mockListener();

            tmp_list.add(m1);
            if(!t.addListener(m1))
                Assert.fail();
            Assert.assertEquals(tmp.get(t), tmp_list);

            tmp_list.add(m2);
            if(!t.addListener(m2))
                Assert.fail();

            tmp_list.add(m3);
            if(!t.addListener(m3))
                Assert.fail();


            if(!t.addListener(m4))
                Assert.fail();
            Assert.assertNotEquals(tmp.get(t), tmp_list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStart(){
        mockListener m1 = Mockito.mock(mockListener.class);

        int i=0;
        int u=0;

        // Execute
        Timer t=new Timer(1000);
        t.addListener(m1);
        t.start();

        while(i<5) {
            try {
                doThrow(Exception.class).when(m1).updateOnTime();
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



}
