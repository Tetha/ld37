package org.subquark.tetris_station.build_overlay;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.rooms.Room;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RoomOverlay extends Actor {
    private Room room;
    
    private Texture goodSpot;
    private Texture badSpot;
    
    private boolean isValidPosition;
    private int tileX;
    private int tileY;
    
    public RoomOverlay() {
        
        Pixmap goodMap = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGBA4444);
        goodMap.setColor(0, 1, 0, 0.8f);
        goodMap.fill();
        goodSpot = new Texture(goodMap);

        Pixmap badMap = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGBA4444);
        badMap.setColor(1, 0, 0, 0.8f);
        badMap.fill();
        badSpot = new Texture(badMap);
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void setValidPosition(boolean valid) {
        this.isValidPosition = valid;
    }
    public void setTileX(int tileX) {
        this.tileX = tileX;
    }
    
    public void setTileY(int tileY) {
        this.tileY = tileY;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (room == null) return;
        
        Texture source = isValidPosition ? goodSpot : badSpot;
        for (int y = 0; y < room.getTileHeight(); y++) {
            for (int x = 0; x < room.getTileWidth(); x++) {
                if (room.isPartOfRoom(x, y)) {
                    batch.draw(source,
                               getX() + (tileX + x) * GameConstants.TILE_WIDTH_PX,
                               getY() + (tileY + y) * GameConstants.TILE_HEIGHT_PX);
                }
            }
        }
    }

    public void checkValidity() {
        System.out.println("tileY: " + tileY);
        if (!(0 <= tileX && tileX + room.getTileWidth() <= GameConstants.GRID_WIDTH_TILE)) {
            setValidPosition(false);
            return;
        }
        if (!(tileY <= GameConstants.GRID_HEIGHT_TILE && room.getTileHeight() <= tileY)) {
            setValidPosition(false);
            return;
        }
        
        setValidPosition(true);
    }
}
