package org.subquark.tetris_station.rooms.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

public class GiveMineralsActivation implements RoomActivation {
    private int amount;
    
    public GiveMineralsActivation(int amount) {
        this.amount = amount;
    }

    @Override
    public void activate(GameState gs) {
        gs.availableMetal = Math.min(GameConstants.MAX_METAL, gs.availableMetal + amount);
    }
}
