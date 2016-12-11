package org.subquark.tetris_station.rooms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.subquark.tetris_station.GameConstants;

import com.badlogic.gdx.scenes.scene2d.Group;

public class RoomGrid {
    private List<Room> rooms = new ArrayList<Room>();
    private Group actorGroup;
    private RoomGridDisplay display;
    private int[][] isOccupied;
    
    private Map<Integer, Set<Integer>> adjacencies = new HashMap<>();
    
    public static final int NO_ROOM = -1;
    
    public RoomGrid(Group actorGroup) {
        this.actorGroup = actorGroup;
        this.display = new RoomGridDisplay();
        actorGroup.addActor(display);
        isOccupied = new int[GameConstants.GRID_HEIGHT_TILE][GameConstants.GRID_WIDTH_TILE];
        
        for (int y = 0; y < GameConstants.GRID_HEIGHT_TILE; y++) {
            for (int x = 0; x < GameConstants.GRID_WIDTH_TILE; x++) {
                isOccupied[y][x] = NO_ROOM;
            }
        }
    }
    
    public RoomGridDisplay getDisplay() {
        return display;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
        actorGroup.addActorAfter(display, room);
        int roomIndex = rooms.size() - 1;
        
        for (int x = 0; x < room.getTileWidth(); x++) {
            for (int y = 0; y < room.getTileHeight(); y++) {
                if (room.isPartOfRoom(x, y)) {
                    isOccupied[room.getTileY() + y][room.getTileX() + x] = roomIndex;
                }
            }
        }
        
        recalculateAdjacencies();
    }
    
    private void recalculateAdjacencies() {
        adjacencies.clear();
        for (int i = 0; i < rooms.size(); i++) {
            adjacencies.put(i, new HashSet<Integer>());
        }
        for (int y = 0; y < GameConstants.GRID_HEIGHT_TILE; y++) {
            for (int x = 0; x + 1 < GameConstants.GRID_WIDTH_TILE; x++) {
                int currentRoom = isOccupied[y][x];
                int nextRoom = isOccupied[y][x+1];
                if (currentRoom == nextRoom) continue;
                if (currentRoom == NO_ROOM || nextRoom == NO_ROOM) continue;
                
                adjacencies.get(currentRoom).add(nextRoom);
                adjacencies.get(nextRoom).add(currentRoom);
            }
        }
        
        for (int y = 0; y + 1 < GameConstants.GRID_HEIGHT_TILE; y++) {
            for (int x = 0; x < GameConstants.GRID_WIDTH_TILE; x++) {
                int currentRoom = isOccupied[y][x];
                int nextRoom = isOccupied[y+1][x];
                if (currentRoom == nextRoom) continue;
                if (currentRoom == NO_ROOM || nextRoom == NO_ROOM) continue;
                
                adjacencies.get(currentRoom).add(nextRoom);
                adjacencies.get(nextRoom).add(currentRoom);
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        return isOccupied[y][x] != NO_ROOM;
    }

    public int getRoomAt(int tileX, int tileY) {
        return isOccupied[tileY][tileX];
    }

    public Room getRoom(int currentRoomIndex) {
        return rooms.get(currentRoomIndex);
    }

    public Collection<? extends Integer> getRoomsAdjacentTo(int roomIndex) {
        return adjacencies.get(roomIndex);
    }
}
