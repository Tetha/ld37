package org.subquark.tetris_station.rooms;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;

public class RoomGrid {
    private List<Room> rooms = new ArrayList<Room>();
    private Group actorGroup;
    private RoomGridDisplay display;
    
    public RoomGrid(Group actorGroup) {
        this.actorGroup = actorGroup;
        this.display = new RoomGridDisplay();
        actorGroup.addActor(display);
    }
    
    public RoomGridDisplay getDisplay() {
        return display;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
        actorGroup.addActor(room);
    }
}
