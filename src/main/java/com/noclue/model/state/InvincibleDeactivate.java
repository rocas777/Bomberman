package com.noclue.model.state;

import com.noclue.model.LivesModel;

public class InvincibleDeactivate extends DeactivateState {
    @Override
    public boolean deactivate(LivesModel livesModel) {
        return false;
    }
}
