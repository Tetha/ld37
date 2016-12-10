package org.subquark.tetris_station.rooms;

import org.subquark.tetris_station.GameConstants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Room extends Actor {
    // one corner of the room
    private int tileX;
    private int tileY;
    
    private int width;
    private int height;
    
    private boolean[][] partOfRoom;
    
    private Texture roomSource;
    
    public Room() {
        Pixmap map = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGB565);
        map.setColor(Color.RED);
        map.fill();
        roomSource = new Texture(map);
        
        width = 5;
        height = 5;
        partOfRoom = new boolean[][] {
                {false, true, true, false, false},
                {false, true, true, false, false},
                {false, false, true, true, false},
                {false, false, true, true, false},
                {false, false, true, true, false}
        };
    }
    @Override
    public void draw (Batch batch, float parentAlpha) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (partOfRoom[y][x]) {
                    batch.draw(roomSource,
                               (tileX + x) * GameConstants.TILE_WIDTH_PX,
                               (tileY + y) * GameConstants.TILE_HEIGHT_PX);
                }
            }
        }
    }
}
