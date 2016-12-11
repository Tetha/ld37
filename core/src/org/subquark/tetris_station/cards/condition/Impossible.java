package org.subquark.tetris_station.cards.condition;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.cards.Card;

public class Impossible implements PlayCondition {

    @Override
    public void setPlayable(GameState gs, Card c) {
        c.canPlay = false;
        c.cannotPlayReason = "Unplayable";
    }
}
