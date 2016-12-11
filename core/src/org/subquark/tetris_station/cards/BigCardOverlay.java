package org.subquark.tetris_station.cards;

import java.util.ArrayList;
import java.util.List;

import org.subquark.tetris_station.GameState;
import org.subquark.tetris_station.build_overlay.BuildOverlay;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BigCardOverlay {
    private BigCardDisplay display;
    private Table layout;
    private Group gameGroup;
    private int cardIndex;
    private GameState gameState;
    
    private Button playButton;
    
    private List<Runnable> onCardPlayCallbacks = new ArrayList<Runnable>();
    private List<Runnable> onCardDiscardedCallbacks = new ArrayList<Runnable>();
    
    public BigCardOverlay(Group gameGroup, Table layout, final BuildOverlay buildOverlay, final GameState gameState) {
        this.layout = layout;
        this.gameState = gameState;
        
        layout.setFillParent(true);
        display = new BigCardDisplay();
        this.gameGroup = gameGroup;
        
        TextButtonStyle style = new TextButtonStyle();
        style.font = new BitmapFont();
        
        Button cancelButton = new TextButton("Cancel", style);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button Changed");
                removeCard();
            }
        });
        
        playButton = new TextButton("Play", style);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (display.getCard().playAction != null) {
                    display.getCard().playAction.run(gameState, buildOverlay);
                    
                }
                gameState.cards.remove(cardIndex);
                
                for (Runnable r : onCardPlayCallbacks) {
                    r.run();
                }
                removeCard();
            }
        });
        Button discardButton = new TextButton("Discard", style);
        discardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (display.getCard().discardAction != null) {
                    display.getCard().discardAction.run(gameState, buildOverlay);
                    
                }
                gameState.cards.remove(cardIndex);
                
                for (Runnable r : onCardDiscardedCallbacks) {
                    r.run();
                }
                removeCard();
            }
        });

        Table buttonLayout = new Table();
        
        this.layout.add(display).width(BigCardDisplay.CARD_WIDTH).height(BigCardDisplay.CARD_HEIGHT);
        this.layout.add(buttonLayout);
        this.layout.row();
        
        buttonLayout.add(cancelButton).expand().top().row();
        buttonLayout.add(playButton).row();
        buttonLayout.add(discardButton).row();
        
        this.layout.setTouchable(Touchable.disabled);
        this.layout.setVisible(false);
    }
    
    public void addCardPlayedCallback(Runnable callback) {
        this.onCardPlayCallbacks.add(callback);
    }
    public void addCardDiscardedCallback(Runnable callback) {
        this.onCardDiscardedCallbacks.add(callback);
    }

    
    public void setCard(int index, Card c) {
        this.cardIndex = index;
        c.playCondition.setPlayable(gameState, c);
        this.playButton.setVisible(c.canPlay);

        display.setCard(c);
        
        
        this.layout.setTouchable(Touchable.enabled);
        gameGroup.setTouchable(Touchable.disabled);
        this.layout.setVisible(true);
    }
    
    void removeCard() {
        display.removeCard();

        this.layout.setTouchable(Touchable.disabled);
        gameGroup.setTouchable(Touchable.enabled);
        this.layout.setVisible(false);
    }
}
