package com.noclue.view.block;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.IView;
import com.noclue.model.Position;
import com.noclue.model.block.RemovableBlockModel;

import static com.googlecode.lanterna.SGR.BOLD;

public class RemovableBlockView implements IView {
    RemovableBlockModel model;
    TextGraphics textGraphics;
    public RemovableBlockView(RemovableBlockModel model, TextGraphics textGraphics) {
        this.model = model;
        this.textGraphics = textGraphics;
    }
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY(),"######",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+1,"######",BOLD);
        textGraphics.putString(position.getRealPosition().getX(),position.getRealPosition().getY()+2,"######",BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0f7b30"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public RemovableBlockModel getModel() {
        return model;
    }

    @Override
    public void draw() {
        draw(textGraphics,model.getPosition());
    }
}
