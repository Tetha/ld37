package org.subquark.tetris_station.rooms;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.build_overlay.BuildOverlay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RoomGridDisplay extends Actor {
    private Texture evenTile;
    private Texture oddTile;
    
    public RoomGridDisplay() {
        Pixmap map = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGB565);
        map.setColor(Color.GRAY);
        map.fill();
        evenTile = new Texture(map);
        
        Pixmap map2 = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGB565);
        map2.setColor(Color.DARK_GRAY);
        map2.fill();
        oddTile = new Texture(map2);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int y = 0; y < GameConstants.GRID_HEIGHT_TILE; y++) {
            boolean rowOdd = y % 2 == 0;
            for (int x = 0; x < GameConstants.GRID_WIDTH_TILE; x++) {
                boolean colOdd = x % 2 == 0;
                if (rowOdd != colOdd) {
                    batch.draw(oddTile, x * GameConstants.TILE_WIDTH_PX, y * GameConstants.TILE_HEIGHT_PX);
                } else {
                    batch.draw(evenTile, x * GameConstants.TILE_WIDTH_PX, y * GameConstants.TILE_WIDTH_PX);
                }
            }
        }
    }

    public boolean areStageCoordsOnGrid(float x, float y) {
        return 0 <= x && x <= GameConstants.GRID_WIDTH_TILE * GameConstants.TILE_WIDTH_PX
                && 0 <= y && y <= GameConstants.GRID_HEIGHT_TILE * GameConstants.TILE_HEIGHT_PX;
    }
    
    public int xPixelToGridTile(float x) {
        return (int)(x / GameConstants.TILE_WIDTH_PX);
    }
    
    public int yPixelToGridTile(float y) {
        return (int)(y / GameConstants.TILE_HEIGHT_PX);
    }
    
}
