package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.FieldModel;
import com.noclue.model.character.MonsterModel;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;
import com.noclue.timer.TimerInterface;
import com.noclue.view.character.MonsterView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class MonsterViewTest {
    private class mockTimer implements TimerInterface {
        TimeListener timeListener;
        @Override
        public void updateListeners(CopyOnWriteArrayList<TimeListener> timeListeners) {
            timeListener.updateOnTime();
        }

        @Override
        public void addListener(TimeListener timeListener) {
            this.timeListener = timeListener;
        }

        @Override
        public void removeListener(TimeListener timeListener) {
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
        FieldModel fieldModel = Mockito.mock(FieldModel.class);

        monsterView = new MonsterView(monsterModel,textGraphics);
        CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(monsterView);
        //when(fieldModel.getViews()).thenReturn(iViews);
        when(monsterModel.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);

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
    public void drawI() {
        monsterView.draw();

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

}