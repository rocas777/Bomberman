package com.noclue.view.bomb;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.view.bomb.BombViewTicking;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.mockito.Mockito.when;

public class BombViewTickingTest {
    BombViewTicking bombViewTicking;
    TextGraphics textGraphics;

    @Before
    public void setup(){
        BombModel model = Mockito.mock(BombModel.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);

        when(model.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);
        bombViewTicking = new BombViewTicking(textGraphics,model);

    }

    @Test
    public void draw() {
        bombViewTicking.draw(bombViewTicking.getTextGraphics(), bombViewTicking.getModel());

        Mockito.verify(bombViewTicking.getTextGraphics(),Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(bombViewTicking.getTextGraphics(),Mockito.times(1))
                .putString(bombViewTicking.getModel().getPosition().getRealPosition().getX()+2
                        , bombViewTicking.getModel().getPosition().getRealPosition().getY()+1
                        ,"++",BOLD
                );

    }
}