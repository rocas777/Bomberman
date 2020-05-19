package com.noclue.model;

import com.noclue.IBombInterface;
import com.noclue.IView;
import com.noclue.model.character.MonsterModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

public class FieldModelTest {
    CopyOnWriteArrayList<CopyOnWriteArrayList<IView>> views = new CopyOnWriteArrayList<>();
    FieldModel fieldModel = new FieldModel(40,20);
    IView view = Mockito.mock(IView.class);
    Position position = Mockito.mock(Position.class);
    MonsterModel monsterModel = Mockito.mock(MonsterModel.class);
    CopyOnWriteArrayList<Position> monster = new CopyOnWriteArrayList<>();
    IBombInterface bombInterface = Mockito.mock(IBombInterface.class);

    @Before
    public void setup(){
        views.add(new CopyOnWriteArrayList<IView>());
        views.get(0).add(view);
        monster.add(position);
    }

    @Test
    public void getViews() {
        fieldModel.setViews(views);
        Assert.assertEquals(fieldModel.getViews(),views);
        Assert.assertNotEquals(fieldModel.getViews(),null);
    }

    @Test
    public void setMonsters() {
        fieldModel.setMonsters(monster);
        Assert.assertEquals(fieldModel.getMonsters(),monster);
        Assert.assertNotEquals(fieldModel.getMonsters(),null);
    }

    @Test
    public void setViews() {
        fieldModel.setViews(views);
        Assert.assertEquals(fieldModel.getViews(),views);
        Assert.assertNotEquals(fieldModel.getViews(),null);
    }

    @Test
    public void setkServer() {
    }

    @Test
    public void settServer() {
    }

    @Test
    public void getHeight() {
        Assert.assertEquals(fieldModel.getHeight(),20);
    }

    @Test
    public void getWidth() {
        Assert.assertEquals(fieldModel.getWidth(),40);
    }

    @Test
    public void getTiles() {
    }

    @Test
    public void getMonsters() {
        fieldModel.setMonsters(monster);
        Assert.assertEquals(fieldModel.getMonsters(),monster);
        Assert.assertNotEquals(fieldModel.getMonsters(),null);
    }

    @Test
    public void getBomb() {
        fieldModel.setBombModel(bombInterface);
        Assert.assertEquals(fieldModel.getBomb(),bombInterface);
        Assert.assertNotEquals(fieldModel.getBomb(),null);
    }

    @Test
    public void getkServer() {
    }

    @Test
    public void getHero_pos() {
        fieldModel.setHero(position);
        Assert.assertEquals(fieldModel.getHero(),position);
    }

    @Test
    public void gettServer() {
    }

    @Test
    public void setHero_pos() {
        fieldModel.setHero(position);
        Assert.assertEquals(fieldModel.getHero(),position);
    }

    @Test
    public void setBombModel() {
        fieldModel.setBombModel(bombInterface);
        Assert.assertEquals(fieldModel.getBomb(),bombInterface);
        Assert.assertNotEquals(fieldModel.getBomb(),null);
    }

    @Test
    public void checkPos() {
    }
}