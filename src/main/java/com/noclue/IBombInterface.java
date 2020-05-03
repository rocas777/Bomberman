package com.noclue;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.noclue.model.BombModel;
import com.noclue.timer.TimeListener;

public interface IBombInterface extends TimeListener {
    int getSum();

    void setSum(int sum);

    TextGraphics getTextGraphics();

    BombModel getModel();

    IView getView();

    void setViewFire(IView viewFire);

    void setView(IView view);

    void setViewTicking(IView viewTicking);

    void updateOnTime();

    void draw();
}
