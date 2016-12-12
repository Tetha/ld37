package org.subquark.tetris_station.activation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.rooms.Room;
import org.subquark.tetris_station.rooms.RoomGrid;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RoomActivationOverlay extends Actor {
    private int tileX;
    private int tileY;
    
    private Texture active;
    
    private RoomGrid rooms;
    private GameState gameState;
    
    private List<Room> activeRooms = new ArrayList<>();
    
    public RoomActivationOverlay(RoomGrid roomGrid, GameState gameState) {
        this.rooms = roomGrid;
        this.gameState = gameState;
        
        Pixmap activeSpot = new Pixmap(GameConstants.TILE_WIDTH_PX, GameConstants.TILE_HEIGHT_PX, Pixmap.Format.RGBA4444);
        activeSpot.setColor(1, 1, 0, 0.7f);
        activeSpot.fill();
        active = new Texture(activeSpot);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Room r : activeRooms) {
            for (int y = 0; y < r.getTileHeight(); y++) {
                for (int x = 0; x < r.getTileWidth(); x++) {
                    if (r.isPartOfRoom(x, y)) {
                        batch.draw(active,
                                   getX() + (r.getTileX() + x) * GameConstants.TILE_WIDTH_PX,
                                   getY() + (r.getTileY() + y) * GameConstants.TILE_HEIGHT_PX);
                    }
                }
            }
        }
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
            int currentRoomIndex = roomsToProcess.removeFirst();
            Room currentRoom = rooms.getRoom(currentRoomIndex);
            if (activeRooms.contains(currentRoom)) continue;
            
            activeRooms.add(currentRoom);
            
            if (currentRoom.getType().equals(Room.RoomType.EnergyTransmitter)) {
                roomsToProcess.addAll(rooms.getRoomsAdjacentTo(currentRoomIndex));
            }
        }
    }

    public void activateRooms() {
        for (Room r : activeRooms) {
            r.getActivation().activate(gameState);
        }
    }
}
