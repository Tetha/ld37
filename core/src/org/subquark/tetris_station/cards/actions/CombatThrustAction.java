package org.subquark.tetris_station.cards.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.build_overlay.BuildOverlay;

public class CombatThrustAction implements CardAction {
    @Override
    public void run(GameState gs, BuildOverlay buildOverlay) {
        gs.hostileShips = Math.max(0, gs.hostileShips -2);
        gs.shipPosition = Math.min(GameConstants.MAX_SUN_DISTANCE, gs.shipPosition+1);
    }
}
