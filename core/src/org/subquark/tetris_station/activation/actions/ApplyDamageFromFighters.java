package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

public class ApplyDamageFromFighters implements PostActivationAction {

    @Override
    public void act(GameState gs) {
        if (gs.hostileShips > GameConstants.FIGHTER_THRESHOLD) {
            gs.health = Math.max(0, gs.health - 1);
        }
    }
}
