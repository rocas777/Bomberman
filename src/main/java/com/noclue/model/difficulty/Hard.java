package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

public class Hard implements Difficulty {

    class Par implements Comparator {
        float f ;
        Movement movement;
        Par(Float f ,Movement movement){
            this.f = f;
            this.movement = movement;
        }

        @Override
        public int compare(Object o, Object t1) {
            return (int) ((Float) o- (Float)t1);
        }
    }

    @Override
    public ArrayList<Movement> nextMove(Position monster,Position hero, ArrayList<Position> bomb) {
        Movement _1 = Movement.left;
        Movement _2 = Movement.right;
        Movement _3 = Movement.up;
        Movement _4 = Movement.down;

        Float dist1= Float.valueOf(0);
        Float dist2= Float.valueOf(0);
        Float dist3= Float.valueOf(0);
        Float dist4= Float.valueOf(0);

        dist1 = Float.valueOf(abs(monster.getLeft().getX()-hero.getX()) + abs(monster.getY()-hero.getY()));
        dist2 = Float.valueOf(abs(monster.getRight().getX()-hero.getX()) + abs(monster.getY()-hero.getY()));
        dist3 = Float.valueOf(abs(monster.getX()-hero.getX()) + abs(monster.getUp().getY()-hero.getY()));
        dist4 = Float.valueOf(abs(monster.getX()-hero.getX()) + abs(monster.getDown().getY()-hero.getY()));

        ArrayList<Hard.Par> list = new ArrayList<>();
        list.add(new Hard.Par(dist1,_1));
        list.add(new Hard.Par(dist2,_2));
        list.add(new Hard.Par(dist3,_3));
        list.add(new Hard.Par(dist4,_4));

        Collections.sort(list, new Comparator<Hard.Par>() {
            @Override
            public int compare(Hard.Par par, Hard.Par t1) {
                return (int)(par.f-t1.f);
            }
        });

        ArrayList<Movement> out = new ArrayList();
        boolean inExplosionList = checkIsInExplosionList(bomb,monster);

        populateArray(out,list,bomb,monster,true);

        if(inExplosionList){
            populateArray(out,list,bomb,monster,false);
        }

        return out;
    }

    private boolean checkIsInExplosionList(ArrayList<Position> bomb, Position monster){
        if(bomb!=null) {
            for (Position p : bomb) {
                if (monster.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void populateArray(ArrayList<Movement> out,ArrayList<Par> list, ArrayList<Position> bomb, Position monster, boolean addCond){
        for(Hard.Par m:list){
            if(bomb!=null){
                boolean add = true;
                for(Position i:bomb){
                    if(monster.getPositionByMovement(m.movement).equals(i)){
                        add=false;
                    }
                }
                if(add == addCond){
                    out.add(m.movement);
                }
            }
            else{
                out.add(m.movement);
            }
        }
    }
}
