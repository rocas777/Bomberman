package com.noclue.model.collectible;

import com.noclue.controller.FieldController;
import com.noclue.model.Position;

import static com.noclue.model.collectible.Visitor.visitDoor;

public class DoorModel implements Collectible {
    private Position position;

    public DoorModel(Position position) {
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
        visitDoor(fieldController);
    }
}
