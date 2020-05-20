package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.collectible.DoorModel;
import com.noclue.model.FieldModel;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;
import com.noclue.timer.TimerInterface;
import com.noclue.view.collectible.DoorView;
import com.noclue.IView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class DoorViewTest {
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

    DoorViewTest.mockTimer timer = new DoorViewTest.mockTimer();
    DoorView doorView;
    DoorView m1;
    @Before
    public void setup(){
        DoorModel doorModel = Mockito.mock(DoorModel.class);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);
        FieldModel fieldModel = Mockito.mock(FieldModel.class);

        doorView = new DoorView(doorModel,textGraphics);
        CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> iViews = new CopyOnWriteArrayList<>();
        iViews.add(new CopyOnWriteArrayList<IView>());
        iViews.get(0).add(doorView);
        //when(fieldModel.getViews()).thenReturn(iViews);
        when(doorModel.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);

    }

    @Test
    public void draw() {
        doorView.draw(doorView.getTextGraphics(),doorView.getModel().getPosition());

        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ffFF00"));
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .putString(doorView.getModel().getPosition().getRealPosition().getX(),doorView.getModel().getPosition().getRealPosition().getY()," 0000 ",BOLD);
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .putString(doorView.getModel().getPosition().getRealPosition().getX(),doorView.getModel().getPosition().getRealPosition().getY()+1,"000000",BOLD);
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .putString(doorView.getModel().getPosition().getRealPosition().getX(),doorView.getModel().getPosition().getRealPosition().getY()+2,"000000",BOLD);

    }

    @Test
    public void drawI() {
        doorView.draw();

        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .setBackgroundColor(TextColor.Factory.fromString("#ffFF00"));
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(2))
                .setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .putString(doorView.getModel().getPosition().getRealPosition().getX(),doorView.getModel().getPosition().getRealPosition().getY()," 0000 ",BOLD);
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .putString(doorView.getModel().getPosition().getRealPosition().getX(),doorView.getModel().getPosition().getRealPosition().getY()+1,"000000",BOLD);
        Mockito.verify(doorView.getTextGraphics(),Mockito.times(1))
                .putString(doorView.getModel().getPosition().getRealPosition().getX(),doorView.getModel().getPosition().getRealPosition().getY()+2,"000000",BOLD);

    }

}