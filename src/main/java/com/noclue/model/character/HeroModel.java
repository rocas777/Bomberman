package com.noclue.model.character;

import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.state.*;
import com.noclue.timer.Timer;

public class HeroModel {
    private Position position;
    private LivesModel livesModel;
    private Timer timer = new Timer(50);
    private DeactivateState deactivateState;
    private int timerCount = 0;
    private boolean isActive = true;
    private int touchCounter = -1;
    private DeactivateState normalDeactivate;
    private DeactivateState invincibleDeactivate;
    private IsTouchingState isTouchingState;
    private IsTouchingState normalIsTouching;
    private IsTouchingState invencibleIsTouching;

    public HeroModel(Position position) {
        this.setPosition(position);
        getTimer().start();
    }

    public int getTouchCounter() {
        return touchCounter;
    }

    public void setTouchCounter(int touchCounter) {
        this.touchCounter = touchCounter;
    }

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

    public void setDeactivateState(DeactivateState deactivateState) {
        this.deactivateState = deactivateState;
    }

    public DeactivateState getInvincibleDeactivate() {
        return invincibleDeactivate;
    }

    public void setInvincibleDeactivate(InvincibleDeactivate invincibleDeactivate) {
        this.invincibleDeactivate = invincibleDeactivate;
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

    public DeactivateState getNormalDeactivate() {
        return normalDeactivate;
    }

    public void setNormalDeactivate(NormalDeactivate normalDeactivate) {
        this.normalDeactivate = normalDeactivate;
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
