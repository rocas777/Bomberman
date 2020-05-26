package com.noclue.model.character;

import com.noclue.model.Filler;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.State.*;
import com.noclue.timer.TimeListener;
import com.noclue.timer.Timer;

public class HeroModel {
    Position position;
    LivesModel livesModel;
    Timer timer = new Timer(50);
    DeactivateState deactivateState;
    int timerCount = 0;
    boolean isActive =true;
    int touchCounter = -1;

    public int getTouchCounter() {
        return touchCounter;
    }

    public void setTouchCounter(int touchCounter) {
        this.touchCounter = touchCounter;
    }

    DeactivateState normalDeactivate;
    DeactivateState invincibleDeactivate;


    IsTouchingState isTouchingState;

    IsTouchingState normalIsTouching;
    IsTouchingState invencibleIsTouching;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTimerCount() {
        return timerCount;
    }

    public void setTimerCount(int timerCount) {
        this.timerCount = timerCount;
    }

    public DeactivateState getDeactivateState() {
        return deactivateState;
    }

    public DeactivateState getInvincibleDeactivate() {
        return invincibleDeactivate;
    }

    public HeroModel(Position position){
        this.position=position;
        timer.start();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LivesModel getLivesModel() {
        return livesModel;
    }

    public void setLivesModel(LivesModel livesModel) {
        this.livesModel = livesModel;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setDeactivateState(DeactivateState deactivateState) {
        this.deactivateState = deactivateState;
    }

    public DeactivateState getNormalDeactivate() {
        return normalDeactivate;
    }

    public void setNormalDeactivate(NormalDeactivate normalDeactivate) {
        this.normalDeactivate = normalDeactivate;
    }

    public void setInvincibleDeactivate(InvincibleDeactivate invincibleDeactivate) {
        this.invincibleDeactivate = invincibleDeactivate;
    }

    public IsTouchingState getIsTouchingState() {
        return isTouchingState;
    }

    public void setIsTouchingState(IsTouchingState isTouchingState) {
        this.isTouchingState = isTouchingState;
    }

    public IsTouchingState getNormalIsTouching() {
        return normalIsTouching;
    }

    public void setNormalIsTouching(NormalIsTouching normalIsTouching) {
        this.normalIsTouching = normalIsTouching;
    }

    public IsTouchingState getInvencibleIsTouching() {
        return invencibleIsTouching;
    }

    public void setInvencibleIsTouching(IsTouchingState invencibleIsTouching) {
        this.invencibleIsTouching = invencibleIsTouching;
    }
}
