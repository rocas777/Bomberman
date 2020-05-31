package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.character.MonsterModel;
import com.noclue.model.difficulty.Easy;
import com.noclue.model.difficulty.Hard;
import com.noclue.model.difficulty.Medium;

import static com.googlecode.lanterna.SGR.BOLD;

public class MonsterView implements IView {
    private TextGraphics textGraphics;
    private MonsterModel monsterModel;

    public MonsterView(MonsterModel monsterModel, TextGraphics textGraphics) {
        this.setMonsterModel(monsterModel);
        this.setTextGraphics(textGraphics);
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void setTextGraphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    public MonsterModel getMonsterModel() {
        return monsterModel;
    }

    public void setMonsterModel(MonsterModel monsterModel) {
        this.monsterModel = monsterModel;
    }

    public void draw(TextGraphics textGraphics, Position position) {

        if (getMonsterModel().getDifficulty() instanceof Medium)
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#ffff00"));

        else if (getMonsterModel().getDifficulty() instanceof Easy) {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#10ff44"));

        } else if (getMonsterModel().getDifficulty() instanceof Hard) {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF1010"));
        }
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 1, "  ", BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY(), " ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 5, position.getRealPosition().getY(), " ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 1, position.getRealPosition().getY() + 1, " ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 4, position.getRealPosition().getY() + 1, " ", BOLD);
        textGraphics.putString(position.getRealPosition().getX(), position.getRealPosition().getY() + 2, " ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 5, position.getRealPosition().getY() + 2, " ", BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY(), "  ", BOLD);
        textGraphics.putString(position.getRealPosition().getX() + 2, position.getRealPosition().getY() + 2, "  ", BOLD);


    }

    @Override
    public void draw() {
        draw(getTextGraphics(), getMonsterModel().getPosition());
    }
}
