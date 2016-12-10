package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class HandDisplay {
    private final GameState gameState;
    private final Group actorGroup;
    
    public HandDisplay(final GameState gameState, final Group actorGroup, final BigCardOverlay bigDisplay) {
        this.gameState = gameState;
        this.actorGroup = actorGroup;
        
        for (int i = 0; i < GameConstants.MAX_MAX_CARDS; i++) {
            final int cardIndex = i;
            SmallCardDisplay display = new SmallCardDisplay(gameState, i);
            display.setX((SmallCardDisplay.CARD_WIDTH + 10) * i );
            display.setBounds((SmallCardDisplay.CARD_WIDTH + 10) * i, 0, SmallCardDisplay.CARD_WIDTH, SmallCardDisplay.CARD_HEIGHT);
            display.setDebug(true);
            display.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Card #" + cardIndex + " clicked!");
                    if (0 <= cardIndex && cardIndex < gameState.cards.size()) {
                        bigDisplay.setCard(gameState.cards.get(cardIndex));
                    }
                    return true;
                }
            });
            actorGroup.addActor(display);
        }
    }
}
