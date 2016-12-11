package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.cards.actions.BuildDefenseGun;
import org.subquark.tetris_station.cards.actions.BuildEnergyTransmitter1;
import org.subquark.tetris_station.cards.actions.BuildEnergyTransmitter2;
import org.subquark.tetris_station.cards.actions.BuildEngineAction;
import org.subquark.tetris_station.cards.actions.BuildMetalGenerator;
import org.subquark.tetris_station.cards.actions.BuildWarpCore;
import org.subquark.tetris_station.cards.actions.CardAction;

public class Card {
    public String headline;
    public String description;
    public CardAction playAction;
    
    public static Card createBuildDefenseGun() {
        Card result = new Card();
        result.headline = "Build Defense Gun";
        result.playAction = new BuildDefenseGun();
        return result;
    }

    public static Card createBuildEnergyTransmitter1() {
        Card result = new Card();
        result.headline = "Build Horizontal Energy Transmitter";
        result.playAction = new BuildEnergyTransmitter1();
        return result;
    }
    
    public static Card createBuildEnergyTransmitter2() {
        Card result = new Card();
        result.headline = "Build Vertical Energy Transmitter";
        result.playAction = new BuildEnergyTransmitter2();
        return result;
    }
    
    public static Card createBuildEngineCard() {
        Card result = new Card();
        result.headline = "Build Engine";
        result.playAction = new BuildEngineAction();
        return result;
    }
    
    public static Card createBuildMetalGenerator() {
        Card result = new Card();
        result.headline = "Build Metal Generator";
        result.playAction = new BuildMetalGenerator();
        return result;
    }
    
    public static Card createWarpCore() {
        Card result = new Card();
        result.headline = "Build Warp Core";
        result.playAction = new BuildWarpCore();
        return result;
    }
}
