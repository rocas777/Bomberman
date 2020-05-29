package com.noclue.model.state;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvincibleDeactivateTest {

    @Test
    public void deactivate() {
        InvincibleDeactivate invincibleDeactivate = new InvincibleDeactivate();
        Assert.assertFalse(invincibleDeactivate.deactivate(null));
    }
}