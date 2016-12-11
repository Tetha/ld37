package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

public class MoveCloserToSun implements PostActivationAction {

    @Override
    public void act(GameState gs) {
        gs.shipPosition = Math.min(GameConstants.MAX_SUN_DISTANCE, gs.shipPosition + 1);
    }
}
