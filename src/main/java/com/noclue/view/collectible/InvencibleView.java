package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.Invencible;

import static com.googlecode.lanterna.SGR.BOLD;

public class InvencibleView implements IView {
    private Invencible invencible;
    private TextGraphics textGraphics;

    public InvencibleView(Invencible invencible, TextGraphics textGraphics) {
        this.setInvencible(invencible);
        this.setTextGraphics(textGraphics);
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 1, "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);
    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getInvencible().getPosition());
    }

    public Invencible getInvencible() {
        return invencible;
    }

    public void setInvencible(Invencible invencible) {
        this.invencible = invencible;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }
}

