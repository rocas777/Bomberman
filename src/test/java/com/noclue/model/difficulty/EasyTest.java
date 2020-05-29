package com.noclue.model.difficulty;

import com.noclue.Movement;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EasyTest {

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
        Easy easy = new Easy();
        ArrayList<Movement> arrayList;
        arrayList = easy.nextMove(null,null,null);
        int bi = arrayList.size();
        int ai = removeDuplicates(arrayList).size();
        Assert.assertEquals(ai,bi);
        Assert.assertEquals(ai,4);
    }
}