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
	    
	    stage.addActor(new Room());
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
