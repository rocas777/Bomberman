package com.noclue.model.difficulty;

import com.noclue.Movement;
import com.noclue.model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

public class Medium implements Difficulty {

    @Override
    public ArrayList<Movement> nextMove(Position monster, Position hero, ArrayList<Position> bomb) {
        Movement _1 = Movement.left;
        Movement _2 = Movement.right;
        Movement _3 = Movement.up;
        Movement _4 = Movement.down;

        Float dist1 = Float.valueOf(0);
        Float dist2 = Float.valueOf(0);
        Float dist3 = Float.valueOf(0);
        Float dist4 = Float.valueOf(0);

        dist1 = Float.valueOf(abs(monster.getLeft().getX() - hero.getX()) + abs(monster.getY() - hero.getY()));
        dist2 = Float.valueOf(abs(monster.getRight().getX() - hero.getX()) + abs(monster.getY() - hero.getY()));
        dist3 = Float.valueOf(abs(monster.getX() - hero.getX()) + abs(monster.getUp().getY() - hero.getY()));
        dist4 = Float.valueOf(abs(monster.getX() - hero.getX()) + abs(monster.getDown().getY() - hero.getY()));

        ArrayList<Par> list = new ArrayList<>();
        list.add(new Par(dist1, _1));
        list.add(new Par(dist2, _2));
        list.add(new Par(dist3, _3));
        list.add(new Par(dist4, _4));

        //sort from shortest to furthest away from the hero

        Collections.sort(list, new Comparator<Par>() {
            @Override
            public int compare(Par par, Par t1) {
                return (int) (par.f - t1.f);
            }
        });

        ArrayList<Movement> out = new ArrayList();
        for (Par m : list) {
            out.add(m.movement);
        }

        return out;
    }

    class Par {
        float f;
        Movement movement;

        Par(Float f, Movement movement) {
            this.f = f;
            this.movement = movement;
        }
    }
}
