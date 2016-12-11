package org.subquark.tetris_station.activation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.subquark.tetris_station.rooms.Room;
import org.subquark.tetris_station.rooms.RoomGrid;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class RoomActivationOverlay extends Actor {
    private int tileX;
    private int tileY;
    
    private RoomGrid rooms;
    
    private List<Room> activeRooms = new ArrayList<>();
    
    public RoomActivationOverlay(RoomGrid roomGrid) {
        this.rooms = roomGrid;
    }
    
    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public void updateActivations() {
        activeRooms.clear();
        int startingRoomIndex = rooms.getRoomAt(tileX, tileY);
        if (startingRoomIndex == RoomGrid.NO_ROOM) {
            return;
        }
        
        LinkedList<Integer> roomsToProcess = new LinkedList<Integer>();
        roomsToProcess.add(startingRoomIndex);
        
        while (!roomsToProcess.isEmpty()) {
            int currentRoomIndex = roomsToProcess.pop();
            Room currentRoom = rooms.getRoom(currentRoomIndex);
            if (activeRooms.contains(currentRoom)) continue;
            
            activeRooms.add(currentRoom);
            
            if (currentRoom.getType().equals(Room.RoomType.EnergyTransmitter)) {
                roomsToProcess.addAll(rooms.getRoomsAdjacentTo(currentRoomIndex));
            }
        }
    }
}
