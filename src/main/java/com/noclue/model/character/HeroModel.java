package com.noclue.model.character;

import com.noclue.model.Filler;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.State.*;
import com.noclue.timer.TimeListener;
import com.noclue.timer.Timer;

public class HeroModel extends Filler implements Character, TimeListener {
    Position position;
    LivesModel livesModel;
    Timer timer = new Timer(50);
    DeactivateState deactivateState;

    NormalDeactivate normalDeactivate;
    InvincibleDeactivate invincibleDeactivate;

    IsTouchingHimselfState isTouchingHimselfState;

    NormalIsTouching normalIsTouching;
    InvencibleIsTouching invencibleIsTouching;

    Integer timerCount=0;

    public DeactivateState getDeactivateState() {
        return deactivateState;
    }

    public NormalDeactivate getNormal() {
        return normalDeactivate;
    }

    public InvincibleDeactivate getInvincibleDeactivate() {
        return invincibleDeactivate;
    }

    public HeroModel(Position position){
        normalDeactivate = new NormalDeactivate();
        invincibleDeactivate = new InvincibleDeactivate();

        invencibleIsTouching = new InvencibleIsTouching();
        normalIsTouching = new NormalIsTouching();

        deactivateState = normalDeactivate;
        isTouchingHimselfState = normalIsTouching;

        this.position=position;
        timer.start();
    }

    public LivesModel getLivesModel() {
        return livesModel;
    }

    public void setLivesModel(LivesModel livesModel) {
        this.livesModel = livesModel;
    }

    public void addLife(){
        if(livesModel.getLives()<5){
            livesModel.setLives(livesModel.getLives()+1);
        }
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
        return isTouchingHimselfState.isTouching(filler);
    }

    public void ActivateInvencible(){
        if(isTouchingHimselfState.equals(normalIsTouching)){
            timer.addListener(this);
        }

        synchronized (isTouchingHimselfState) {
            isTouchingHimselfState = invencibleIsTouching;
        }
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
            synchronized (deactivateState) {
                deactivateState = normalDeactivate;
                isTouchingHimselfState = normalIsTouching;
            }
        }
    }

    @Override
    public boolean deactivate() {
        if(deactivateState.equals(normalDeactivate)){
            timer.addListener(this);
        }
        if(livesModel.getLives()==0){
            isActive = false;
        }
        else{
            synchronized (deactivateState) {
                deactivateState.deactivate(livesModel);
                deactivateState = invincibleDeactivate;
            }
        }
        return true;
    }
}
