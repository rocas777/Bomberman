package com.noclue.view.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.FieldModel;
import com.noclue.model.Position;
import com.noclue.model.block.RemovableBlockModel;
import com.noclue.IView;
import com.noclue.timer.TimeListener;
import com.noclue.timer.TimerInterface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class RemovableBlockViewTest  {
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

    RemovableBlockViewTest.mockTimer timer = new RemovableBlockViewTest.mockTimer();
    RemovableBlockView removableBlockView;
    RemovableBlockView m1;
    @Before
    public void setup(){
        RemovableBlockModel removableBlockModel = Mockito.mock(RemovableBlockModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);
        FieldModel fieldModel = Mockito.mock(FieldModel.class);

        removableBlockView = new RemovableBlockView(removableBlockModel,textGraphics);
        CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(removableBlockView);
        when(fieldModel.getViews()).thenReturn(iViews);
        when(removableBlockModel.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);

    }

    @Test
    public void draw() {
        removableBlockView.draw(removableBlockView.getTextGraphics(),removableBlockView.getModel().getPosition());

        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .putString(removableBlockView.getModel().getPosition().getRealPosition().getX(),removableBlockView.getModel().getPosition().getRealPosition().getY(),"######",BOLD);
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .putString(removableBlockView.getModel().getPosition().getRealPosition().getX(),removableBlockView.getModel().getPosition().getRealPosition().getY()+1,"######",BOLD);
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .putString(removableBlockView.getModel().getPosition().getRealPosition().getX(),removableBlockView.getModel().getPosition().getRealPosition().getY()+2,"######",BOLD);

    }

    @Test
    public void drawI() {
        removableBlockView.draw();

        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .putString(removableBlockView.getModel().getPosition().getRealPosition().getX(),removableBlockView.getModel().getPosition().getRealPosition().getY(),"######",BOLD);
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .putString(removableBlockView.getModel().getPosition().getRealPosition().getX(),removableBlockView.getModel().getPosition().getRealPosition().getY()+1,"######",BOLD);
        Mockito.verify(removableBlockView.getTextGraphics(),Mockito.times(1))
                .putString(removableBlockView.getModel().getPosition().getRealPosition().getX(),removableBlockView.getModel().getPosition().getRealPosition().getY()+2,"######",BOLD);

    }
}