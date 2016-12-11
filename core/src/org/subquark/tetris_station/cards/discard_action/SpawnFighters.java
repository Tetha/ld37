package org.subquark.tetris_station.cards.discard_action;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.build_overlay.BuildOverlay;

public class SpawnFighters implements DiscardAction {
    public int amount;
    
    public SpawnFighters(int amount) {
        this.amount = amount;
    }
    
    @Override
    public void run(GameState gs, BuildOverlay buildOverlay) {
        gs.hostileShips = Math.min(GameConstants.MAX_FIGHTERS, gs.hostileShips + amount);
    }
}
