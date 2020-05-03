package com.noclue.timer;

import java.util.concurrent.CopyOnWriteArrayList;

public class Timer implements TimerInterface {
    private CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();

    private int mseconds;
    private boolean isOn;

    public CopyOnWriteArrayList<TimeListener> getTimeListeners() {
        return timeListeners;
    }

    public Timer(int mseconds){
        if(mseconds<=0)
            this.mseconds=1;
        else
            this.mseconds=mseconds;
        isOn=false;
    }

    public int getMSeconds(){
        return mseconds;
    }

    public void addListener(TimeListener timeListener){
            timeListeners.add(timeListener);
    }



    public void removeListener(TimeListener timeListener){
            timeListeners.remove(timeListener);
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

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
        for (TimeListener t : timeListeners)
            t.updateOnTime();
    }
}
