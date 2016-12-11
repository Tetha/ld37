package org.subquark.tetris_station.build_overlay;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.rooms.Room;
import org.subquark.tetris_station.rooms.RoomGrid;
import org.subquark.tetris_station.rooms.RoomGridDisplay;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class BuildOverlay extends Actor {
    private final RoomOverlay roomOverlay;
    
    public BuildOverlay(final RoomGridDisplay gridDisplay, final RoomOverlay roomOverlay, final RoomGrid roomGrid) {
        this.roomOverlay = roomOverlay;
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
                    roomOverlay.checkValidity();
                    
                    if (roomOverlay.isValidLocation()) {
                        roomOverlay.initRoom();
                        roomGrid.addRoom(roomOverlay.getRoom());
                        
                        roomOverlay.setTouchable(Touchable.disabled);
                        BuildOverlay.this.setTouchable(Touchable.disabled);
                        roomOverlay.setVisible(false);
                        BuildOverlay.this.setVisible(false);
                    }
                }

                return true;
            }
            @Override
            public boolean mouseMoved(InputEvent e, float x, float y) {
                if (gridDisplay.areStageCoordsOnGrid(x, y)) {
                    roomOverlay.setTileX(gridDisplay.xPixelToGridTile(x));
                    roomOverlay.setTileY(gridDisplay.yPixelToGridTile(y));
                    roomOverlay.checkValidity();
                }
                return false;
            }
        });
    }

    public void enableWithRoom(Room room) {
        roomOverlay.setRoom(room);
        roomOverlay.setTouchable(Touchable.enabled);
        this.setTouchable(Touchable.enabled);
        
        roomOverlay.setVisible(true);
        this.setVisible(true);
    }
}
