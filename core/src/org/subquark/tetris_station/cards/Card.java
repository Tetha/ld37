package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.GameConstants;
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
    public String playDescription;
    public String discardDescription;
    
    public Card() {}

    private Card(Card c) {
        this.headline = c.headline;
        this.description = c.description;
        this.playAction = c.playAction;
        this.playDescription = c.playDescription;
        this.discardDescription = c.discardDescription;
    }
    
    public Card shallowClone() {
        return new Card(this); 
    }
    public static Card createBuildDefenseGun() {
        Card result = new Card();
        result.headline = "Defense Gun";
        result.playAction = new BuildDefenseGun();
        
        result.playDescription = "Build a Defense Gun\nIf you activate a Defense gun,\nIt destroys 1 enemy fighter";
        
        return result;
    }

    public static Card createBuildEnergyTransmitter1() {
        Card result = new Card();
        result.headline = "Horizontal\nTransmitter";
        result.playAction = new BuildEnergyTransmitter1();
        
        result.playDescription = "Build an Energy Transmitter\nIf you activate an energy transmitter,\nIt activates adjacent rooms";

        return result;
    }
    
    public static Card createBuildEnergyTransmitter2() {
        Card result = new Card();
        result.headline = "Vertical\nTransmitter";
        result.playAction = new BuildEnergyTransmitter2();
        
        result.playDescription = "Build an Energy Transmitter\nIf you activate an energy transmitter,\nIt activates adjacent rooms";

        return result;
    }
    
    public static Card createBuildEngineCard() {
        Card result = new Card();
        result.headline = "Engine";
        result.playAction = new BuildEngineAction();
        
        result.playDescription = "Build an Engine\nIf you activate an engine,\nIt moves you 1 spot away from the sun";
        
        return result;
    }
    
    public static Card createBuildMetalGenerator() {
        Card result = new Card();
        result.headline = "Metal\nGenerator";
        result.playAction = new BuildMetalGenerator();
        
        result.playDescription = "Build a Metal Generator\nIf you activate a Metal Generator\nIt generates 1 unit of Metal";
        
        return result;
    }
    
    public static Card createWarpCore() {
        Card result = new Card();
        result.headline = "Warp Core";
        result.playAction = new BuildWarpCore();
        
        result.playDescription = "Build a warp core\nIf you activate a Warp Core, \nYou gain a Warp-Point.\nGain " +  GameConstants.MAX_HYPER_POINTS + " points to win!";
        
        return result;
    }
}
