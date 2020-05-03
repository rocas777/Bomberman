package com.noclue.model.character;

import com.noclue.model.Filler;
import com.noclue.model.Position;

public interface Character extends Filler {
    Position getPosition();
    void setPosition(Position position);
}
