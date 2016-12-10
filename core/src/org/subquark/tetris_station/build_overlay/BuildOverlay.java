package org.subquark.tetris_station.build_overlay;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.rooms.RoomGridDisplay;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BuildOverlay extends Actor {
    private final RoomOverlay roomOverlay;
    
    public BuildOverlay(final RoomGridDisplay gridDisplay, final RoomOverlay roomOverlay) {
        this.roomOverlay = roomOverlay;
        this.setBounds(gridDisplay.getParent().getX(), 
                       gridDisplay.getParent().getY(), 
                       GameConstants.GRID_WIDTH_TILE * GameConstants.TILE_WIDTH_PX,
                       GameConstants.GRID_HEIGHT_TILE * GameConstants.TILE_HEIGHT_PX);
        this.setDebug(true);
        this.addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent e, float x, float y) {
                System.out.println("Mouse moved");
                if (gridDisplay.areStageCoordsOnGrid(x, y)) {
                    roomOverlay.setTileX(gridDisplay.xPixelToGridTile(x));
                    roomOverlay.setTileY(gridDisplay.yPixelToGridTile(y) + 1);
                }
                return false;
            }
        });
    }
    
}
