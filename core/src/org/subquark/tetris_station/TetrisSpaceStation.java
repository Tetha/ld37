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
import org.subquark.tetris_station.scores.HealthDisplay;
import org.subquark.tetris_station.scores.HostileShipDisplay;
import org.subquark.tetris_station.scores.HyperPointDisplay;
import org.subquark.tetris_station.scores.MetalDisplay;
import org.subquark.tetris_station.scores.SolarDistanceDisplay;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TetrisSpaceStation extends ApplicationAdapter {
	private Stage stage;
	
	@Override
	public void create () {
	    stage = new Stage(new StretchViewport(800, 600));
	    
	    GameState gameState = new GameState();   
        gameState.hostileShips = 0;
        gameState.availableMetal = 3;
        gameState.shipPosition = 0;
        gameState.hyperPointsEarned = 0;
        gameState.maxCards = 5;
        gameState.health = 6;
        gameState.deck = DeckBuilder.createDefaultDeck();
        gameState.cards.add(Card.createBuildMetalGenerator());
        gameState.cards.add(Card.createBuildEngineCard());
        
        
        Group gameArea = new Group();
        stage.addActor(gameArea);
        
        Group lossScreen = new Group();
        stage.addActor(lossScreen);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        Pixmap redBox = new Pixmap(1, 1, Pixmap.Format.RGB565);
        redBox.setColor(Color.RED);
        redBox.fill();
        labelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(redBox)));
        final Label bigRedScreen = new Label("She' breaking apart! you hear someone yell.\nThen loud screeching, crashing, tearing...\nThen nothing.\nYou lost :(", labelStyle);
        bigRedScreen.setWidth(800);
        bigRedScreen.setHeight(600);
        lossScreen.addActor(bigRedScreen);
        lossScreen.setVisible(false);
        lossScreen.setTouchable(Touchable.disabled);
        
        Group winScreen = new Group();
        stage.addActor(winScreen);
        LabelStyle winLabelStyle = new LabelStyle();
        winLabelStyle.font = new BitmapFont();
        
        Pixmap greenBox = new Pixmap(1, 1, Pixmap.Format.RGB565);
        greenBox.setColor(0, 0.8f, 0, 1f);
        greenBox.fill();
        winLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(greenBox)));
        final Label bigGreenScreen = new Label("You hear the warp condensators discharge with a loud screeching\nThe solar system disappears\nYou have won :)", winLabelStyle);
        bigGreenScreen.setWidth(800);
        bigGreenScreen.setHeight(600);
        winScreen.addActor(bigGreenScreen);
        winScreen.setVisible(false);
        winScreen.setTouchable(Touchable.disabled);
        
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
        final ActivationOverlay activationOverlay = new ActivationOverlay(activationRoomOverlay,
                                                                    grid.getDisplay(),
                                                                    gameState,
                                                                    lossScreen,
                                                                    winScreen,
                                                                    bigGreenScreen,
                                                                    Arrays.asList(scores, hand));
        gameArea.addActor(activationOverlay);      

        
        
        Table cardLayout = new Table();
        BigCardOverlay bigCardOverlay = new BigCardOverlay(gameArea, cardLayout, buildOverlay, gameState);
        stage.addActor(cardLayout);

        
	    gameArea.addActor(hand);
	    new HandDisplay(gameState, hand, bigCardOverlay);
	    
	    LabelStyle instructionStyle = new LabelStyle();
	    instructionStyle.font = new BitmapFont();
        Pixmap greyBox = new Pixmap(1, 1, Pixmap.Format.RGB565);
        greyBox.setColor(0.5f, 0.5f, 0.5f, 1f);
        greyBox.fill();
        instructionStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(greyBox)));

	    gameArea.addActor(scores);
	    scores.setX(550);
	    scores.setHeight(600);
	    scores.setWidth(800 - 620);

	    HealthDisplay healthDisplay = new HealthDisplay(gameState);
	    scores.add(healthDisplay)
	          .width(healthDisplay.getWidth())
	          .height(healthDisplay.getHeight())
	          .pad(10);
	    scores.add(new Label("Health", instructionStyle)).row();
	    
	    HostileShipDisplay hostileShipDisplay = new HostileShipDisplay(gameState);
	    scores.add(hostileShipDisplay)
	          .width(hostileShipDisplay.getWidth())
	          .height(hostileShipDisplay.getHeight())
	          .pad(10);
	    scores.add(new Label("Hostiles\n(They hurt)", instructionStyle)).row();
	    
	    MetalDisplay metalDisplay = new MetalDisplay(gameState);
	    scores.add(metalDisplay)
	          .width(metalDisplay.getWidth())
	          .height(metalDisplay.getHeight())
	          .pad(10);
	    scores.add(new Label("Metal\n(building!)", instructionStyle)).row();
	    
	    SolarDistanceDisplay distanceDisplay = new SolarDistanceDisplay(gameState);
	    scores.add(distanceDisplay)
	          .width(distanceDisplay.getWidth())
	          .height(distanceDisplay.getHeight())
	          .pad(10);
	    scores.add(new Label("Solar\nDistance\n(It'll burn)", instructionStyle)).row();
	    
	    HyperPointDisplay hyperDisplay = new HyperPointDisplay(gameState);
	    scores.add(hyperDisplay)
	          .width(hyperDisplay.getWidth())
	          .height(hyperDisplay.getHeight())
	          .pad(10);
	    scores.add(new Label("Warp\nGet 10 to win", instructionStyle)).row();
	    

	    final Label playCardInstructions = new Label("Click on a card\ndown there", instructionStyle);
	    scores.add(playCardInstructions).pad(10).colspan(2).row();
	    
	    final Label activationInstructions = new Label("Click on a room\nActivated rooms are highlighted", instructionStyle);
	    scores.add(activationInstructions).colspan(2).row();
	    activationInstructions.setVisible(false);
	    
        TextButtonStyle style = new TextButtonStyle();
        style.font = new BitmapFont();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(greyBox)));
        final Button activateButton = new TextButton("Play more cards\nor click here\n to activate one room\nand end turn", style);
        activateButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button Changed");
                activationOverlay.enable();
            }
        });
        scores.add(activateButton).pad(10).colspan(2).row();
        activateButton.setVisible(false);
        activateButton.setTouchable(Touchable.disabled);
        
        Runnable showActivationInstructions = new Runnable() {
            @Override
            public void run() {
                activateButton.setVisible(false);
                activateButton.setTouchable(Touchable.disabled);
                
                activationInstructions.setVisible(true);
            }
        };
        activationOverlay.addActivationEnabledCallback(showActivationInstructions);
        
        Runnable showInstructions = new Runnable() {
            @Override
            public void run() {
                activationInstructions.setVisible(false);
                playCardInstructions.setVisible(true);           
           }
        };
        activationOverlay.addEndTurnCallback(showInstructions);
        
        Runnable enableActivateButton = new Runnable() {
            @Override
            public void run() {
                System.out.println("Enabling button again");
                activateButton.setVisible(true);
                activateButton.setTouchable(Touchable.enabled);
                
                playCardInstructions.setVisible(false);
            }
        };
        bigCardOverlay.addCardPlayedCallback(enableActivateButton);
        bigCardOverlay.addCardDiscardedCallback(enableActivateButton);
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
