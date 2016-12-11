package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.cards.actions.BuildDefenseGun;
import org.subquark.tetris_station.cards.actions.BuildEnergyTransmitter1;
import org.subquark.tetris_station.cards.actions.BuildEnergyTransmitter2;
import org.subquark.tetris_station.cards.actions.BuildEngineAction;
import org.subquark.tetris_station.cards.actions.BuildMetalGenerator;
import org.subquark.tetris_station.cards.actions.BuildWarpCore;
import org.subquark.tetris_station.cards.actions.CardAction;
import org.subquark.tetris_station.cards.condition.MaterialCost;
import org.subquark.tetris_station.cards.condition.PlayCondition;

public class Card {
    public String headline;
    public String description;
    public CardAction playAction;
    public String playDescription;
    public String discardDescription;
    
    private PlayCondition condition;
    public boolean canPlay;
    public String cannotPlayReason;
    
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
        
        result.condition = new MaterialCost(GameConstants.DEFENSE_GUN_COST);
        return result;
    }

    public static Card createBuildEnergyTransmitter1() {
        Card result = new Card();
        result.headline = "Horizontal\nTransmitter";
        result.playAction = new BuildEnergyTransmitter1();
        
        result.playDescription = "Build an Energy Transmitter\nIf you activate an energy transmitter,\nIt activates adjacent rooms";

        result.condition = new MaterialCost(GameConstants.ENERGY_TRANSMITTER_COST);
        return result;
    }
    
    public static Card createBuildEnergyTransmitter2() {
        Card result = new Card();
        result.headline = "Vertical\nTransmitter";
        result.playAction = new BuildEnergyTransmitter2();
        
        result.playDescription = "Build an Energy Transmitter\nIf you activate an energy transmitter,\nIt activates adjacent rooms";

        result.condition = new MaterialCost(GameConstants.ENERGY_TRANSMITTER_COST);
        
        return result;
    }
    
    public static Card createBuildEngineCard() {
        Card result = new Card();
        result.headline = "Engine";
        result.playAction = new BuildEngineAction();
        
        result.playDescription = "Build an Engine\nIf you activate an engine,\nIt moves you 1 spot away from the sun";
        
        result.condition = new MaterialCost(GameConstants.ENGINE_COST);

        return result;
    }
    
    public static Card createBuildMetalGenerator() {
        Card result = new Card();
        result.headline = "Metal\nGenerator";
        result.playAction = new BuildMetalGenerator();
        
        result.playDescription = "Build a Metal Generator\nIf you activate a Metal Generator\nIt generates 1 unit of Metal";
        
        result.condition = new MaterialCost(GameConstants.METAL_GENERATOR_COST);

        return result;
    }
    
    public static Card createWarpCore() {
        Card result = new Card();
        result.headline = "Warp Core";
        result.playAction = new BuildWarpCore();
        
        result.playDescription = "Build a warp core\nIf you activate a Warp Core, \nYou gain a Warp-Point.\nGain " +  GameConstants.MAX_HYPER_POINTS + " points to win!";
        
        result.condition = new MaterialCost(GameConstants.WARP_CORE_COST);
        
        return result;
    }
}
