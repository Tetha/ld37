package org.subquark.tetris_station.scores;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SolarDistanceDisplay extends Actor {
private GameState gameState;
    
    private Texture emptySpot;
    private Texture dangerousSpot;
    private Texture shipPosition;
    private Texture sun;
    
    private final int SLOT_WIDTH = 25;
    private final int SLOT_HEIGHT = 25;
    
    public SolarDistanceDisplay(GameState gameState) {
        this.gameState = gameState;
        
        Pixmap map = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.LIGHT_GRAY);
        map.fill();
        emptySpot = new Texture(map);
        
        Pixmap mapDanger = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        mapDanger.setColor(Color.ORANGE);
        mapDanger.fill();
        dangerousSpot = new Texture(mapDanger);
        
        Pixmap shipMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGBA4444);
        shipMap.setColor(Color.GRAY);
        shipMap.fillCircle(12, 12, 12);
        shipPosition = new Texture(shipMap);
        
        Pixmap sunMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        sunMap.setColor(Color.YELLOW);
        sunMap.fillCircle(12, 12, 12);
        sun = new Texture(sunMap);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = getX();
        float y = getY();
        for (int slot = 0; slot <= GameConstants.MAX_SUN_DISTANCE; slot++) {
            if (slot < GameConstants.SUN_DISTANCE_THRESHOLD) {
                batch.draw(emptySpot, x, y);
            } else {
                batch.draw(dangerousSpot, x, y);
            }
            x += SLOT_WIDTH;
        }

        batch.draw(sun, getX() + SLOT_WIDTH * (GameConstants.MAX_SUN_DISTANCE+1), getY());
        batch.draw(shipPosition, getX() + SLOT_WIDTH * gameState.shipPosition, getY());
    }
}
