package com.noclue.model;

import javax.swing.table.TableRowSorter;

public abstract class Filler {
    protected boolean isActive = true;
    public abstract boolean isFilled();
    public abstract boolean deactivate();
    public boolean isActive() {
        return isActive;
    }
}
