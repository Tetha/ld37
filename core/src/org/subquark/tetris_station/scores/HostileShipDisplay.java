package org.subquark.tetris_station.scores;

import org.subquark.tetris_station.GameConstants;
import org.subquark.tetris_station.GameState;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HostileShipDisplay extends Actor {
    private GameState gameState;
    
    private Texture emptySpot;
    private Texture dangerousSpot;
    private Texture ship;
    
    private final int SLOT_WIDTH = 25;
    private final int SLOT_HEIGHT = 25;
    
    public HostileShipDisplay(GameState gameState) {
        this.gameState = gameState;
        
        Pixmap map = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        map.setColor(Color.GRAY);
        map.fill();
        emptySpot = new Texture(map);
        
        Pixmap mapDanger = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGB565);
        mapDanger.setColor(Color.ORANGE);
        mapDanger.fill();
        dangerousSpot = new Texture(mapDanger);

        Pixmap shipMap = new Pixmap(SLOT_WIDTH, SLOT_HEIGHT, Pixmap.Format.RGBA4444);
        shipMap.setColor(Color.RED);
        shipMap.fillTriangle(0, 25, 12, 0, 25, 25);
        ship = new Texture(shipMap);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = getX();
        float y = getY();
        for (int slot = 0; slot < GameConstants.MAX_FIGHTERS; slot++) {
            if (slot < GameConstants.FIGHTER_THRESHOLD) {
                batch.draw(emptySpot, x, y);
            } else {
                batch.draw(dangerousSpot, x, y);
            }
            
            if (slot % 2 == 0) {
                y = SLOT_HEIGHT;
            } else {
                y = getY();
                x += SLOT_WIDTH;
            }
        }
        
        x = getX();
        y = getY();
        for (int slot = 0; slot < gameState.hostileShips; slot++) {
            batch.draw(ship, x, y);
            if (slot % 2 == 0) {
                y = SLOT_HEIGHT;
            } else {
                y = getY();
                x += SLOT_WIDTH;
            }
        }
    }
}
