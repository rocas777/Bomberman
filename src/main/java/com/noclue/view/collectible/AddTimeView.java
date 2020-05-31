package com.noclue.view.collectible;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.collectible.AddTime;

import static com.googlecode.lanterna.SGR.BOLD;

public class AddTimeView implements IView {
    private AddTime addTime;
    private TextGraphics textGraphics;

    public AddTimeView(AddTime addTime, TextGraphics textGraphics) {
        this.setAddTime(addTime);
        this.setTextGraphics(textGraphics);
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 1, "      ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);
    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getAddTime().getPosition());
    }

    public AddTime getAddTime() {
        return addTime;
    }

    public void setAddTime(AddTime addTime) {
        this.addTime = addTime;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }
}
