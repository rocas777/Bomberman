package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.AddLife;

import static com.googlecode.lanterna.SGR.BOLD;

public class AddLifeView implements IView {
    private AddLife addLife;
    private TextGraphics textGraphics;

    public AddLifeView(AddLife addLife, TextGraphics textGraphics) {
        this.setAddLife(addLife);
        this.setTextGraphics(textGraphics);
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 1, "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);
    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getAddLife().getPosition());
    }

    public AddLife getAddLife() {
        return addLife;
    }

    public void setAddLife(AddLife addLife) {
        this.addLife = addLife;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }
}
