package com.noclue;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.model.Position;
import com.noclue.timer.TimeListener;

import java.util.ArrayList;

public interface IBombInterface extends TimeListener {
    int getSum();

    void setSum(int sum);

    TextGraphics getTextGraphics();

    BombModel getModel();

    IView getView();

    void setView(IView view);

    void setViewFire(IView viewFire);

    void setViewTicking(IView viewTicking);

    void updateOnTime();

    void draw();

    ArrayList<Position> getExplosionList();
}
