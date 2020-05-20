package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class EasyTest {

    @Test
    public void nextMove() {
        ArrayList<Movement> movements = new ArrayList<>();
        movements.add(Movement.right);
        movements.add(Movement.left);
        movements.add(Movement.down);
        movements.add(Movement.up);
        Easy easy= new Easy();
        Position p = Mockito.mock(Position.class);
        //Assert.assertNotEquals(movements,easy.nextMove(p));
        //Assert.assertNotEquals(null,easy.nextMove(p));

    }

    @Test
    public void randomizeArray(){
        ArrayList<Movement> movements = new ArrayList<>();
        movements.add(Movement.right);
        movements.add(Movement.left);
        movements.add(Movement.down);
        movements.add(Movement.up);
        movements.add(Movement.stay);
        Easy easy= new Easy();
        Assert.assertNotEquals(movements,easy.randomizeArray((ArrayList<Movement>) movements.clone()));
        Assert.assertNotEquals(null,easy.randomizeArray((ArrayList<Movement>) movements.clone()));

    }
}