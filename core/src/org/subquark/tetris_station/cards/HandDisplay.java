package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.scenes.scene2d.Group;

public class HandDisplay {
    private GameState gameState;
    private Group actorGroup;
    
    public HandDisplay(GameState gameState, Group actorGroup) {
        this.gameState = gameState;
        this.actorGroup = actorGroup;
        
        for (int i = 0; i < GameConstants.MAX_MAX_CARDS; i++) {
            SmallCardDisplay display = new SmallCardDisplay(gameState, i);
            display.setX((SmallCardDisplay.CARD_WIDTH + 10) * i );
            actorGroup.addActor(display);
        }
    }
}
