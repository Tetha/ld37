package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

public class ApplyDamageFromSun implements PostActivationAction {

    @Override
    public void act(GameState gs) {
        System.out.println(String.format("Checking: %d > %d", gs.shipPosition, GameConstants.SUN_DISTANCE_THRESHOLD));
        if (gs.shipPosition >= GameConstants.SUN_DISTANCE_THRESHOLD) {
            gs.health = Math.max(0, gs.health - 1);
        }
    }
}
