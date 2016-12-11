package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ApplyDamageFromFighters implements PostActivationAction {

    @Override
    public void act(GameState gs, Group lossScreen, Group winScreen, Label winLabel) {
        if (gs.hostileShips > GameConstants.FIGHTER_THRESHOLD) {
            gs.health = Math.max(0, gs.health - 1);
        }
    }
}
