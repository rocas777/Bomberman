package com.noclue.controller;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
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

    public Timer(int mseconds){
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
        if(screen!=null)
            screen.clear();
        for (TimeListener t : timeListeners)
            t.updateOnTime();
        try {
            if(screen!=null)
                screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
