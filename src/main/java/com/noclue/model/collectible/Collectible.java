package com.noclue.model.collectible;

import com.noclue.controller.FieldController;
import com.noclue.model.Position;

public interface Collectible {
    void visit(FieldController fieldController);
}
