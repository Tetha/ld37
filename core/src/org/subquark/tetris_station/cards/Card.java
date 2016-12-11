package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.cards.actions.BuildDefenseGun;
import org.subquark.tetris_station.cards.actions.BuildEnergyTransmitter1;
import org.subquark.tetris_station.cards.actions.BuildEnergyTransmitter2;
import org.subquark.tetris_station.cards.actions.BuildEngineAction;
import org.subquark.tetris_station.cards.actions.BuildMetalGenerator;
import org.subquark.tetris_station.cards.actions.BuildWarpCore;
import org.subquark.tetris_station.cards.actions.CardAction;
import org.subquark.tetris_station.cards.actions.CombatThrustAction;
import org.subquark.tetris_station.cards.condition.AlwaysPlayable;
import org.subquark.tetris_station.cards.condition.DistanceFromSun;
import org.subquark.tetris_station.cards.condition.Impossible;
import org.subquark.tetris_station.cards.condition.MaterialCost;
import org.subquark.tetris_station.cards.condition.PlayCondition;
import org.subquark.tetris_station.cards.discard_action.DiscardAction;
import org.subquark.tetris_station.cards.discard_action.SpawnFighters;

public class Card {
    public String headline;
    public String description;
    public CardAction playAction;
    public String playDescription;
    public String discardDescription;
    
    public PlayCondition playCondition;
    public boolean canPlay;
    public String cannotPlayReason;
    protected DiscardAction discardAction;
    public int cost = -1;
    public String flavorText;
    
    public Card() {}

    private Card(Card c) {
        this.headline = c.headline;
        this.description = c.description;
        this.playAction = c.playAction;
        this.playDescription = c.playDescription;
        this.discardDescription = c.discardDescription;
        this.playCondition = c.playCondition;
        this.canPlay = c.canPlay;
        this.cannotPlayReason = c.cannotPlayReason;
        this.discardAction = c.discardAction;
        this.cost = c.cost;
        this.flavorText = c.flavorText;
    }
    
    public Card shallowClone() {
        return new Card(this); 
    }
    public static Card createBuildDefenseGun() {
        Card result = new Card();
        result.headline = "Defense Gun";
        result.playAction = new BuildDefenseGun();
        
        result.playDescription = "Build a Defense Gun\nIf you activate a Defense gun,\nIt destroys 1 enemy fighter";
        
        result.cost = GameConstants.DEFENSE_GUN_COST;
        result.playCondition = new MaterialCost(GameConstants.DEFENSE_GUN_COST);
        
        result.flavorText = "'The collectors think they are moving the most\n"
                          + "metal on this ship. They are dead wrong.' -- Gunnery Seargent";
        return result;
    }

    public static Card createBuildEnergyTransmitter1() {
        Card result = new Card();
        result.headline = "Horizontal\nTransmitter";
        result.playAction = new BuildEnergyTransmitter1();
        
        result.playDescription = "Build an Energy Transmitter\nIf you activate an energy transmitter,\nIt activates adjacent rooms";

        result.cost = GameConstants.ENERGY_TRANSMITTER_COST;
        result.playCondition = new MaterialCost(GameConstants.ENERGY_TRANSMITTER_COST);
        
        result.flavorText = "'We call this the room of funny hairstyles'\n"
                          + "-- an Engineer";
        return result;
    }
    
    public static Card createBuildEnergyTransmitter2() {
        Card result = new Card();
        result.headline = "Vertical\nTransmitter";
        result.playAction = new BuildEnergyTransmitter2();
        
        result.playDescription = "Build an Energy Transmitter\nIf you activate an energy transmitter,\nIt activates adjacent rooms";

        result.cost = GameConstants.ENERGY_TRANSMITTER_COST;
        result.playCondition = new MaterialCost(GameConstants.ENERGY_TRANSMITTER_COST);

        result.flavorText = "'We call this the room of funny hairstyles'\n"
                + "-- an Engineer";
        return result;
    }
    
    public static Card createBuildEngineCard() {
        Card result = new Card();
        result.headline = "Engine";
        result.playAction = new BuildEngineAction();
        
        result.playDescription = "Build an Engine\nIf you activate an engine,\nIt moves you 1 spot away from the sun";
        
        result.cost = GameConstants.ENGINE_COST;
        result.playCondition = new MaterialCost(GameConstants.ENGINE_COST);

        result.flavorText = "Controlled Explosions in the ship to keep\n"
                          + "the station away from the giant natural\n"
                          + "explosion";
        return result;
    }
    
    public static Card createBuildMetalGenerator() {
        Card result = new Card();
        result.headline = "Metal\nGenerator";
        result.playAction = new BuildMetalGenerator();
        
        result.playDescription = "Build a Metal Generator\nIf you activate a Metal Generator\nIt generates 1 unit of Metal";
        
        result.cost = GameConstants.METAL_GENERATOR_COST;
        result.playCondition = new MaterialCost(GameConstants.METAL_GENERATOR_COST);

        result.flavorText = "'Generator' the engineer sneers at you.\n"
                            + "'It's a giant magnet, nothing else.'\n"
                            + "'Be mindful with those keys though'";
        return result;
    }
    
    public static Card createWarpCore() {
        Card result = new Card();
        result.headline = "Warp Core";
        result.playAction = new BuildWarpCore();
        
        result.playDescription = "Build a warp core\nIf you activate a Warp Core, \nYou gain a Warp-Point.\nGain " +  GameConstants.MAX_HYPER_POINTS + " points to win!";
        
        result.cost = GameConstants.WARP_CORE_COST;
        result.playCondition = new MaterialCost(GameConstants.WARP_CORE_COST);
        
        result.flavorText = "'How this works?' the scientist wonders"
                          + "'Well, if only we knew. Last guy who tried\n"
                          + "to understand too hard went mad. Badly'";
        return result;
    }
    
    public static Card createSmallHostileFleet() {
        Card result = new Card();
        result.headline = "Small Scouts";
        
        result.playCondition = new Impossible();
        
        result.discardDescription = "Two hostile fighters warp in.";
        result.discardAction = new SpawnFighters(2);
        
        result.flavorText = "'They just blinked in. Probably on a test flight\n"
                          + "But still, they're here now' -- Sensor Crew";
        return result;
    }
    
    public static Card createMediumHostileFleet() {
        Card result = new Card();
        result.headline = "Patrol Group";
        
        result.playCondition = new Impossible();
        
        result.discardDescription = "Three hostile fighters warp in.";
        result.discardAction = new SpawnFighters(3);
        
        result.flavorText = "'Standard hostile patrol behavior. Just a matter\n"
                          + "of tme till they found us' -- Tactical Officer";
        return result;
    }
    
    public static Card createLargeHostileFleet() {
        Card result = new Card();
        result.headline = "Combat Group";
        
        result.playCondition = new Impossible();
        
        result.discardDescription = "Five hostile fighters warp in.";
        result.discardAction = new SpawnFighters(5);
        
        result.flavorText = "'ALL MAN ON BRIDGE, WE ARE UNDER DIRECT ASSAULT'";
        return result;
    }
    
    public static Card createCombatThrust() {
        Card result = new Card();
        result.headline = "Combat Thrust";
        
        result.playCondition = new DistanceFromSun(GameConstants.MAX_SUN_DISTANCE-1);
        result.playDescription = " -1 distance to the sun\n"
                               + " -2 hostile fighters";
        result.playAction = new CombatThrustAction();
        
        result.flavorText = "'If I can ignore the sun, I can hit\n"
                          + "some of these jerks with that engine'\n"
                          + "-- Old Engineer";
        return result;
    }
}
