package com.noclue.collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.collectible.CoinModel;
import com.noclue.model.FieldModel;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;
import com.noclue.timer.TimerInterface;
import com.noclue.view.collectible.CoinView;
import com.noclue.IView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class CoinViewTest {
    private class mockTimer implements TimerInterface {
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

    CoinViewTest.mockTimer timer = new CoinViewTest.mockTimer();
    CoinView coinView;
    CoinView m1;
    @Before
    public void setup(){
        CoinModel coinModel = Mockito.mock(CoinModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);
        FieldModel fieldModel = Mockito.mock(FieldModel.class);

        coinView = new CoinView(coinModel,textGraphics);
        CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(coinView);
        when(fieldModel.getViews()).thenReturn(iViews);
        when(coinModel.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);

    }

    @Test
    public void draw() {
        coinView.draw(coinView.getTextGraphics(),coinView.getModel().getPosition());

        Mockito.verify(coinView.getTextGraphics(),Mockito.times(1))
                .putString(coinView.getModel().getPosition().getRealPosition().getX(),coinView.getModel().getPosition().getRealPosition().getY(),"  00  ",BOLD);
        Mockito.verify(coinView.getTextGraphics(),Mockito.times(1))
                .putString(coinView.getModel().getPosition().getRealPosition().getX(),coinView.getModel().getPosition().getRealPosition().getY()+1," 0000 ",BOLD);
        Mockito.verify(coinView.getTextGraphics(),Mockito.times(1))
                .putString(coinView.getModel().getPosition().getRealPosition().getX(),coinView.getModel().getPosition().getRealPosition().getY()+2,"  00  ",BOLD);

    }

    @Test
    public void drawI() {
        coinView.draw();

        Mockito.verify(coinView.getTextGraphics(),Mockito.times(1))
                .putString(coinView.getModel().getPosition().getRealPosition().getX(),coinView.getModel().getPosition().getRealPosition().getY(),"  00  ",BOLD);
        Mockito.verify(coinView.getTextGraphics(),Mockito.times(1))
                .putString(coinView.getModel().getPosition().getRealPosition().getX(),coinView.getModel().getPosition().getRealPosition().getY()+1," 0000 ",BOLD);
        Mockito.verify(coinView.getTextGraphics(),Mockito.times(1))
                .putString(coinView.getModel().getPosition().getRealPosition().getX(),coinView.getModel().getPosition().getRealPosition().getY()+2,"  00  ",BOLD);

    }

}