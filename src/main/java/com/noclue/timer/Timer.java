package com.noclue.timer;

import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;

public class Timer implements TimerInterface {
    private CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();

    private static int mseconds;
    private boolean isOn;
    Thread thread;

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

    public static  int getSeconds(){
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
            long timeMilli2;
            while (isOn) {
                try {
                    timeMilli2 = System.currentTimeMillis();
                    updateListeners(timeListeners);
                    if(mseconds<1) {
                        return;
                    }
                    long wait = mseconds+timeMilli2-System.currentTimeMillis();
                    if(wait>0)
                        java.lang.Thread.sleep(wait);
                    else
                        java.lang.Thread.sleep(1);
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

    public void stop(){
        isOn = false;
    }

    public void resume(){
        isOn = true;
    }

    @Override
    public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
        for (TimeListener t : timeListeners)
            t.updateOnTime();
    }
}
