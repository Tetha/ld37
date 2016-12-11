package org.subquark.tetris_station.activation;

import java.util.ArrayList;
import java.util.List;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.activation.actions.ApplyDamageFromFighters;
import org.subquark.tetris_station.activation.actions.ApplyDamageFromSun;
import org.subquark.tetris_station.activation.actions.LoseWithNoHealth;
import org.subquark.tetris_station.activation.actions.MoveCloserToSun;
import org.subquark.tetris_station.activation.actions.PostActivationAction;
import org.subquark.tetris_station.activation.actions.RefillHand;
import org.subquark.tetris_station.activation.actions.WinWithMaxHyperPoints;
import org.subquark.tetris_station.build_overlay.BuildOverlay;
import org.subquark.tetris_station.rooms.Room;
import org.subquark.tetris_station.rooms.RoomGridDisplay;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ActivationOverlay extends Actor {
    final List<PostActivationAction> postActivationActions = new ArrayList<>();
    private RoomActivationOverlay roomOverlay;
    private List<Group> exclusiveGroups;
    
    private List<Runnable> activationSelectionStartedCallbacks = new ArrayList<Runnable>();
    private List<Runnable> turnEndCallbacks = new ArrayList<Runnable>();
    
    public ActivationOverlay(final RoomActivationOverlay roomOverlay,
                             final RoomGridDisplay gridDisplay,
                             final GameState gameState,
                             Group lossScreen,
                             Group winScreen,
                             Label winLabel,
                             List<Group> exclusiveGroups) {
        postActivationActions.add(new ApplyDamageFromFighters());
        postActivationActions.add(new ApplyDamageFromSun());
        postActivationActions.add(new MoveCloserToSun());
        postActivationActions.add(new LoseWithNoHealth());
        postActivationActions.add(new WinWithMaxHyperPoints());
        postActivationActions.add(new RefillHand());
        
        this.roomOverlay = roomOverlay;
        this.exclusiveGroups = exclusiveGroups;
        
        this.setBounds(gridDisplay.getParent().getX(), 
                gridDisplay.getParent().getY(), 
                GameConstants.GRID_WIDTH_TILE * GameConstants.TILE_WIDTH_PX,
                GameConstants.GRID_HEIGHT_TILE * GameConstants.TILE_HEIGHT_PX);
        roomOverlay.setTouchable(Touchable.disabled);
        this.setTouchable(Touchable.disabled);
        roomOverlay.setVisible(false);
        this.setVisible(false);
        
        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
                if (gridDisplay.areStageCoordsOnGrid(x, y)) {
                    roomOverlay.setTileX(gridDisplay.xPixelToGridTile(x));
                    roomOverlay.setTileY(gridDisplay.yPixelToGridTile(y));
                    roomOverlay.updateActivations();
                    
                    roomOverlay.activateRooms();
                    
                    for (PostActivationAction action : postActivationActions) {
                        action.act(gameState, lossScreen, winScreen, winLabel);
                    }
                    
                    for(Runnable r : turnEndCallbacks) {
                        r.run();
                    }
                    
                    roomOverlay.setTouchable(Touchable.disabled);
                    ActivationOverlay.this.setTouchable(Touchable.disabled);
                    roomOverlay.setVisible(false);
                    ActivationOverlay.this.setVisible(false);
                    
                    for (Group eg : exclusiveGroups) {
                        eg.setTouchable(Touchable.enabled);
                    }
                    return true;
                }
                return false;
            }
            @Override
            public boolean mouseMoved(InputEvent e, float x, float y) {
                if (gridDisplay.areStageCoordsOnGrid(x, y)) {
                    roomOverlay.setTileX(gridDisplay.xPixelToGridTile(x));
                    roomOverlay.setTileY(gridDisplay.yPixelToGridTile(y));
                    roomOverlay.updateActivations();
                }
                return false;
            }
        });
    }

    public void addActivationEnabledCallback(Runnable r) {
        this.activationSelectionStartedCallbacks.add(r);
    }
    
    public void addEndTurnCallback(Runnable r) {
        this.turnEndCallbacks.add(r);
    }
    
    public void enable() {
        roomOverlay.setTouchable(Touchable.enabled);
        this.setTouchable(Touchable.enabled);
        
        roomOverlay.setVisible(true);
        this.setVisible(true);

        for (Group eg : exclusiveGroups) {
            eg.setTouchable(Touchable.disabled);
        }
        
        for(Runnable r : activationSelectionStartedCallbacks) {
            r.run();
        }
    }
}
