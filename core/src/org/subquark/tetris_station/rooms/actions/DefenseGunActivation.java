package org.subquark.tetris_station.rooms.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

public class DefenseGunActivation implements RoomActivation {

    @Override
    public void activate(GameState gs) {
        gs.hostileShips = Math.max(0, gs.hostileShips - GameConstants.FIGHTERS_PER_GUN);
    }

}
