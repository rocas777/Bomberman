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

    public IsTouchingHimselfState getIsTouchingHimselfState() {
        return isTouchingHimselfState;
    }

    public InvencibleIsTouching getInvencibleIsTouching() {
        return invencibleIsTouching;
    }

    public void setInvencibleIsTouching(InvencibleIsTouching invencibleIsTouching) {
        this.invencibleIsTouching = invencibleIsTouching;
    }

    public void setInvincibleDeactivate(InvincibleDeactivate invincibleDeactivate) {
        this.invincibleDeactivate = invincibleDeactivate;
    }

    public void setIsTouchingHimselfState(IsTouchingHimselfState isTouchingHimselfState) {
        this.isTouchingHimselfState = isTouchingHimselfState;
    }

    IsTouchingHimselfState isTouchingHimselfState;

    NormalIsTouching normalIsTouching;
    InvencibleIsTouching invencibleIsTouching;

    Integer timerCount=0;
    int touchCounter = -1;

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
        if (touchCounter==0) {
            isTouchingHimselfState = normalIsTouching;
            deactivateState = normalDeactivate;
        }
        touchCounter -=1;
        return isTouchingHimselfState.isTouching(filler);
    }

    public void ActivateInvencible(){
        if(touchCounter<=0) {
            touchCounter = 10;
            synchronized (isTouchingHimselfState) {
                isTouchingHimselfState = invencibleIsTouching;
                deactivateState = invincibleDeactivate;
            }
        }
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public void updateOnTime() {
        System.out.println(deactivateState.equals(invincibleDeactivate));
        System.out.println(deactivateState.equals(normalIsTouching));
        System.out.println(timerCount);
        System.out.println();
        timerCount++;
        if(timerCount>=40){
            timer.removeListener(this);
            timerCount=0;
            synchronized (deactivateState) {
                deactivateState = normalDeactivate;
            }
        }
    }

    @Override
    public boolean deactivate() {
        if(deactivateState.equals(normalDeactivate)){
            timer.addListener(this);
        }
        synchronized (deactivateState) {
            deactivateState.deactivate(livesModel);
            deactivateState = invincibleDeactivate;
        }
        if(livesModel.getLives()==0){
            isActive = false;
        }
        return true;
    }
}
