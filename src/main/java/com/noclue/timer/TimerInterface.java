package com.noclue.timer;

import java.util.concurrent.CopyOnWriteArrayList;

public interface TimerInterface {
    void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners);
    void addListener(TimeListener timeListener);
    void removeListener(TimeListener timeListener);

    void start();
    int getMSeconds();
}
