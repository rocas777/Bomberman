package com.noclue;

import com.noclue.model.Position;

import java.util.ArrayList;

public interface ExplosionListener {
    void explode(ArrayList<Position> positions);

    void fireDone();
}
