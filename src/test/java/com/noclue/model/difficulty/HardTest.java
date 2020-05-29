package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HardTest {
    private static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
{
    ArrayList<T> newList = new ArrayList<T>();

    for (T element : list) {

        if (!newList.contains(element)) {

            newList.add(element);
        }
    }
    return newList;
}

    @Test
    public void nextMove() {
        Position hero = new Position(10,10,1,1);
        Position monster = new Position(10,10,3,1);
        ArrayList<Position> positions = new ArrayList<>();


        Hard hard = new Hard();
        ArrayList<Movement> arrayList;
        arrayList = hard.nextMove(monster,hero,positions);
        int bi = arrayList.size();
        ArrayList<Movement> pm = removeDuplicates(arrayList);
        Assert.assertEquals(pm.size(),bi);
        Assert.assertEquals(pm.size(),4);

        Assert.assertEquals(pm.get(0),Movement.left);
        Assert.assertEquals(pm.get(1),Movement.right);
        Assert.assertEquals(pm.get(2),Movement.up);
        Assert.assertEquals(pm.get(3),Movement.down);


        hero = new Position(10,10,5,1);
        monster = new Position(10,10,3,1);
        arrayList = hard.nextMove(monster,hero,positions);
        bi = arrayList.size();
        pm = removeDuplicates(arrayList);
        Assert.assertEquals(pm.size(),bi);
        Assert.assertEquals(pm.size(),4);

        Assert.assertEquals(pm.get(0),Movement.right);
        Assert.assertEquals(pm.get(1),Movement.left);
        Assert.assertEquals(pm.get(2),Movement.up);
        Assert.assertEquals(pm.get(3),Movement.down);

        Position bomb = new Position(10,10,4,1);
        positions.add(bomb);

        hero = new Position(10,10,5,1);
        monster = new Position(10,10,3,1);
        arrayList = hard.nextMove(monster,hero,positions);
        bi = arrayList.size();
        pm = removeDuplicates(arrayList);
        Assert.assertEquals(pm.size(),bi);
        Assert.assertEquals(pm.size(),3);

        Assert.assertEquals(pm.get(0),Movement.left);
        Assert.assertEquals(pm.get(1),Movement.up);
        Assert.assertEquals(pm.get(2),Movement.down);



        hero = new Position(10,10,5,1);
        monster = new Position(10,10,3,1);
        arrayList = hard.nextMove(monster,hero,null);
        bi = arrayList.size();
        pm = removeDuplicates(arrayList);
        Assert.assertEquals(pm.size(),bi);
        Assert.assertEquals(pm.size(),4);

        Assert.assertEquals(pm.get(0),Movement.right);
        Assert.assertEquals(pm.get(1),Movement.left);
        Assert.assertEquals(pm.get(2),Movement.up);
        Assert.assertEquals(pm.get(3),Movement.down);
    }

}