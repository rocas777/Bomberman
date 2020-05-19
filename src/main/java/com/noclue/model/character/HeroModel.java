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

    public State getState() {
        return state;
    }

    public Normal getNormal() {
        return normal;
    }

    public Invincible getInvincible() {
        return invincible;
    }

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
            synchronized (state) {
                state = normal;
            }
        }
    }

    @Override
    public boolean deactivate() {
        if(state.equals(normal)){
            timer.addListener(this);
        }
        if(livesModel.getLives()==0){
            isActive = false;
        }
        else{
            synchronized (state) {
                state.deactivate(livesModel);
                state = invincible;
            }
        }
        return true;
    }


}
