package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public interface PostActivationAction {
    void act(GameState gs, Group lossScreen, Group winScreen, Label winLabel);
}
