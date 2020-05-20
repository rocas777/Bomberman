package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.FieldModel;
import com.noclue.model.character.HeroModel;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;
import com.noclue.timer.TimerInterface;
import com.noclue.view.character.HeroView;
import com.noclue.IView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class HeroViewTest {

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
    HeroView heroView;
    HeroView m1;
    @Before
    public void setup(){
        HeroModel heroModel = Mockito.mock(HeroModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);
        FieldModel fieldModel = Mockito.mock(FieldModel.class);

        heroView = new HeroView(heroModel,textGraphics);
        CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(heroView);
        //when(fieldModel.getViews()).thenReturn(iViews);
        when(heroModel.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);

    }

    @Test
    public void draw() {
        heroView.draw(heroView.getTextGraphics(),heroView.getModel().getPosition());

        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0000ff"));
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .putString(heroView.getModel().getPosition().getRealPosition().getX(),heroView.getModel().getPosition().getRealPosition().getY(),"  ()  ",BOLD);
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .putString(heroView.getModel().getPosition().getRealPosition().getX(),heroView.getModel().getPosition().getRealPosition().getY()+1,"0=||=0",BOLD);
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .putString(heroView.getModel().getPosition().getRealPosition().getX(),heroView.getModel().getPosition().getRealPosition().getY()+2,"0=||=0",BOLD);
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
    }

    @Test
    public void drawI() {
        heroView.draw();

        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0000ff"));
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .putString(heroView.getModel().getPosition().getRealPosition().getX(),heroView.getModel().getPosition().getRealPosition().getY(),"  ()  ",BOLD);
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .putString(heroView.getModel().getPosition().getRealPosition().getX(),heroView.getModel().getPosition().getRealPosition().getY()+1,"0=||=0",BOLD);
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .putString(heroView.getModel().getPosition().getRealPosition().getX(),heroView.getModel().getPosition().getRealPosition().getY()+2,"0=||=0",BOLD);
        Mockito.verify(heroView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
    }
}