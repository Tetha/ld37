package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MoveCloserToSun implements PostActivationAction {

    @Override
    public void act(GameState gs, Group lossScreen, Group winScreen, Label winLabel) {
        gs.shipPosition = Math.min(GameConstants.MAX_SUN_DISTANCE, gs.shipPosition + 1);
    }
}
