package com.noclue.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.TimeLeft;

import static com.googlecode.lanterna.SGR.BOLD;

public class TimeLeftView implements IView {
    TimeLeft timeLeft;
    TextGraphics textGraphics;

    public TimeLeftView(TimeLeft timeLeft, TextGraphics textGraphics) {
        this.timeLeft = timeLeft;
        this.textGraphics = textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        String string = "  " + timeLeft.getSeconds() / 60 + ":" + timeLeft.getSeconds() % 60;

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getX(), position.getY(), "TimeLeft", BOLD);
        textGraphics.putString(position.getX(), position.getY() + 1, string, BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
    }

    @Override
    public void draw() {
        draw(textGraphics, timeLeft.getPosition());
    }
}
