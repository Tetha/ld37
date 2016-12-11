package org.subquark.tetris_station.activation;

import java.util.ArrayList;
import java.util.List;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.activation.actions.MoveCloserToSun;
import org.subquark.tetris_station.activation.actions.PostActivationAction;
import org.subquark.tetris_station.rooms.RoomGridDisplay;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ActivationOverlay extends Actor {
    final List<PostActivationAction> postActivationActions = new ArrayList<>();
    
    public ActivationOverlay(final RoomActivationOverlay roomOverlay, final RoomGridDisplay gridDisplay, final GameState gameState) {
        postActivationActions.add(new MoveCloserToSun());
        
        this.setBounds(gridDisplay.getParent().getX(), 
                gridDisplay.getParent().getY(), 
                GameConstants.GRID_WIDTH_TILE * GameConstants.TILE_WIDTH_PX,
                GameConstants.GRID_HEIGHT_TILE * GameConstants.TILE_HEIGHT_PX);
        
        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
                if (gridDisplay.areStageCoordsOnGrid(x, y)) {
                    roomOverlay.setTileX(gridDisplay.xPixelToGridTile(x));
                    roomOverlay.setTileY(gridDisplay.yPixelToGridTile(y));
                    roomOverlay.updateActivations();
                    
                    roomOverlay.activateRooms();
                    
                    for (PostActivationAction action : postActivationActions) {
                        action.act(gameState);
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
}
