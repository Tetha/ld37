package org.subquark.tetris_station;

import java.util.Arrays;

import org.subquark.tetris_station.activation.ActivationOverlay;
import org.subquark.tetris_station.activation.RoomActivationOverlay;
import org.subquark.tetris_station.build_overlay.BuildOverlay;
import org.subquark.tetris_station.build_overlay.RoomOverlay;
import org.subquark.tetris_station.cards.BigCardOverlay;
import org.subquark.tetris_station.cards.Card;
import org.subquark.tetris_station.cards.HandDisplay;
import org.subquark.tetris_station.deck.DeckBuilder;
import org.subquark.tetris_station.rooms.Room;
import org.subquark.tetris_station.rooms.RoomGrid;
import org.subquark.tetris_station.scores.HostileShipDisplay;
import org.subquark.tetris_station.scores.HyperPointDisplay;
import org.subquark.tetris_station.scores.MetalDisplay;
import org.subquark.tetris_station.scores.SolarDistanceDisplay;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
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
        gameState.deck = DeckBuilder.createDefaultDeck();
        gameState.refreshHand();
        
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
        
        Table scores = new Table();
        Group hand = new Group();

        RoomActivationOverlay activationRoomOverlay = new RoomActivationOverlay(grid, gameState);
        rooms.addActor(activationRoomOverlay);
        ActivationOverlay activationOverlay = new ActivationOverlay(activationRoomOverlay,
                                                                    grid.getDisplay(),
                                                                    gameState,
                                                                    Arrays.asList(scores, hand));
        gameArea.addActor(activationOverlay);      

        
        
        Table cardLayout = new Table();
        BigCardOverlay bigCardOverlay = new BigCardOverlay(gameArea, cardLayout, buildOverlay, gameState);
        stage.addActor(cardLayout);
        
	    gameArea.addActor(hand);
	    new HandDisplay(gameState, hand, bigCardOverlay);
	    	    
	    gameArea.addActor(scores);
	    scores.setDebug(true);
	    scores.setX(620);
	    scores.setHeight(600);
	    scores.setWidth(800 - 620);

	    HostileShipDisplay hostileShipDisplay = new HostileShipDisplay(gameState);
	    scores.add(hostileShipDisplay)
	          .width(hostileShipDisplay.getWidth())
	          .height(hostileShipDisplay.getHeight())
	          .pad(10)
	          .row();
	    
	    MetalDisplay metalDisplay = new MetalDisplay(gameState);
	    scores.add(metalDisplay)
	          .width(metalDisplay.getWidth())
	          .height(metalDisplay.getHeight())
	          .pad(10)
	          .row();
	    
	    SolarDistanceDisplay distanceDisplay = new SolarDistanceDisplay(gameState);
	    scores.add(distanceDisplay)
	          .width(distanceDisplay.getWidth())
	          .height(distanceDisplay.getHeight())
	          .pad(10)
	          .row();
	    
	    HyperPointDisplay hyperDisplay = new HyperPointDisplay(gameState);
	    scores.add(hyperDisplay)
	          .width(hyperDisplay.getWidth())
	          .height(hyperDisplay.getHeight())
	          .pad(10)
	          .row();
	    
        TextButtonStyle style = new TextButtonStyle();
        style.font = new BitmapFont();
        Button activateButton = new TextButton("Activate one Room\nand End Turn", style);
        activateButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button Changed");
                activationOverlay.enable();
            }
        });
        scores.add(activateButton).pad(10).row();
        
/*        
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
*/
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
