package org.subquark.tetris_station.cards.condition;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.cards.Card;

public class DistanceFromSun implements PlayCondition {
    private int minimum;
    
    public DistanceFromSun(int minimum) {
        this.minimum = minimum;
    }
    @Override
    public void setPlayable(GameState gs, Card c) {
        if (gs.shipPosition < minimum) {
            c.canPlay = true;
        } else {
            c.canPlay = false;
            c.cannotPlayReason = "Too close to sun";
        }
    }
}
