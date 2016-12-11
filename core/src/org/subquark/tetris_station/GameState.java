package org.subquark.tetris_station;

import java.util.ArrayList;
import java.util.List;

import org.subquark.tetris_station.cards.Card;
import org.subquark.tetris_station.deck.Deck;

public class GameState {
    public int turns;
    public boolean cardPlayedThisTurn;
    
    public int hostileShips;
    public int availableMetal;
    public int shipPosition;
    public int hyperPointsEarned;
    public int health;
    
    public int maxCards;
    
    public Deck deck;
    public List<Card> cards = new ArrayList<Card>();
    
    public void refreshHand() {
        while (cards.size() < maxCards) {
            cards.add(deck.draw());
        }
    }
}
