package com.noclue.model.collectible;

import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DoorModelTest {
    Position position = Mockito.mock(Position.class);
    Position p2 = Mockito.mock(Position.class);
    DoorModel doorModel = new DoorModel(position);

    @Test
    public void setPosition() {
        Assert.assertEquals(position,doorModel.getPosition());
        doorModel.setPosition(p2);
        Assert.assertEquals(p2,doorModel.getPosition());
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(position,doorModel.getPosition());
    }
}