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
    private Label cost;
    private Label onPlayText;
    private Label onDiscardText;
    
    private Label cannotPlayText;
    
    public BigCardDisplay() {
        Pixmap map = new Pixmap(CARD_WIDTH, CARD_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.GRAY);
        map.fill();
        cardBackground = new Texture(map);
        font = new BitmapFont();
        
        layout = new Table();
        layout.setDebug(true);
        layout.setX(getX());
        layout.setY(getY());
        layout.setHeight(CARD_HEIGHT);
        layout.setWidth(CARD_WIDTH);
        layout.setBackground(new TextureRegionDrawable(new TextureRegion(cardBackground)));

        LabelStyle style = new LabelStyle();
        style.font = font;
        Pixmap textMap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        textMap.setColor(Color.DARK_GRAY);
        textMap.fill();
        style.background = new TextureRegionDrawable(new TextureRegion(new Texture(textMap)));

        this.cardHeading = new Label("", style);
        this.cost = new Label("", style);
        this.onPlayText = new Label("", style);
        this.onDiscardText = new Label("", style);
        this.cannotPlayText = new Label("", style);
        
        this.layout.add(cardHeading);
        this.layout.add(cost).width(100);
        this.layout.row();
        
        this.layout.add(cannotPlayText).padTop(30).colspan(2).row();
        
        this.layout.add(new Label("On play: ", style)).width(CARD_WIDTH - 40).padLeft(0).padTop(30).colspan(2).row();
        this.layout.add(onPlayText).width(CARD_WIDTH - 40).padLeft(0).colspan(2).row();

        this.layout.add(new Label("On Discard: ", style)).width(CARD_WIDTH - 40).padLeft(0).padTop(30).colspan(2).row();
        this.layout.add(onDiscardText).width(CARD_WIDTH - 40).padLeft(0).colspan(2).row();
    }
    
    public Card getCard() {
        return this.card;
    }
    
    public void setCard(Card card) {
        this.card = card;
        this.cardHeading.setText(card.headline);
        
        if (this.card.cost > 0) {
            this.cost.setText(this.card.cost + "Metal");
            this.cost.setVisible(true);
        } else {
            this.cost.setText("");;
            this.cost.setVisible(false);
        }
        
        if (card.playDescription == null) {
            onPlayText.setText("None");
        } else {
            onPlayText.setText(card.playDescription);
        }
        
        if (card.discardDescription == null) {
            onDiscardText.setText("None");
        } else {
            onDiscardText.setText(card.discardDescription);
        }
        
        if (card.canPlay) {
            cannotPlayText.setText("");
            cannotPlayText.setVisible(false);
        } else {
            cannotPlayText.setText("Cannot Play: " + card.cannotPlayReason);
            cannotPlayText.setVisible(true);
        }

        this.layout.invalidate();
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
