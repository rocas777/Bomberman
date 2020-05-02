package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
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

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
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
    MonsterView m1;
    @Before
    public void setup(){
        MonsterModel monsterModel = Mockito.mock(MonsterModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getX()).thenReturn(20);
        when(position.getY()).thenReturn(20);


        when(monsterModel.getPosition()).thenReturn(position);
        monsterView = new MonsterView(monsterModel,textGraphics);

        m1 = spy(monsterView);
        timer.addListener(m1);
        when(m1.getMonsterModel()).thenReturn(monsterModel);
    }

    @Test
    public void draw() {
        monsterView.draw(monsterView.getTextGraphics(),monsterView.getMonsterModel().getPosition());

        Mockito.verify(monsterView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(monsterView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(monsterView.getTextGraphics(),Mockito.times(1))
                .putString(monsterView.getMonsterModel().getPosition().getRealPosition().getX(),monsterView.getMonsterModel().getPosition().getRealPosition().getY(),"*(OO)*",BOLD);
        Mockito.verify(monsterView.getTextGraphics(),Mockito.times(1))
                .putString(monsterView.getMonsterModel().getPosition().getRealPosition().getX(),monsterView.getMonsterModel().getPosition().getRealPosition().getY()+1,"X=VV=X",BOLD);
        Mockito.verify(monsterView.getTextGraphics(),Mockito.times(1))
                .putString(monsterView.getMonsterModel().getPosition().getRealPosition().getX(),monsterView.getMonsterModel().getPosition().getRealPosition().getY()+2,"X=VV=X",BOLD);
        Mockito.verify(monsterView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
    }

    @Test
    public void updateOnTime() {
        timer.updateListeners(null);
        timer.updateListeners(null);
        timer.updateListeners(null);
        timer.updateListeners(null);
        timer.updateListeners(null);

        Mockito.verify(m1,Mockito.times(5))
                .updateOnTime();
    }
}