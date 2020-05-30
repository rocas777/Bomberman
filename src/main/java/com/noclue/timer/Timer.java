package com.noclue.timer;

import java.util.concurrent.CopyOnWriteArrayList;

public class Timer implements TimerInterface {
    private static int mseconds;
    Boolean isOn;
    Thread thread;
    private CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();

    public Timer(int mseconds) {
        if (mseconds <= 0)
            Timer.mseconds = 1;
        else
            Timer.mseconds = mseconds;
        isOn = false;
    }

    public static int getSeconds() {
        return mseconds;
    }

    public static void setSeconds(int seconds) {
        mseconds = seconds;
    }

    public CopyOnWriteArrayList<TimeListener> getTimeListeners() {
        return timeListeners;
    }

    public int getMSeconds() {
        return mseconds;
    }

    public void addListener(TimeListener timeListener) {
        timeListeners.add(timeListener);
    }

    public void removeListener(TimeListener timeListener) {
        timeListeners.remove(timeListener);
    }

    public void start() {
        isOn = true;
        thread = new Thread(() -> {
            long timeMilli2;
            while (isOn) {
                try {
                    timeMilli2 = System.currentTimeMillis();
                    updateListeners(timeListeners);
                    if (mseconds < 1) {
                        return;
                    }
                    long wait = mseconds + timeMilli2 - System.currentTimeMillis();
                    if (wait > 0)
                        java.lang.Thread.sleep(wait);
                    else
                        java.lang.Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void stop() {
        isOn = false;
    }

    public void resume() {
        isOn = true;
    }

    @Override
    public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
        for (TimeListener t : timeListeners)
            t.updateOnTime();
    }

    public void removeListeners() {
        timeListeners = new CopyOnWriteArrayList<>();
    }
}
