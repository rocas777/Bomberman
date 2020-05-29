package com.noclue.model.collectible;

import com.noclue.controller.FieldController;
import com.noclue.model.Position;

import static com.noclue.model.collectible.Visitor.visitTime;

public class AddTime implements Collectible {
    Position position;

    public AddTime(Position position) {
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
        visitTime(fieldController);
    }
}
