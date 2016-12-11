package org.subquark.tetris_station.deck;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.subquark.tetris_station.cards.Card;

public class DeckBuilder {
    private static List<Card> meanCards = Arrays.asList(
            Card.createSmallHostileFleet(),
            Card.createSmallHostileFleet(),
            Card.createSmallHostileFleet(),
            Card.createSmallHostileFleet(),
            Card.createSmallHostileFleet(),
            Card.createMediumHostileFleet(),
            Card.createMediumHostileFleet(),
            Card.createMediumHostileFleet(),
            Card.createLargeHostileFleet()
    );
    
    private static final int NUM_MEAN_CARDS = 10;
    
    public static Deck createDefaultDeck() {
        Deck result = new Deck();
        Random random = new Random();
        addNecessaryCards(result, random);
        addMeanCards(result, random);
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
    
    private static void addMeanCards(Deck d, Random r) {
        for (int i = 0; i < NUM_MEAN_CARDS; i++) {
            int randomIndex = r.nextInt(meanCards.size());
            d.addCard(meanCards.get(randomIndex).shallowClone());
        }
    }
}
