package com.noclue.difficulty;

import com.noclue.Position;

import java.util.ArrayList;
import java.util.Random;

public class Easy implements Difficulty {

    private ArrayList<Position> randomizeArray(ArrayList<Position> positions){
        Random rand=new Random();
        for (int i = 0; i < positions.size(); i++) {
            int randomIndexToSwap = rand.nextInt(positions.size());
            Position temp = positions.get(randomIndexToSwap);
            positions.set(randomIndexToSwap,positions.get(i));
            positions.set(i,temp);
        }
        return positions;
    }
    @Override
    public ArrayList<Position> nextMove(Position position) {
        Position _1 = position;
        Position _2 = position;
        Position _3 = position;
        Position _4 = position;
        try {
            _1.setX(position.getX() + 1);
        } catch (Exception positionError) {
            positionError.printStackTrace();
        }
        try {
            _2.setY(position.getY() + 1);
        } catch (Exception positionError) {
            positionError.printStackTrace();
        }
        try {
            _3.setX(position.getX() - 1);
        } catch (Exception positionError) {
            positionError.printStackTrace();
        }
        try {
            _4.setY(position.getY() - 1);
        } catch (Exception positionError) {
            positionError.printStackTrace();
        }
        ArrayList<Position> out=new ArrayList<Position>(){
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
