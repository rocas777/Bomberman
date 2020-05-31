package com.noclue.model.collectible;

import com.noclue.controller.FieldController;
import com.noclue.model.Position;

import static com.noclue.model.collectible.Visitor.visitLife;

public class AddLife implements Collectible {
    private Position position;

    public AddLife(Position position) {
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
        visitLife(fieldController);
    }
}
