package com.noclue;

import com.noclue.model.Position;

public interface ExplosionListener {
    void explode(Position position);
    void fireDone(Position position);
}
