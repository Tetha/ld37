package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.cards.actions.BuildEngineAction;
import org.subquark.tetris_station.cards.actions.CardAction;

public class Card {
    public String headline;
    public String description;
    public CardAction playAction;
    
    public static Card createBuildEngineCard() {
        Card result = new Card();
        result.headline = "Buid Engine";
        result.playAction = new BuildEngineAction();
        return result;
    }
}
