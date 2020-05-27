package com.noclue.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;

import static com.googlecode.lanterna.SGR.BOLD;

public class LivesView implements IView {
    LivesModel lives;
    TextGraphics textGraphics;

    public LivesView(LivesModel lives, TextGraphics textGraphics) {
        this.lives = lives;
        this.textGraphics = textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        for (int i = 0; i < lives.getLives(); i++) {
            textGraphics.putString(position.getX() + 1, position.getY() + i * 5, "  ", BOLD);
            textGraphics.putString(position.getX() + 5, position.getY() + i * 5, "  ", BOLD);
            textGraphics.putString(position.getX(), position.getY() + 1 + i * 5, "        ", BOLD);
            textGraphics.putString(position.getX() + 1, position.getY() + 2 + i * 5, "      ", BOLD);
            textGraphics.putString(position.getX() + 3, position.getY() + 3 + i * 5, "  ", BOLD);

        }
    }

    @Override
    public void draw() {
        draw(textGraphics, lives.getPosition());
    }

}