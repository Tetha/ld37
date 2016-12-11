package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.scenes.scene2d.Group;

public class ApplyDamageFromFighters implements PostActivationAction {

    @Override
    public void act(GameState gs, Group lossScreen, Group winScreen) {
        if (gs.hostileShips > GameConstants.FIGHTER_THRESHOLD) {
            gs.health = Math.max(0, gs.health - 1);
        }
    }
}
