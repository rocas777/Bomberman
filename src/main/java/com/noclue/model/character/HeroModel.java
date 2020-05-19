package com.noclue.model.character;

import com.noclue.model.Filler;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.State.Invincible;
import com.noclue.model.State.Normal;
import com.noclue.model.State.State;
import com.noclue.timer.TimeListener;
import com.noclue.timer.Timer;

public class HeroModel extends Filler implements Character, TimeListener {
    Position position;
    LivesModel livesModel;
    Timer timer = new Timer(50);
    State state;
    Normal normal;
    Invincible invincible;
    Integer timerCount=0;

    public HeroModel(Position position){
        normal = new Normal();
        invincible = new Invincible();
        state=normal;
        this.position=position;
        timer.start();
    }

    public LivesModel getLivesModel() {
        return livesModel;
    }

    public void setLivesModel(LivesModel livesModel) {
        this.livesModel = livesModel;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isTouching(Filler filler) {
        return false;
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public void updateOnTime() {
        timerCount++;
        if(timerCount==40){
            timer.removeListener(this);
            timerCount=0;
            state=normal;
        }
    }

    @Override
    public boolean deactivate() {
        state.deactivate(livesModel);
        if(livesModel.getLives()==0){
            isActive = false;
        }
        else{
            state=invincible;
            timer.addListener(this);
        }

        return true;
    }


}
