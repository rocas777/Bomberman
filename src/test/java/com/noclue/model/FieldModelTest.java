package com.noclue.model;

import com.noclue.Movement;
import com.noclue.controller.TileController;
import com.noclue.controller.TileControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FieldModelTest {
    Filler fl = Mockito.mock(Filler.class);
    Filler fr = Mockito.mock(Filler.class);
    Filler fs = Mockito.mock(Filler.class);
    Filler fu = Mockito.mock(Filler.class);
    Filler fd = Mockito.mock(Filler.class);
    TileController tl = Mockito.mock(TileController.class);
    TileController tr = Mockito.mock(TileController.class);
    TileController ts = Mockito.mock(TileController.class);
    TileController tu = Mockito.mock(TileController.class);
    TileController td = Mockito.mock(TileController.class);

    private void setWhens(boolean _1,boolean _2){
        when(tl.isFilled()).thenReturn(_1);
        when(tr.isFilled()).thenReturn(_1);
        when(ts.isFilled()).thenReturn(_1);
        when(td.isFilled()).thenReturn(_1);
        when(tu.isFilled()).thenReturn(_1);

        when(fl.isActive()).thenReturn(_2);
        when(fr.isActive()).thenReturn(_2);
        when(fs.isActive()).thenReturn(_2);
        when(fd.isActive()).thenReturn(_2);
        when(fu.isActive()).thenReturn(_2);
    }

    @Test
    public void checkPos() {
        FieldModel fieldModel = new FieldModel(100,100,1);
        Grid grid = Mockito.mock(Grid.class);
        fieldModel.setTiles(grid);

        Position pl = new Position(20,20,0,1);
        Position pr = new Position(20,20,2,1);
        Position ps = new Position(20,20,1,1);
        Position pu = new Position(20,20,1,0);
        Position pd = new Position(20,20,1,2);

        when(grid.getTile(pl)).thenReturn(tl);
        when(grid.getTile(pr)).thenReturn(tr);
        when(grid.getTile(ps)).thenReturn(ts);
        when(grid.getTile(pu)).thenReturn(tu);
        when(grid.getTile(pd)).thenReturn(td);

        when(tl.getFiller()).thenReturn(fl);
        when(tr.getFiller()).thenReturn(fr);
        when(ts.getFiller()).thenReturn(fs);
        when(td.getFiller()).thenReturn(fd);
        when(tu.getFiller()).thenReturn(fu);


        setWhens(true,true);
        Assert.assertFalse(fieldModel.checkPos(ps,Movement.left));

        setWhens(false,true);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.left));

        setWhens(true,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.left));

        setWhens(false,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.left));

        //right
        setWhens(true,true);
        Assert.assertFalse(fieldModel.checkPos(ps,Movement.right));

        setWhens(false,true);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.right));

        setWhens(true,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.right));

        setWhens(false,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.right));

        //up
        setWhens(true,true);
        Assert.assertFalse(fieldModel.checkPos(ps,Movement.up));

        setWhens(false,true);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.up));

        setWhens(true,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.up));

        setWhens(false,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.up));

        //down
        setWhens(true,true);
        Assert.assertFalse(fieldModel.checkPos(ps,Movement.down));

        setWhens(false,true);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.down));

        setWhens(true,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.down));

        setWhens(false,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.down));


        setWhens(true,true);
        Assert.assertFalse(fieldModel.checkPos(ps,Movement.stay));

        setWhens(false,true);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.stay));

        setWhens(true,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.stay));

        setWhens(false,false);
        Assert.assertTrue(fieldModel.checkPos(ps,Movement.stay));

        Assert.assertFalse(fieldModel.checkPos(ps,null));

    }
}