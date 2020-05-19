package com.noclue.model.State;

import com.noclue.model.LivesModel;

public class Normal extends State{
    @Override
    public void deactivate(LivesModel livesModel) {
        livesModel.setLives(livesModel.getLives()-1);
    }
}
