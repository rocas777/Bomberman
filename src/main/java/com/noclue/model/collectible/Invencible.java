package com.noclue.model.collectible;

import com.noclue.controller.FieldController;
import com.noclue.model.Position;

import static com.noclue.model.collectible.Visitor.visitInvencible;

public class Invencible implements Collectible {
    Position position;

    public Invencible(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void visit(FieldController fieldController) {
        visitInvencible(fieldController);
    }
}
