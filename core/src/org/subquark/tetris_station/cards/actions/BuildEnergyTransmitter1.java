package org.subquark.tetris_station.cards.actions;

import org.subquark.tetris_station.build_overlay.BuildOverlay;
import org.subquark.tetris_station.rooms.Room;

public class BuildEnergyTransmitter1 implements CardAction {
    @Override
    public void run(BuildOverlay buildOverlay) {
        buildOverlay.enableWithRoom(Room.createEnergyTransmitter1());
    }
}
