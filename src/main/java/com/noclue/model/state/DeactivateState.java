package com.noclue.model.state;

import com.noclue.model.LivesModel;

public abstract class DeactivateState {
    public abstract boolean deactivate(LivesModel livesModel);
}
