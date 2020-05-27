package com.noclue.controller;

import com.noclue.model.Filler;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.State.InvencibleIsTouching;
import com.noclue.model.State.InvincibleDeactivate;
import com.noclue.model.State.NormalDeactivate;
import com.noclue.model.State.NormalIsTouching;
import com.noclue.model.character.Character;
import com.noclue.model.character.HeroModel;
import com.noclue.view.character.HeroView;

public class HeroController extends Filler implements Character {
    HeroModel model;
    HeroView view;

    public HeroController(HeroModel model, HeroView view) {
        this.model = model;
        this.view = view;
        model.setNormalDeactivate(new NormalDeactivate(model));
        model.setInvincibleDeactivate(new InvincibleDeactivate());

        model.setInvencibleIsTouching(new InvencibleIsTouching(model));
        model.setNormalIsTouching(new NormalIsTouching(model));

        model.setDeactivateState(model.getNormalDeactivate());
        model.setIsTouchingState(model.getNormalIsTouching());
    }


    @Override
    public boolean deactivate() {
        return model.getDeactivateState().deactivate(model.getLivesModel());
    }

    public LivesModel getLivesModel() {
        return model.getLivesModel();
    }

    public void setLivesModel(LivesModel livesModel) {
        model.setLivesModel(livesModel);
    }

    public void addLife(){
        if(model.getLivesModel().getLives()<5){
            model.getLivesModel().setLives(model.getLivesModel().getLives()+1);
        }
    }

    public HeroModel getModel() {
        return model;
    }

    @Override
    public Position getPosition() {
        return model.getPosition();
    }

    public void setPosition(Position position) {
        model.setPosition(position);
    }

    @Override
    public boolean isTouching(Filler filler) {
        return model.getIsTouchingState().isTouching(filler);
    }

    public void ActivateInvencible(){
        model.getIsTouchingState().Activate();
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public boolean isActive(){
        return  model.isActive();
    }
}
