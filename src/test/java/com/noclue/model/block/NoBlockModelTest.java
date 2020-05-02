package com.noclue.model.block;

import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class NoBlockModelTest {

    @Test
    public void isFilled() {
        NoBlockModel n1 = new NoBlockModel();
        NoBlockModel n2 = new NoBlockModel();

        Assert.assertEquals(n1.isFilled(),false);
        Assert.assertEquals(n2.isFilled(),false);
    }
}