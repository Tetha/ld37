package org.subquark.tetris_station.cards.condition;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.cards.Card;

public interface PlayCondition {
    public void setPlayable(GameState gs, Card c);
}
