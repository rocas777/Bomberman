package com.noclue;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Block.BlockView;
import com.noclue.Collectible.Collectible;
import com.noclue.Collectible.CollectibleView;

public class TileView implements Drawable {
    FillerView fillerView;
    CollectibleView collectibleView;
    TileView(CollectibleView collectible, FillerView filler){
        collectibleView=collectible;
        fillerView=filler;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        collectibleView.draw(textGraphics, position);
        fillerView.draw(textGraphics,position);
    }
}
