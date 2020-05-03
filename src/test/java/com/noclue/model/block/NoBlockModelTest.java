package com.noclue.model.block;

import org.junit.Assert;
import org.junit.Test;

public class NoBlockModelTest {

    @Test
    public void isFilled() {
        NoBlockModel n1 = new NoBlockModel();
        NoBlockModel n2 = new NoBlockModel();

        Assert.assertEquals(n1.isFilled(),false);
        Assert.assertEquals(n2.isFilled(),false);
    }
}