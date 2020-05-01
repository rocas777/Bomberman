package com.noclue;

import com.noclue.character.TimeListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Timer implements TimerInterface{
    private CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();

    private int mseconds;
    private boolean isOn;
    Timer(int mseconds){
        this.mseconds=mseconds;
        isOn=false;
    }
    public int getMSeconds(){
        return mseconds;
    }

    public boolean addListener(TimeListener timeListener){
        try {
            timeListeners.add(timeListener);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }



    public boolean removeListener(TimeListener timeListener){
        try {
            timeListeners.remove(timeListener);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void start(){
        isOn=true;
        new Thread(() -> {
            while (isOn) {
                try {
                    updateListeners(timeListeners);
                    if(mseconds<1) {
                        return;
                    }
                    java.lang.Thread.sleep(mseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop(){
        isOn=false;
    }

    @Override
    public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
        for (TimeListener t : timeListeners)
            t.updateOnTime();
    }
}
