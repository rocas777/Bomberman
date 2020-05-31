package com.noclue.timer;

import java.util.concurrent.CopyOnWriteArrayList;

public class Timer implements TimerInterface {
    private static int mseconds;
    private Boolean isOn;
    private Thread thread;
    private CopyOnWriteArrayList<TimeListener> timeListeners = new CopyOnWriteArrayList<TimeListener>();

    public Timer(int mseconds) {
        if (mseconds <= 0)
            Timer.setMseconds(1);
        else
            Timer.setMseconds(mseconds);
        isOn = false;
    }

    public static int getSeconds() {
        return getMseconds();
    }

    public static void setSeconds(int seconds) {
        setMseconds(seconds);
    }

    public static int getMseconds() {
        return mseconds;
    }

    public static void setMseconds(int mseconds) {
        Timer.mseconds = mseconds;
    }

    public CopyOnWriteArrayList<TimeListener> getTimeListeners() {
        return timeListeners;
    }

    public void setTimeListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
        this.timeListeners = timeListeners;
    }

    public int getMSeconds() {
        return getMseconds();
    }

    public void addListener(TimeListener timeListener) {
        getTimeListeners().add(timeListener);
    }

    public void removeListener(TimeListener timeListener) {
        getTimeListeners().remove(timeListener);
    }

    public void start() {
        isOn = true;
        setThread(new Thread(() -> {
            long timeMilli2;
            while (getOn()) {
                try {
                    timeMilli2 = System.currentTimeMillis();    //track execution time to subtract from sleep
                    updateListeners(getTimeListeners());
                    if (getMseconds() < 1) {
                        return;
                    }
                    long wait = getMseconds() + timeMilli2 - System.currentTimeMillis(); //adjust sleep time
                    if (wait > 0)
                        Thread.sleep(wait);
                    else
                        Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
        getThread().start();
    }

    public boolean isOn() {
        return getOn();
    }

    public void stop() {
        isOn = false;
    }

    @Override
    public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) { //notify listeners
        for (TimeListener t : timeListeners)
            t.updateOnTime();
    }

    public void removeListeners() {
        setTimeListeners(new CopyOnWriteArrayList<>());
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
