package com.noclue.view.bomb;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.ExplosionListener;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class BombViewFireTest {
    BombViewFire bombViewFire;
    TextGraphics textGraphics;
    BombModel model;
    ArrayList<Position> explosionList;
    @Before
    public void setup(){
        model = Mockito.mock(BombModel.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        Position position = Mockito.mock(Position.class);

        when(model.getPosition()).thenReturn(position);
        when(position.getRealPosition()).thenReturn(position);
        when(position.getRealPosition().getX()).thenReturn(20);
        when(position.getRealPosition().getY()).thenReturn(20);
        explosionList = new ArrayList<>();
        explosionList.add(position);

        when(model.getExplosionList()).thenReturn(explosionList);
        bombViewFire = new BombViewFire(textGraphics,model);

    }

    @Test
    public void getTextGraphics() {
        Assert.assertEquals(bombViewFire.getTextGraphics(),textGraphics);
    }

    @Test
    public void getModel() {
        Assert.assertEquals(bombViewFire.getModel(),model);
    }

    @Test
    public void testDraw() {
    }

    @Test
    public void draw() {
        bombViewFire.draw(textGraphics,model);

        Mockito.verify(bombViewFire.getTextGraphics(),Mockito.times(1))
                .setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(textGraphics,times(1))
                .putString(
                        model.getExplosionList().get(0).getRealPosition().getX()+2
                        , model.getExplosionList().get(0).getRealPosition().getY()+1
                        , "++", BOLD
                );
    }
}