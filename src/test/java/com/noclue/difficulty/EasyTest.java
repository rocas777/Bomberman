package com.noclue.difficulty;

import com.noclue.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class EasyTest {

    @Test
    public void nextMove() {
        Easy easy = new Easy();
        ArrayList<ArrayList> tmp = new ArrayList<ArrayList>();

        //position not used
        for(int i=0;i<100;i++){
            tmp.add(easy.nextMove(new Position(0,0,0,0)));
        }

        for(ArrayList a:tmp){
            if(Collections.frequency(tmp, a)>20)
                Assert.fail();
        }
    }
}