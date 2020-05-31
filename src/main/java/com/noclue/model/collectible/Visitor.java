package com.noclue.model.collectible;

import com.noclue.controller.FieldController;

public class Visitor {
    //the players picks up a door
    public static void visitDoor(FieldController fieldController) {

        fieldController.getModel().setWon(true);
        fieldController.getModel().gettServer().removeListener(fieldController);
        fieldController.getView().draw();   //draw last info from normal game
        fieldController.setView(fieldController.getWinView());  //sets normal view to the winning view
        fieldController.getView().draw();   //draw win screen
        fieldController.setEnded(true);
    }

    //the players picks up a coin
    public static void visitCoin(FieldController fieldController) {
        fieldController.getModel().addPoint();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();   //clear collectible
    }

    //the players picks up an AddTime
    public static void visitTime(FieldController fieldController) {
        fieldController.getTimeLeft().addTime();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();   //clear collectible
    }

    //the players picks up an AddLife
    public static void visitLife(FieldController fieldController) {
        fieldController.getModel().getHero().addLife();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();   //clear collectible
    }

    //the players picks up the thing that gets him powered-up
    public static void visitInvencible(FieldController fieldController) {
        fieldController.getModel().getHero().ActivateInvencible();
        fieldController.getModel().getTiles().getTile(fieldController.getModel().getHero().getPosition()).blankCollectible();   //clear collectible
    }
}
