package org.subquark.tetris_station.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.subquark.tetris_station.cards.Card;

public class Deck {
    private List<Card> masterList = new ArrayList<Card>();
    private LinkedList<Card> currentStack = new LinkedList<Card>();
    
    public void addCard(Card c) {
        masterList.add(c);
    }
    
    public void shuffle() {
        currentStack.clear();
        for (Card c : masterList) {
            currentStack.add(c.shallowClone());
        }
        
	Random random = new Random();
        for(int index = 0; index < currentStack.size(); index += 1) {  
            Collections.swap(currentStack, index, index + random.nextInt(currentStack.size() - index));  
        }
    }

    
    public Card draw() {
        if (currentStack.isEmpty()) {
            shuffle();
        }
        return currentStack.removeFirst();
    }
}
