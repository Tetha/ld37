package org.subquark.tetris_station.cards.actions;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.build_overlay.BuildOverlay;

public interface CardAction {
    public void run(GameState gs, BuildOverlay buildOverlay);
}
