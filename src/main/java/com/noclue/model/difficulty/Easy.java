package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;

import java.util.ArrayList;
import java.util.Random;

public class Easy implements Difficulty {

    private ArrayList<Movement> randomizeArray(ArrayList<Movement> movements){
        Random rand=new Random();
        for (int i = 0; i < movements.size(); i++) {
            int randomIndexToSwap = rand.nextInt(movements.size());
            Movement temp = movements.get(randomIndexToSwap);
            movements.set(randomIndexToSwap,movements.get(i));
            movements.set(i,temp);
        }
        return movements;
    }
    @Override
    public ArrayList<Movement> nextMove(Position position) {
        Movement _1 = Movement.left;
        Movement _2 = Movement.right;
        Movement _3 = Movement.up;
        Movement _4 = Movement.down;

        ArrayList<Movement> out=new ArrayList<com.noclue.Movement>(){
            {
                add(_1);
                add(_2);
                add(_3);
                add(_4);

            }
        };

        return randomizeArray(out);
    }
}
