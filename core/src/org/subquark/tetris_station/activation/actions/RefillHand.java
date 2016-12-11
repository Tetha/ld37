package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameState;

public class RefillHand implements PostActivationAction {

    @Override
    public void act(GameState gs) {
        gs.refreshHand();
    }

}
