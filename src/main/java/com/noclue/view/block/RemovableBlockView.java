package com.noclue.view.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.block.RemovableBlockModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class RemovableBlockView implements IView {
    private RemovableBlockModel model;
    private TextGraphics textGraphics;

    public RemovableBlockView(RemovableBlockModel model, TextGraphics textGraphics) {
        this.setModel(model);
        this.setTextGraphics(textGraphics);
    }

    public void draw(TextGraphics textGraphics, Position position) {

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#005555"));
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY(), "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 1, "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 2, "      ", BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 1, "  ", BOLD);
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public RemovableBlockModel getModel() {
        return model;
    }

    public void setModel(RemovableBlockModel model) {
        this.model = model;
    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getModel().getPosition());
    }
}
