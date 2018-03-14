package com.glove.imz.screens.main_menu;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

public class MainMenu implements Screen
{
	private MainMenuStage mainMenuStage;

	public MainMenu() {
		mainMenuStage = new MainMenuStage();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Update the stage
		mainMenuStage.draw();
		mainMenuStage.act(delta);
	}

	@Override
	public void resize(int width, int height) {
		mainMenuStage.getViewport().update(width,height,false);
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
