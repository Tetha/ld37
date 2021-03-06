package org.subquark.tetris_station.activation.actions;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class WinWithMaxHyperPoints implements PostActivationAction {

    @Override
    public void act(GameState gs, Group lossScreen, Group winScreen, Label winLabel) {
        if (gs.hyperPointsEarned == GameConstants.MAX_HYPER_POINTS) {
            winScreen.setVisible(true);
            winScreen.setTouchable(Touchable.enabled);
            
            winLabel.setText(winLabel.getText() + "\n It took you " + gs.turns + "turns!");
        }
    }
}
