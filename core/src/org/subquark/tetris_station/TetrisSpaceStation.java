package org.subquark.tetris_station;

import org.subquark.tetris_station.build_overlay.BuildOverlay;
import org.subquark.tetris_station.build_overlay.RoomOverlay;
import org.subquark.tetris_station.cards.BigCardOverlay;
import org.subquark.tetris_station.cards.Card;
import org.subquark.tetris_station.cards.HandDisplay;
import org.subquark.tetris_station.rooms.Room;
import org.subquark.tetris_station.rooms.RoomGrid;
import org.subquark.tetris_station.scores.HostileShipDisplay;
import org.subquark.tetris_station.scores.HyperPointDisplay;
import org.subquark.tetris_station.scores.MetalDisplay;
import org.subquark.tetris_station.scores.SolarDistanceDisplay;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TetrisSpaceStation extends ApplicationAdapter {
	private Stage stage;
	
	@Override
	public void create () {
	    stage = new Stage(new StretchViewport(800, 600));
	    
	    GameState gameState = new GameState();   
        gameState.hostileShips = 5;
        gameState.availableMetal = 12;
        gameState.shipPosition = 3;
        gameState.hyperPointsEarned = 7;
        gameState.maxCards = 5;
        gameState.cards.add(Card.createBuildEngineCard());
        
        Group gameArea = new Group();
        stage.addActor(gameArea);
        
        Group rooms = new Group();
        rooms.setY(100);
        RoomGrid grid = new RoomGrid(rooms);
        gameArea.addActor(rooms);
        
        RoomOverlay overlay = new RoomOverlay(grid);
        rooms.addActor(overlay);
        overlay.setRoom(Room.createDefenseGun());
        BuildOverlay buildOverlay = new BuildOverlay(grid.getDisplay(), overlay, grid); 
        gameArea.addActor(buildOverlay);
        
        Table cardLayout = new Table();
        BigCardOverlay bigCardOverlay = new BigCardOverlay(gameArea, cardLayout, buildOverlay);
        stage.addActor(cardLayout);
        
	    Group hand = new Group();
	    gameArea.addActor(hand);
	    new HandDisplay(gameState, hand, bigCardOverlay);
	    	    
	    Group scores = new Group();
	    gameArea.addActor(scores);
	    scores.setX(620);

	    HostileShipDisplay hostileShipDisplay = new HostileShipDisplay(gameState);
	    scores.addActor(hostileShipDisplay);
	    
	    MetalDisplay metalDisplay = new MetalDisplay(gameState);
	    scores.addActor(metalDisplay);
	    metalDisplay.setY(50);
	    
	    SolarDistanceDisplay distanceDisplay = new SolarDistanceDisplay(gameState);
	    scores.addActor(distanceDisplay);
	    distanceDisplay.setY(225);
	    
	    HyperPointDisplay hyperDisplay = new HyperPointDisplay(gameState);
	    scores.addActor(hyperDisplay);
	    hyperDisplay.setY(275);

	    
	    Room transmitter1_1 =Room.createEnergyTransmitter1();
	    transmitter1_1.setTileX(4);
	    transmitter1_1.setTileY(0);
	    grid.addRoom(transmitter1_1);

	    Room transmitter1_2 = Room.createEnergyTransmitter1();
	    transmitter1_2.setTileX(5);
	    transmitter1_2.setTileY(6);
	    grid.addRoom(transmitter1_2);
	        
	    Room transmitter2 = Room.createEnergyTransmitter2();
	    transmitter2.setTileX(5);
	    transmitter2.setTileY(2);
	    grid.addRoom(transmitter2);
	    
        Room matterGenerator = Room.createMetalGenerator();
        matterGenerator.setTileX(8);
        matterGenerator.setTileY(0);
        grid.addRoom(matterGenerator);
        
        Room defenseGun = Room.createDefenseGun();
        defenseGun.setTileX(9);
        defenseGun.setTileY(6);
        grid.addRoom(defenseGun);
        
        Room engine = Room.createEngine();
        engine.setTileX(1);
        engine.setTileY(5);
        grid.addRoom(engine);
	    
        Room warpCore = Room.createWarpCore();
        warpCore.setTileX(4);
        warpCore.setTileY(8);
        grid.addRoom(warpCore);

	    Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
	    stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void render () {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
	}
}
