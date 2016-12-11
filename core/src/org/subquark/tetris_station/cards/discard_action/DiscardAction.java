package org.subquark.tetris_station.cards.discard_action;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.build_overlay.BuildOverlay;

public interface DiscardAction {
    public void run(GameState gs, BuildOverlay buildOverlay);

}
