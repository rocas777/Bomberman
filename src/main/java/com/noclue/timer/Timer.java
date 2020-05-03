package com.noclue.timer;

import com.googlecode.lanterna.screen.Screen;

import java.util.concurrent.CopyOnWriteArrayList;

public class Timer implements TimerInterface {
    Screen screen=null;
    private CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();

    private int mseconds;
    private boolean isOn;
    public Timer(int mseconds,Screen screen){
        this.mseconds=mseconds;
        isOn=false;
        this.screen=screen;
    }

    public CopyOnWriteArrayList<TimeListener> getTimeListeners() {
        return timeListeners;
    }

    public Timer(int mseconds){
        if(mseconds<1)
            this.mseconds=1;
        else
            this.mseconds=mseconds;
        isOn=false;
    }

    public int getMSeconds(){
        return mseconds;
    }

    public boolean addListener(TimeListener timeListener){
            timeListeners.add(timeListener);
            return true;
    }



    public boolean removeListener(TimeListener timeListener){
            timeListeners.remove(timeListener);
            return true;
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
                    java.lang.Thread.sleep(20);
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
