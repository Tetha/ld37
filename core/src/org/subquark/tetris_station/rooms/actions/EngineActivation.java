package org.subquark.tetris_station.rooms.actions;

import org.subquark.tetris_station.GameState;

public class EngineActivation implements RoomActivation {
    @Override
    public void activate(GameState gs) {
        gs.shipPosition = Math.max(0, gs.shipPosition-2);
    }
}
