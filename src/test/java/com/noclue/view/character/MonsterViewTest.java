package com.noclue.view.character;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.controller.TimeListener;
import com.noclue.controller.Timer;
import com.noclue.controller.TimerInterface;
import com.noclue.model.Position;
import com.noclue.model.character.MonsterModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MonsterViewTest {
    private class mockTimer implements TimerInterface{
        TimeListener timeListener;
        @Override
        public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
            timeListener.updateOnTime();
        }

        @Override
        public boolean addListener(TimeListener timeListener) {
            this.timeListener = timeListener;
            return true;
        }

        @Override
        public boolean removeListener(TimeListener timeListener) {
            return false;
        }

        @Override
        public void stop() {

        }

        @Override
        public void start() {

        }

        @Override
        public int getMSeconds() {
            return 20;
        }
    }

    mockTimer timer = new mockTimer();
    MonsterView monsterView;
    @Before
    public void setup(){
        MonsterModel monsterModel = Mockito.mock(MonsterModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);

        when(monsterModel.getPosition()).thenReturn(position);
        monsterView = new MonsterView(monsterModel,textGraphics);
        timer.addListener(monsterView);
    }

    @Test
    public void draw() {
    }

    @Test
    public void updateOnTime() {
        timer.updateListeners(null);
        timer.updateListeners(null);
        timer.updateListeners(null);
        timer.updateListeners(null);
        timer.updateListeners(null);

        Mockito.verify(monsterView,Mockito.times(5))
                .draw(monsterView.getTextGraphics(),monsterView.getMonsterModel().getPosition());
    }
}