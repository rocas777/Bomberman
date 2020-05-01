package com.noclue;

import com.noclue.character.TimeListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface TimerInterface {
    void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners);
    boolean addListener(TimeListener timeListener);
    boolean removeListener(TimeListener timeListener);
    void stop();
    void start();
    int getMSeconds();
}
