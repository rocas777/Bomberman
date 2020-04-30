package com.noclue;

import com.noclue.character.TimeListener;

import java.util.ArrayList;

public class Timer {
    static private ArrayList<TimeListener> timeListeners=new ArrayList<>();
    static private int mseconds;
    boolean isOn;
    Timer(int mseconds){
        Timer.mseconds=mseconds;
        isOn=false;
    }

    static public void addListener(TimeListener timeListener){
        timeListeners.add(timeListener);
    }

    public void start(){
        isOn=true;
        new Thread(() -> {
            while (isOn) {
                try {
                    for(TimeListener t:timeListeners)
                        t.updateOnTime();
                    Thread.sleep(mseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public void stop(){
        isOn=false;
    }
}
