package com.noclue.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuModelTest {

    @Test
    public void optUp() {
        MenuModel menuModel = new MenuModel(1,1);
        Assert.assertEquals(1,menuModel.getOption());

        menuModel = new MenuModel(1,1);
        menuModel.optUp();
        Assert.assertEquals(4,menuModel.getOption());

        menuModel = new MenuModel(2,1);
        menuModel.optUp();
        Assert.assertEquals(1,menuModel.getOption());
    }

    @Test
    public void optDown() {
        MenuModel menuModel = new MenuModel(1,1);
        Assert.assertEquals(1,menuModel.getOption());

        menuModel = new MenuModel(4,1);
        menuModel.optDown();
        Assert.assertEquals(1,menuModel.getOption());

        menuModel = new MenuModel(2,1);
        menuModel.optDown();
        Assert.assertEquals(3,menuModel.getOption());
    }

    @Test
    public void subOptUp() {
        MenuModel menuModel = new MenuModel(1,1);
        Assert.assertEquals(1,menuModel.getSubOption());

        menuModel = new MenuModel(4,1);
        menuModel.subOptUp();
        Assert.assertEquals(3,menuModel.getSubOption());

        menuModel = new MenuModel(2,3);
        menuModel.subOptUp();
        Assert.assertEquals(2,menuModel.getSubOption());
    }

    @Test
    public void subOptDown() {
        MenuModel menuModel = new MenuModel(1,1);
        Assert.assertEquals(1,menuModel.getSubOption());

        menuModel = new MenuModel(4,3);
        menuModel.subOptDown();
        Assert.assertEquals(1,menuModel.getSubOption());

        menuModel = new MenuModel(2,2);
        menuModel.subOptDown();
        Assert.assertEquals(3,menuModel.getSubOption());
    }
}