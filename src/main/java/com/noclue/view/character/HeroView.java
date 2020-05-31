package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.character.HeroModel;
import com.noclue.view.LivesView;

import static com.googlecode.lanterna.SGR.BOLD;

public class HeroView implements IView {
    private HeroModel model;
    private TextGraphics textGraphics;
    private LivesView livesView;
    private int i = 0;

    public HeroView(HeroModel model, TextGraphics textGraphics) {
        this.setModel(model);
        this.setTextGraphics(textGraphics);
        setLivesView(new LivesView(model.getLivesModel(), textGraphics));
    }

    public HeroModel getModel() {
        return model;
    }

    public void setModel(HeroModel model) {
        this.model = model;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        setI(getI() + 1);
        if (getModel().getDeactivateState() == getModel().getInvincibleDeactivate() && getI() % 10 <= 4) { //tick one color while invincible
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));   //head and arms
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
            textGraphics.putString(position.getRealPosition().getX() + 1, position.getRealPosition().getY() + 1, " ", BOLD);
            textGraphics.putString(position.getRealPosition().getX() + 4, position.getRealPosition().getY() + 1, " ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffff00"));   //shirt
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 1, "  ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));   //legs
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#005555"));
        } else if (getModel().getDeactivateState() == getModel().getInvincibleDeactivate()) { //tick the other color while invincible
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));   //head and arms
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
            textGraphics.putString(position.getRealPosition().getX() + 1, position.getRealPosition().getY() + 1, " ", BOLD);
            textGraphics.putString(position.getRealPosition().getX() + 4, position.getRealPosition().getY() + 1, " ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));   //shirt
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 1, "  ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));   //legs
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#005555"));
        } else {    //normal draw
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));   //head and arms
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
            textGraphics.putString(position.getRealPosition().getX() + 1, position.getRealPosition().getY() + 1, " ", BOLD);
            textGraphics.putString(position.getRealPosition().getX() + 4, position.getRealPosition().getY() + 1, " ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#3B5998"));   //shirt
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 1, "  ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));   //legs
            textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#005555"));
        }


        getLivesView().draw();
    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getModel().getPosition());
    }

    public LivesView getLivesView() {
        return livesView;
    }

    public void setLivesView(LivesView livesView) {
        this.livesView = livesView;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
