package org.subquark.tetris_station.activation;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.rooms.RoomGridDisplay;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ActivationOverlay extends Actor {
    public ActivationOverlay(final RoomActivationOverlay roomOverlay, final RoomGridDisplay gridDisplay) {
        this.setBounds(gridDisplay.getParent().getX(), 
                gridDisplay.getParent().getY(), 
                GameConstants.GRID_WIDTH_TILE * GameConstants.TILE_WIDTH_PX,
                GameConstants.GRID_HEIGHT_TILE * GameConstants.TILE_HEIGHT_PX);
        
        this.addListener(new InputListener() {
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
