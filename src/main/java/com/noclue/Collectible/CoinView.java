package com.noclue.Collectible;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.Drawable;
import com.noclue.Position;

public class CoinView implements Drawable {
    Coin coin;
    CoinView(Coin coin){
        this.coin=coin;
    }

    @Override
    public void draw(TextGraphics textGraphics, Position position) {

    }
}
