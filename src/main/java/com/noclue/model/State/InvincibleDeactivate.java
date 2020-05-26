package com.noclue.model.State;

import com.noclue.model.LivesModel;

public class InvincibleDeactivate extends DeactivateState {
    @Override
    public boolean deactivate(LivesModel livesModel) {
        return false;
    }
}
