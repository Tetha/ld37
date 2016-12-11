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
    
    private RoomType type;
    
    private int width;
    private int height;
    
    private boolean[][] partOfRoom;
    
    private Texture roomSource;
    
    public static Room createEnergyTransmitter1() {        
        int width = 4;
        int height = 2;
        boolean[][] partOfRoom = new boolean[][] {
                {true, true, true, true},
                {true, true, true, true},
        };
        
        return new Room(RoomType.EnergyTransmitter, width, height, partOfRoom, Color.BLUE);
    }    
    public static Room createEnergyTransmitter2() {        
        int width = 2;
        int height = 4;
        boolean[][] partOfRoom = new boolean[][] {
                {true, true},
                {true, true},
                {true, true},
                {true, true}
        };
        
        return new Room(RoomType.EnergyTransmitter, width, height, partOfRoom, Color.CYAN);
    }    
    public static Room createMetalGenerator() {
        int width = 4;
        int height = 4;
        boolean[][] partOfRoom = new boolean[][] {
                {false, true, true, false},
                {true,  true, true,  true},
                {true,  true, true,  true},
                {false, true, true, false}
        };
        
        return new Room(RoomType.MetalGenerator, width, height, partOfRoom, Color.MAGENTA);        
    }    
    public static Room createDefenseGun() {
        int width = 4;
        int height = 4;
        boolean[][] partOfRoom = new boolean[][] {
                {true, true, false, false},
                {true, true, true,  true},
                {true, true, true,  true},
                {true, true, false, false}
        };
        
        return new Room(RoomType.DefenseGun, width, height, partOfRoom, Color.RED);        
    }    
    public static Room createEngine() {
        int width = 4;
        int height = 4;
        boolean[][] partOfRoom = new boolean[][] {
                {true, true, true, false},
                {false, false, true,  true},
                {false, false, true,  true},
                {true, true, true, false}
        };
        
        return new Room(RoomType.Engine, width, height, partOfRoom, Color.ORANGE);        
    }
    public static Room createWarpCore() {
        int width = 4;
        int height = 3;
        boolean[][] partOfRoom = new boolean[][] {
                {true, false, false, true},                
                {true, true, true,  true},
                {false, true, true,  false},
        };
        
        return new Room(RoomType.WarpCore, width, height, partOfRoom, Color.GREEN);        
    }
    
    public enum RoomType {
        EnergyTransmitter,
        MetalGenerator,
        DefenseGun,
        Engine,
        WarpCore
    }
    
    private Room(RoomType type, int width, int height, boolean[][] partOfRoom, Color color) {
        this.type = type;
        
        Pixmap map = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGB565);
        map.setColor(color);
        map.fill();
        roomSource = new Texture(map);
        
        this.width = width;
        this.height = height;
        this.partOfRoom = partOfRoom;
    }
    
    public int getTileHeight() {
        return this.height;
    }
    
    public int getTileWidth() {
        return this.width;
    }
    
    public void setTileX(int newTileX) {
        this.tileX = newTileX;
    }
    
    public int getTileX() {
        return this.tileX;
    }
    
    public void setTileY(int newTileY) {
        this.tileY = newTileY;
    }
    
    public int getTileY() {
        return this.tileY;
    }

    public RoomType getType() {
        return this.type;
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

    public boolean isPartOfRoom(int x, int y) {
        return partOfRoom[y][x];
    }
    
    @Override
    public String toString() {
        return String.format("Room[tileX=%d, tileY=%d, type=%s]", tileX, tileY, type);
    }
}
