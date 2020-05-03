package com.noclue.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.FieldModel;
import com.noclue.model.block.IndestructibleBlockModel;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;
import com.noclue.timer.TimerInterface;
import com.noclue.view.block.IndestructibleBlockView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class IndestructibleBlockViewTest  {
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

    IndestructibleBlockViewTest.mockTimer timer = new IndestructibleBlockViewTest.mockTimer();
    IndestructibleBlockView indestructibleBlockView;
    IndestructibleBlockView m1;
    @Before
    public void setup(){
        IndestructibleBlockModel indestructibleBlockModel = Mockito.mock(IndestructibleBlockModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);
        FieldModel fieldModel = Mockito.mock(FieldModel.class);

        indestructibleBlockView = new IndestructibleBlockView(indestructibleBlockModel,textGraphics);
        CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(indestructibleBlockView);
        when(fieldModel.getViews()).thenReturn(iViews);
        when(indestructibleBlockModel.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);

    }

    @Test
    public void draw() {
        indestructibleBlockView.draw(indestructibleBlockView.getTextGraphics(),indestructibleBlockView.getModel().getPosition());

        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .putString(indestructibleBlockView.getModel().getPosition().getRealPosition().getX(),indestructibleBlockView.getModel().getPosition().getRealPosition().getY(),"+----+",BOLD);
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .putString(indestructibleBlockView.getModel().getPosition().getRealPosition().getX(),indestructibleBlockView.getModel().getPosition().getRealPosition().getY()+1,"|XXXX|",BOLD);
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .putString(indestructibleBlockView.getModel().getPosition().getRealPosition().getX(),indestructibleBlockView.getModel().getPosition().getRealPosition().getY()+2,"+----+",BOLD);

    }

    @Test
    public void drawI() {
        indestructibleBlockView.draw();

        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .putString(indestructibleBlockView.getModel().getPosition().getRealPosition().getX(),indestructibleBlockView.getModel().getPosition().getRealPosition().getY(),"+----+",BOLD);
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .putString(indestructibleBlockView.getModel().getPosition().getRealPosition().getX(),indestructibleBlockView.getModel().getPosition().getRealPosition().getY()+1,"|XXXX|",BOLD);
        Mockito.verify(indestructibleBlockView.getTextGraphics(),Mockito.times(1))
                .putString(indestructibleBlockView.getModel().getPosition().getRealPosition().getX(),indestructibleBlockView.getModel().getPosition().getRealPosition().getY()+2,"+----+",BOLD);

    }

}