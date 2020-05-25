package com.noclue.view.character;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.character.HeroModel;
import com.noclue.view.LivesView;

import static com.googlecode.lanterna.SGR.BOLD;

public class HeroView implements IView {
    HeroModel model;
    TextGraphics textGraphics;
    LivesView livesView;

    public HeroView(HeroModel model, TextGraphics textGraphics){
        this.model=model;
        this.textGraphics=textGraphics;
        livesView= new LivesView(model.getLivesModel(),textGraphics);
    }

    public HeroModel getModel() {
        return model;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public void draw(TextGraphics textGraphics, Position position) {
        if(model.getState()==model.getInvincible()){
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#b200ff"));
        }
        else{
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        }
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY(),"  ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+1,position.getRealPosition().getY()+1," ",BOLD);
        textGraphics.putString(position.getRealPosition().getX()+4,position.getRealPosition().getY()+1," ",BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#3B5998"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+1,"  ",BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(position.getRealPosition().getX()+2,position.getRealPosition().getY()+2,"  ",BOLD);

        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#005555"));


        livesView.draw();
    }

    @Override
    public void draw() {
        draw(textGraphics,model.getPosition());
    }
}
