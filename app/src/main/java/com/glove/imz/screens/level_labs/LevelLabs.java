package com.glove.imz.screens.level_labs;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

public class LevelLabs implements Screen
{
	private LevelLabsStage levelLabsStage;

	public LevelLabs() {
		levelLabsStage = new LevelLabsStage();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Update the stage
		levelLabsStage.draw();
		levelLabsStage.act(delta);
	}

	@Override
	public void resize(int width, int height) {
		levelLabsStage.getViewport().update(width,height,false);
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
