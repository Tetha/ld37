package org.subquark.tetris_station.cards.condition;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.cards.Card;

public class AlwaysPlayable implements PlayCondition {
    @Override
    public void setPlayable(GameState gs, Card c) {
        c.canPlay = true;
        c.cannotPlayReason = "";
    }
}
