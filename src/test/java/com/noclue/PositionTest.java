package com.noclue;

//import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class PositionTest {
    private Position a;
    @Before
    public void pre_handler() {
        a = new Position(5, 4);
    }

    @Test
    public void setY(){   // really a stub
        try {
            a.setY(6);
            fail("Foi permitida a atribuição de um valor a y maior que max_y");
        }
        catch (PositionError e){
        }
        try {
            a.setY(1);
        }
        catch (PositionError e){
            fail();
        }
    }

    @Test
    public void setX() {
        try {
            a.setX(6);
            fail("Foi permitida a atribuição de um valor a x maior que max_x");
        }
        catch (PositionError e){
        }
        try {
            a.setX(1);
        }
        catch (PositionError e){
            fail();
        }
    }

}