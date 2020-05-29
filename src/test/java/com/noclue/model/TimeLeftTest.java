package com.noclue.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TimeLeftTest {

    @Test
    public void minusSecond() {
        TimeLeft timeLeft = new TimeLeft(20, Mockito.mock(Position.class));
        Assert.assertEquals(20,timeLeft.getSeconds());
        timeLeft.minusSecond();
        Assert.assertEquals(19,timeLeft.getSeconds());
        timeLeft.minusSecond();
        Assert.assertEquals(18,timeLeft.getSeconds());

    }

    @Test
    public void addTime() {
        TimeLeft timeLeft = new TimeLeft(20, Mockito.mock(Position.class));
        Assert.assertEquals(20,timeLeft.getSeconds());
        timeLeft.addTime();
        Assert.assertEquals(35,timeLeft.getSeconds());
        timeLeft.addTime();
        Assert.assertEquals(50,timeLeft.getSeconds());
    }
}