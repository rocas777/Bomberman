package com.noclue.controller;

import com.noclue.model.Filler;
import com.noclue.model.Grid;
import com.noclue.model.LivesModel;
import com.noclue.model.Position;
import com.noclue.model.character.Character;
import com.noclue.model.character.HeroModel;
import com.noclue.model.state.InvincibleDeactivate;
import com.noclue.model.state.InvincibleIsTouching;
import com.noclue.model.state.NormalDeactivate;
import com.noclue.model.state.NormalIsTouching;
import com.noclue.view.character.HeroView;

public class HeroController extends Filler implements Character {
    private HeroModel model;
    private HeroView view;

    public HeroController(HeroModel model, HeroView view) {
        this.setModel(model);
        this.setView(view);
        model.setNormalDeactivate(new NormalDeactivate(model));
        model.setInvincibleDeactivate(new InvincibleDeactivate());

        model.setInvencibleIsTouching(new InvincibleIsTouching(model));
        model.setNormalIsTouching(new NormalIsTouching(model));

        model.setDeactivateState(model.getNormalDeactivate());
        model.setIsTouchingState(model.getNormalIsTouching());
    }


    @Override
    public boolean deactivate() {
        return getModel().getDeactivateState().deactivate(getModel().getLivesModel());
    }

    public void setLivesModel(LivesModel livesModel) {
        getModel().setLivesModel(livesModel);
    }

    public void addLife() { //increases life if total isn't 5
        if (getModel().getLivesModel().getLives() < 5) {
            getModel().getLivesModel().setLives(getModel().getLivesModel().getLives() + 1);
        }
    }

    public HeroModel getModel() {
        return model;
    }

    public void setModel(HeroModel model) {
        this.model = model;
    }

    @Override
    public Position getPosition() {
        return getModel().getPosition();
    }

    public void setPosition(Position position) {
        getModel().setPosition(position);
    }

    @Override
    public boolean isTouching(Filler filler) {
        return getModel().getIsTouchingState().isTouching(filler);
    }

    public void ActivateInvencible() {
        getModel().getIsTouchingState().Activate();
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    //moving hero to each direction methods

    @Override
    public boolean isActive() {
        return getModel().isActive();
    }

    public void moveLeft(Grid grid) {
        grid.getTile(getModel().getPosition()).moveTile(grid.getTile(getModel().getPosition().getLeft()));
        getModel().getPosition().setLeft();
    }

    public void moveRight(Grid grid) {
        grid.getTile(getModel().getPosition()).moveTile(grid.getTile(getModel().getPosition().getRight()));
        getModel().getPosition().setRight();
    }

    public void moveUp(Grid grid) {
        grid.getTile(getModel().getPosition()).moveTile(grid.getTile(getModel().getPosition().getUp()));
        getModel().getPosition().setUp();
    }

    public void moveDown(Grid grid) {
        grid.getTile(getModel().getPosition()).moveTile(grid.getTile(getModel().getPosition().getDown()));
        getModel().getPosition().setDown();
    }

    public HeroView getView() {
        return view;
    }

    public void setView(HeroView view) {
        this.view = view;
    }
}
