package org.subquark.tetris_station.deck;

import java.util.Random;

import org.subquark.tetris_station.cards.Card;

public class DeckBuilder {
    public static Deck createDefaultDeck() {
        Deck result = new Deck();
        Random random = new Random();
        addNecessaryCards(result, random);
        return result;
    }
    
    private static void addNecessaryCards(Deck d, Random r) {
        d.addCard(Card.createBuildDefenseGun());
        if (r.nextBoolean()) {
            d.addCard(Card.createBuildEnergyTransmitter1());
        } else {
            d.addCard(Card.createBuildEnergyTransmitter2());
        }
        d.addCard(Card.createBuildEngineCard());
        d.addCard(Card.createBuildMetalGenerator());
        d.addCard(Card.createWarpCore());
    }
}
