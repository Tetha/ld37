package org.subquark.tetris_station.cards;

import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class SmallCardDisplay extends Actor {
    private Texture cardBackground;
    private GameState gameState;
    private int cardIndex;
    
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = 50;
    
    private BitmapFont font;
    private Label text;
    
    public SmallCardDisplay(GameState gameState, int cardIndex) {
        this.gameState = gameState;
        this.cardIndex = cardIndex;
        
        Pixmap map = new Pixmap(CARD_WIDTH, CARD_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.LIGHT_GRAY);
        map.fill();
        cardBackground = new Texture(map);
        font = new BitmapFont();
        
        LabelStyle style = new LabelStyle();
        style.font = new BitmapFont();
        text = new Label("", style);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cardBackground, getX(), getY());
        if (0 <= cardIndex && cardIndex < gameState.cards.size()) {
            text.setText(gameState.cards.get(cardIndex).headline);
            text.setX(getX()+10);
            text.setY(getY()+CARD_HEIGHT/2);
            text.draw(batch, parentAlpha);
            //font.draw(batch, gameState.cards.get(cardIndex).headline, getX() + 10, getY() + CARD_HEIGHT/2);
        }
    }
}
