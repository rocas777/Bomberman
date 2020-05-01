package com.noclue;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class PositionTests {

    @Test
    public void testConstructor() {
        Position p1 = new Position(20, 19, 18, 17);

        Assert.assertEquals(18, p1.getX());
        Assert.assertEquals(17, p1.getY());

        try {
            Field tmp = Position.class.getDeclaredField("y_max");
            tmp.setAccessible(true);
            Assert.assertEquals(19, tmp.getInt(p1));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field tmp = Position.class.getDeclaredField("x_max");
            tmp.setAccessible(true);
            Assert.assertEquals(20, tmp.getInt(p1));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Position p2 = new Position(-4, -3, -2, -1);

        try {
            Field tmp = Position.class.getDeclaredField("y_max");
            tmp.setAccessible(true);
            Assert.assertEquals(tmp.getInt(p2), 1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field tmp = Position.class.getDeclaredField("x_max");
            tmp.setAccessible(true);
            Assert.assertEquals(tmp.getInt(p2), 1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(p2.getX(), 1);
        Assert.assertEquals(p2.getY(), 1);

        Position p3 = new Position(20, 20, 40, 40);

        Assert.assertEquals(p3.getX(), 20);
        Assert.assertEquals(p3.getY(), 20);

    }

    @Test
    public void testClone() {
        Position p = new Position(-4, -3, -2, -1);
        Assert.assertEquals(p, p.clone());
    }

    @Test
    public void testGetX() {
        Position p1 = new Position(20, 19, 18, 17);

        try {
            Field tmp = Position.class.getDeclaredField("x");
            tmp.setAccessible(true);
            Assert.assertEquals(18, tmp.getInt(p1));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Position p2 = new Position(20, 19, 40, 40);

        try {
            Field tmp = Position.class.getDeclaredField("x");
            tmp.setAccessible(true);
            Assert.assertEquals(20, tmp.getInt(p2));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetY() {
        Position p1 = new Position(20, 19, 18, 17);

        try {
            Field tmp = Position.class.getDeclaredField("y");
            tmp.setAccessible(true);
            Assert.assertEquals(17, tmp.getInt(p1));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Position p2 = new Position(20, 19, 40, 40);

        try {
            Field tmp = Position.class.getDeclaredField("y");
            tmp.setAccessible(true);
            Assert.assertEquals(19, tmp.getInt(p2));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSetY() {
        Position p1 = new Position(20, 19, 18, 17);
        p1.setY(40);

        Assert.assertEquals(17, p1.getY());

        p1.setY(-1);
        Assert.assertEquals(17, p1.getY());

        p1.setY(5);
        Assert.assertEquals(5, p1.getY());
    }

    @Test
    public void testSetX() {
        Position p1 = new Position(20, 19, 18, 17);
        p1.setX(40);

        Assert.assertEquals(18, p1.getX());

        p1.setX(-1);
        Assert.assertEquals(18, p1.getX());

        p1.setX(5);
        Assert.assertEquals(5, p1.getX());

    }

    @Test
    public void testGetRealPosition(){
        Position p1 = new Position(20, 19, 18, 17);

        Assert.assertEquals(p1.getX()*6,p1.getRealPosition().getX());
        Assert.assertEquals(p1.getY()*3,p1.getRealPosition().getY());

        try {
            Field tmp = Position.class.getDeclaredField("x_max");
            tmp.setAccessible(true);
            Assert.assertEquals(20*6+20, tmp.getInt(p1.getRealPosition()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field tmp = Position.class.getDeclaredField("y_max");
            tmp.setAccessible(true);
            Assert.assertEquals(19*3+20, tmp.getInt(p1.getRealPosition()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
