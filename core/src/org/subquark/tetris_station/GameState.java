package org.subquark.tetris_station;

import java.util.ArrayList;
import java.util.List;

import org.subquark.tetris_station.cards.Card;

public class GameState {
    public int hostileShips;
    public int availableMetal;
    public int shipPosition;
    public int hyperPointsEarned;
    
    public int maxCards;
    public List<Card> cards = new ArrayList<Card>();
}
