package com.noclue.model;

import com.noclue.Movement;
import com.noclue.view.NoView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {
    Position p1;
    Position p2;
    Position p3;
    Position p4;
    Position p5;
    @Before
    public void setup(){
        p1=new Position(10,20,10,10);
        p2=new Position(10,20,-20,-20);//20,20,1,1
        p3=new Position(10,20,1,0);//20,20,0,0
        p4=new Position(-10,-20,20,20);//1,1,1,1
        p5=new Position(10,20,40,40);//20,20,40,40


    }

    @Test
    public void testClone() {
        Assert.assertEquals(p1.clone(),p1);
        Assert.assertEquals(p2.clone(),p2);
        Assert.assertEquals(p3.clone(),p3);
        Assert.assertEquals(p4.clone(),p4);
        Assert.assertEquals(p5.clone(),p5);
        Assert.assertNotEquals(p5.clone(),null);
    }

    @Test
    public void getY() {
        Assert.assertEquals(p1.getY(),10);
        Assert.assertEquals(p2.getY(),20);
        Assert.assertEquals(p3.getY(),0);
        Assert.assertEquals(p4.getY(),1);
        Assert.assertEquals(p5.getY(),20);
    }

    @Test
    public void getX() {
        Assert.assertEquals(p1.getX(),10);
        Assert.assertEquals(p2.getX(),10);
        Assert.assertEquals(p3.getX(),1);
        Assert.assertEquals(p4.getX(),1);
        Assert.assertEquals(p5.getX(),10);
    }

    @Test
    public void setY() {
        p1.setY(5);
        Assert.assertEquals(5,p1.getY());
        p1.setY(-20);
        Assert.assertEquals(5,p1.getY());
        p1.setY(40);
        Assert.assertEquals(5,p1.getY());
        p2.setY(21);
        Assert.assertEquals(20,p2.getY());
        p3.setY(20);
        Assert.assertEquals(20,p3.getY());
        p3.setY(0);
        Assert.assertEquals(0,p3.getY());
    }

    @Test
    public void setX() {
        p1.setX(5);
        Assert.assertEquals(5,p1.getX());
        p1.setX(-20);
        Assert.assertEquals(5,p1.getX());
        p1.setX(40);
        Assert.assertEquals(5,p1.getX());
        p2.setX(11);
        Assert.assertEquals(10,p2.getX());
        p3.setX(10);
        Assert.assertEquals(10,p3.getX());
        p3.setX(0);
        Assert.assertEquals(0,p3.getX());
    }

    @Test
    public void getRealPosition() {
        Assert.assertEquals(p1.getX()*6,p1.getRealPosition().getX());
        Assert.assertEquals(p1.getY()*3,p1.getRealPosition().getY());
        Assert.assertEquals(
                new Position(10*6+20,20*3+20,p1.getX()*6,p1.getY()*3),p1.getRealPosition());
        Assert.assertNotEquals(null,p1.getRealPosition());
    }

    @Test
    public void testEquals()
    {
        Assert.assertEquals(p1.equals(new Position(10,20,10,10)),true);
        Assert.assertEquals(p1.equals(p1),true);
        Assert.assertEquals(p1.equals(new NoView()),false);
    }

    @Test
    public void getPositionByMovement() {
        Position p1 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,4,5),p1.getPositionByMovement(Movement.left));
        Position p2 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,6,5),p2.getPositionByMovement(Movement.right));
        Position p3 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,5,4),p3.getPositionByMovement(Movement.up));
        Position p4 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,5,6),p4.getPositionByMovement(Movement.down));
        p4 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,5,5),p4.getPositionByMovement(Movement.stay));
    }

    @Test
    public void getUp() {
        Position p3 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,5,4),p3.getUp());
        p3 = new Position(20,20,5,0);
        Assert.assertEquals(new Position(20,20,5,0),p3.getUp());
        p3 = new Position(20,20,5,1);
        Assert.assertEquals(new Position(20,20,5,0),p3.getUp());
    }

    @Test
    public void getDown() {
        Position p3 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,5,6),p3.getDown());
        p3 = new Position(20,20,5,20);
        Assert.assertEquals(new Position(20,20,5,20),p3.getDown());
        p3 = new Position(20,20,5,19);
        Assert.assertEquals(new Position(20,20,5,20),p3.getDown());
    }

    @Test
    public void getLeft() {
        Position p3 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,4,5),p3.getLeft());
        p3 = new Position(20,20,0,5);
        Assert.assertEquals(new Position(20,20,0,5),p3.getLeft());
        p3 = new Position(20,20,1,5);
        Assert.assertEquals(new Position(20,20,0,5),p3.getLeft());
    }

    @Test
    public void getRight() {
        Position p3 = new Position(20,20,5,5);
        Assert.assertEquals(new Position(20,20,6,5),p3.getRight());
        p3 = new Position(20,20,20,5);
        Assert.assertEquals(new Position(20,20,20,5),p3.getRight());
        p3 = new Position(20,20,19,5);
        Assert.assertEquals(new Position(20,20,20,5),p3.getRight());
    }

    @Test
    public void setUp() {
        Position p3 = new Position(20,20,5,5);
        p3.setUp();
        Assert.assertEquals(new Position(20,20,5,4),p3);
    }

    @Test
    public void setDown() {
        Position p3 = new Position(20,20,5,5);
        p3.setDown();
        Assert.assertEquals(new Position(20,20,5,6),p3);
    }

    @Test
    public void setLeft() {
        Position p3 = new Position(20,20,5,5);
        p3.setLeft();
        Assert.assertEquals(new Position(20,20,4,5),p3);
    }

    @Test
    public void setRight() {
        Position p3 = new Position(20,20,5,5);
        p3.setRight();
        Assert.assertEquals(new Position(20,20,6,5),p3);
    }
}