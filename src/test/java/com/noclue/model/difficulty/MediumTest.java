package com.noclue.model.difficulty;

import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class MediumTest {

    @Test
    public void nextMove() {
        Position p = Mockito.mock(Position.class);
        Assert.assertEquals(null,new Medium().nextMove(p));
    }
}