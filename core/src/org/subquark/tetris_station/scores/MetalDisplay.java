package org.subquark.tetris_station.scores;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MetalDisplay extends Actor {
    private GameState gameState;
    
    private Texture emptySpot;
    private Texture metalSpot;
    
    private final int SLOT_WIDTH = 25;
    private final int SLOT_HEIGHT = 25;
    private final int COLUMNS = 5;
    
    public MetalDisplay(GameState gameState) {
        this.gameState = gameState;
        
        Pixmap map = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.LIGHT_GRAY);
        map.fill();
        emptySpot = new Texture(map);

        Pixmap metalMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGBA4444);
        metalMap.setColor(Color.DARK_GRAY);
        metalMap.fillCircle(12, 12, 10);
        metalSpot = new Texture(metalMap);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = getX();
        float y = getY();
        for (int slot = 0; slot < GameConstants.MAX_METAL; slot++) {
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
        for (int slot = 0; slot < gameState.availableMetal; slot++) {
            batch.draw(metalSpot, x, y);
            
            if ((slot + 1) % COLUMNS == 0) {
                y = getY();
                x += SLOT_WIDTH;
            } else {
                y += SLOT_HEIGHT;
            }
        }
    }
    
}
