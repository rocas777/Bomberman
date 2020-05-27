package com.noclue;

import com.noclue.controller.FieldController;

public class Visitor {
    public static void visitDoor(FieldController fieldController) {

        fieldController.getModel().setWon(true);
        fieldController.getModel().gettServer().removeListener(fieldController);
        fieldController.getView().draw();
        fieldController.setView(fieldController.getWinView());
        fieldController.getView().draw();
        fieldController.setEnded(true);
    }

    public static void visitCoin(FieldController fieldController) {
        fieldController.getModel().addPoint();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();
    }

    public static void visitTime(FieldController fieldController) {
        fieldController.getTimeLeft().addTime();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();
    }

    public static void visitLife(FieldController fieldController) {
        fieldController.getModel().getHero().addLife();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();
    }

    public static void visitInvencible(FieldController fieldController) {
        fieldController.getModel().getHero().ActivateInvencible();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();
    }
}
