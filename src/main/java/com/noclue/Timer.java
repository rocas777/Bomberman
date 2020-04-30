package com.noclue;

import com.noclue.character.TimeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Timer {
    static CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>(); ;

    static private int mseconds;
    boolean isOn;
    Timer(int mseconds){
        Timer.mseconds=mseconds;
        isOn=false;
    }
    static public int getMSeconsd(){
        return mseconds;
    }

    static public void addListener(TimeListener timeListener){
        try {
            timeListeners.add(timeListener);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    static public void removeListener(TimeListener timeListener){
        try {
            timeListeners.remove(timeListener);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void start(){
        isOn=true;
        new Thread(() -> {
            while (isOn) {
                try {
                    for (TimeListener t : timeListeners)
                        t.updateOnTime();

                    java.lang.Thread.sleep(mseconds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop(){
        isOn=false;
    }
}
