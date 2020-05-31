package com.noclue.view.field;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.noclue.controller.HeroController;
import com.noclue.controller.TileController;
import com.noclue.model.FieldModel;
import com.noclue.model.Grid;
import com.noclue.model.Position;
import com.noclue.model.character.HeroModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.SGR.BOLD;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FieldViewTest {
    Screen screen ;
    TextGraphics textGraphics ;
    FieldModel fieldModel;
    FieldView fieldView;
    Position position ;
    HeroController heroController;
    HeroModel heroModel ;

    @Before
    public void beforeSetup(){
        screen = Mockito.mock(Screen.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        fieldModel = spy(new FieldModel(100, 100, 1));
        fieldView = spy(new FieldView(screen, textGraphics, fieldModel));
        position = new Position(23, 15, 5, 5);
        heroController = Mockito.mock(HeroController.class);
        heroModel = Mockito.mock(HeroModel.class);

    }

    @Test
    public void drawSteps() {
        when(fieldModel.getHero()).thenReturn(heroController);
        when(heroController.getModel()).thenReturn(heroModel);

        when(heroModel.getTouchCounter()).thenReturn(-1);
        fieldView.drawSteps(textGraphics, position);
        verify(textGraphics).putString(position.getX(), position.getY() + 1, "   0", BOLD);

        when(heroModel.getTouchCounter()).thenReturn(0);
        fieldView.drawSteps(textGraphics, position);
        verify(textGraphics).putString(position.getX(), position.getY() + 1, "   " + (fieldModel.getHero().getModel().getTouchCounter() + 1), BOLD);

        when(heroModel.getTouchCounter()).thenReturn(1);
        fieldView.drawSteps(textGraphics, position);
        verify(textGraphics).putString(position.getX(), position.getY() + 1, "   " + (fieldModel.getHero().getModel().getTouchCounter() + 1), BOLD);
    }



    @Test
    public void draw() {
        Grid grid = new Grid();
        grid.add_column();
        grid.addTile();
        grid.add_column();
        grid.addTile();
        TileController tileController1 = Mockito.mock(TileController.class);
        TileController tileController2 = Mockito.mock(TileController.class);
        grid.getTiles().get(0).set(0,tileController1);
        grid.getTiles().get(1).set(0,tileController2);
        doNothing().when(fieldView).drawSteps(any(),any());
        doNothing().when(fieldView).drawLevel(any(),any());
        doNothing().when(fieldView).drawScore(any(),any());

        fieldView.draw(fieldModel,textGraphics,screen,grid);

        verify(tileController1).draw();
        verify(tileController2).draw();

    }
}