package org.subquark.tetris_station.rooms;

import java.util.ArrayList;
import java.util.List;

import org.subquark.tetris_station.GameConstants;

import com.badlogic.gdx.scenes.scene2d.Group;

public class RoomGrid {
    private List<Room> rooms = new ArrayList<Room>();
    private Group actorGroup;
    private RoomGridDisplay display;
    private boolean[][] isOccupied;
    
    public RoomGrid(Group actorGroup) {
        this.actorGroup = actorGroup;
        this.display = new RoomGridDisplay();
        actorGroup.addActor(display);
        isOccupied = new boolean[GameConstants.GRID_HEIGHT_TILE][GameConstants.GRID_WIDTH_TILE];
    }
    
    public RoomGridDisplay getDisplay() {
        return display;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
        actorGroup.addActor(room);
        
        for (int x = 0; x < room.getTileWidth(); x++) {
            for (int y = 0; y < room.getTileHeight(); y++) {
                if (room.isPartOfRoom(x, y)) {
                    isOccupied[room.getTileY() + y][room.getTileX() + x] = true;
                }
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        return isOccupied[y][x];
    }
}
