package org.subquark.tetris_station.scores;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HyperPointDisplay extends Actor {
    private GameState gameState;
    
    private Texture emptySpot;
    private Texture hyperPoint;
    
    private final int SLOT_WIDTH = 25;
    private final int SLOT_HEIGHT = 25;
    private final int COLUMNS = 2;
    
    public HyperPointDisplay(GameState gameState) {
        this.gameState = gameState;
        
        Pixmap map = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.LIGHT_GRAY);
        map.fill();
        emptySpot = new Texture(map);

        Pixmap hyperMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGBA4444);
        hyperMap.setColor(Color.CYAN);
        hyperMap.fillTriangle(0, 12, 12, 25, 25, 12);
        hyperMap.fillTriangle(0, 12, 12, 0, 25, 12);
        hyperPoint = new Texture(hyperMap);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = getX();
        float y = getY();
        for (int slot = 0; slot < GameConstants.MAX_HYPER_POINTS; slot++) {
            batch.draw(emptySpot, x, y);
            
            if ((slot + 1) % COLUMNS == 0) {
                y = getY();
                x += SLOT_WIDTH;
            } else {
                y += SLOT_HEIGHT;
            }
        }
        
        x = getX();
        y = getY();
        for (int slot = 0; slot < gameState.hyperPointsEarned; slot++) {
            batch.draw(hyperPoint, x, y);
            
            if ((slot + 1) % COLUMNS == 0) {
                y = getY();
                x += SLOT_WIDTH;
            } else {
                y += SLOT_HEIGHT;
            }
        }
    }
}
