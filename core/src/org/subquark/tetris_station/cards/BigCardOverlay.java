package org.subquark.tetris_station.cards;

import java.util.Objects;

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
    
    public BigCardOverlay(Group gameGroup, Table layout, final BuildOverlay buildOverlay) {
        this.layout = layout;
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
        
        Button playButton = new TextButton("Play", style);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button Changed");
                if (display.getCard().playAction != null) {
                    display.getCard().playAction.run(buildOverlay);
                }
                // TODO: remove card from hand
                removeCard();
            }
        });
        Button discardButton = new TextButton("Discard", style);

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
    
    public void setCard(Card c) {
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
