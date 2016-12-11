package org.subquark.tetris_station.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class BigCardDisplay extends Actor {
    private Card card;
    
    public static final int CARD_WIDTH = 300;
    public static final int CARD_HEIGHT = 500;

    private Texture cardBackground;
    private BitmapFont font;
    private Table layout;
    
    private Label cardHeading;
    
    public BigCardDisplay() {
        Pixmap map = new Pixmap(CARD_WIDTH, CARD_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.LIGHT_GRAY);
        map.fill();
        cardBackground = new Texture(map);
        font = new BitmapFont();
        
        layout = new Table();
        layout.setHeight(CARD_HEIGHT);
        layout.setWidth(CARD_WIDTH);
        layout.setBackground(new TextureRegionDrawable(new TextureRegion(cardBackground)));

        LabelStyle style = new LabelStyle();
        style.font = font;
        
        this.cardHeading = new Label("", style);
        
        this.layout.add(cardHeading).row();
    }
    
    public Card getCard() {
        return this.card;
    }
    
    public void setCard(Card card) {
        this.card = card;
        this.cardHeading.setText(card.headline);
    }
    
    public void removeCard() {
        this.card = null;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (card == null) return;
        layout.setX(getX());
        layout.setY(getY());
        layout.draw(batch, parentAlpha);
    }
}
