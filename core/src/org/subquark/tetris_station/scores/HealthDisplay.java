package org.subquark.tetris_station.scores;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthDisplay extends Actor {
private GameState gameState;
    
    private Texture fineSpot;
    private Texture dangerousSpot;
    private Texture lethalSpot;
    private Texture healthMarker;
    
    private final int SLOT_WIDTH = 25;
    private final int SLOT_HEIGHT = 25;
    
    public HealthDisplay(GameState gameState) {
        this.gameState = gameState;
        
        Pixmap fineMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        fineMap.setColor(0, 0.8f, 0, 1f);
        fineMap.fill();
        fineSpot = new Texture(fineMap);
        
        Pixmap mapDanger = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        mapDanger.setColor(Color.YELLOW);
        mapDanger.fill();
        dangerousSpot = new Texture(mapDanger);
        
        Pixmap lethalMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGBA4444);
        lethalMap.setColor(Color.RED);
        lethalMap.fill();
        lethalSpot = new Texture(lethalMap);
        
        Pixmap healthSpot = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGBA4444);
        healthSpot.setColor(Color.GREEN);
        healthSpot.fillRectangle(9, 0, 7, 25);
        healthSpot.fillRectangle(0, 9, 25, 7);
        healthMarker = new Texture(healthSpot);
    }
    
    @Override
    public float getWidth() {
        return GameConstants.MAX_HEALTH * SLOT_WIDTH;
    }
    
    @Override
    public float getHeight() {
        return SLOT_HEIGHT;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = getX();
        float y = getY();
        for (int slot = 0; slot <= GameConstants.MAX_HEALTH; slot++) {
            Texture field;
            if (slot < 2) {
                field = lethalSpot;
            } else if (slot < 5) {
                field = dangerousSpot;
            } else {
                field = fineSpot;
            }
            batch.draw(field, x, y);
            x += SLOT_WIDTH;
        }

        batch.draw(healthMarker, getX() + SLOT_WIDTH * gameState.health, getY());
    }
}
