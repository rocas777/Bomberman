package com.noclue.model.collectible;

import com.noclue.Visitor;
import com.noclue.controller.FieldController;
import com.noclue.model.Position;

public class AddLife implements Collectible {
    Position position;

    public AddLife(Position position) {
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
        Visitor.visitLife(fieldController);
    }
}
