package com.noclue.Block;

import com.noclue.Drawable;
import com.noclue.Filler;
import com.noclue.Position;

public interface Block extends Filler {
    @Override
    public default void explode() {

    }
}
