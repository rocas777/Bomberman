package com.noclue.model.collectible;

import com.noclue.controller.FieldController;
import com.noclue.model.Position;

import static com.noclue.model.collectible.Visitor.visitCoin;

public class CoinModel implements Collectible {
    private Position position;

    public CoinModel(Position position) {
        this.setPosition(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void visit(FieldController fieldController) {
        visitCoin(fieldController);
    }
}
