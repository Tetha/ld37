package org.subquark.tetris_station.rooms.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

public class WarpCoreActivation implements RoomActivation {
    @Override
    public void activate(GameState gs) {
        gs.hyperPointsEarned = Math.min(GameConstants.MAX_HYPER_POINTS, gs.hyperPointsEarned + 1);
    }
}
