package org.subquark.tetris_station;

import org.subquark.tetris_station.rooms.Room;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TetrisSpaceStation extends ApplicationAdapter {
	private Stage stage;
	
	@Override
	public void create () {
	    stage = new Stage(new StretchViewport(800, 600));
	    
	    Room transmitter1_1 =Room.createEnergyTransmitter1();
	    transmitter1_1.setTileX(4);
	    transmitter1_1.setTileY(0);
	    stage.addActor(transmitter1_1);

	    Room transmitter1_2 = Room.createEnergyTransmitter1();
	    transmitter1_2.setTileX(5);
	    transmitter1_2.setTileY(6);
	    stage.addActor(transmitter1_2);
	        
	    Room transmitter2 = Room.createEnergyTransmitter2();
	    transmitter2.setTileX(5);
	    transmitter2.setTileY(2);
	    stage.addActor(transmitter2);
	    
        Room matterGenerator = Room.createMetalGenerator();
        matterGenerator.setTileX(8);
        matterGenerator.setTileY(0);
        stage.addActor(matterGenerator);
        
        Room defenseGun = Room.createDefenseGun();
        defenseGun.setTileX(9);
        defenseGun.setTileY(6);
        stage.addActor(defenseGun);
        
        Room engine = Room.createEngine();
        engine.setTileX(1);
        engine.setTileY(5);
        stage.addActor(engine);
	    
        Room warpCore = Room.createWarpCore();
        warpCore.setTileX(4);
        warpCore.setTileY(8);
        stage.addActor(warpCore);
        
	    Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
	    stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void render () {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
	}
}
