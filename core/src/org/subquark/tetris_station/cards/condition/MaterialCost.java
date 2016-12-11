package org.subquark.tetris_station.cards.condition;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.cards.Card;

public class MaterialCost implements PlayCondition {
    private int materialCost;
    
    public MaterialCost(int materialCost) {
        this.materialCost = materialCost;
    }
    
    @Override
    public void setPlayable(GameState gs, Card c) {
        if (gs.availableMetal < materialCost) {
            c.cannotPlayReason = "Not enough material";
            c.canPlay = false;
        } else {
            c.canPlay = true;
            c.cannotPlayReason = "";
        }        
    }
}
