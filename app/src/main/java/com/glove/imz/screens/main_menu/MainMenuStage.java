package com.glove.imz.screens.main_menu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.*;
import com.glove.imz.objects.giu.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.*;

public class MainMenuStage extends Stage
{
	private Vector3 touchPoint = new Vector3();
	public GameConsole console;
	
	public BitmapFont guiFont;
	public BtnActor btnStart;
	public BtnActor btnResume;
	
	public MainMenuStage() {
		super(new ExtendViewport(10,10));
		GameConsole.inicialize(getViewport());
		getCamera().position.set(0,0,0);
		
		Gdx.input.setInputProcessor(this);
		
		// inicialize font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/tahoma.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 128;
		guiFont = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		btnStart = new BtnActor(getViewport(), guiFont);
		btnStart.setPosition(0f,2f);
		btnStart.texture = new Texture(Gdx.files.internal("drawable/main_menu/btnfon.jpg"));
		btnStart.rect = new Rectangle(-2f,-1f,4f,2f);
		btnStart.setText("Start");
		addActor(btnStart);
		
		btnResume = new BtnActor(getViewport(), guiFont);
		btnResume.setPosition(0f,-1f);
		btnResume.texture = new Texture(Gdx.files.internal("drawable/main_menu/btnfon.jpg"));
		btnResume.rect = new Rectangle(-2.5f,-1f,5f,2f);
		btnResume.setText("Resume");
		addActor(btnResume);
		
		GameConsole.output(getRoot());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw() {
		super.draw();
	}

	@Override
	public Actor hit(float stageX, float stageY, boolean touchable)
	{
		Actor target = super.hit(stageX, stageY, touchable);
		if(target == btnStart){
			console.setText("hit btnStart");
		}
		return target;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// расшифровываем координаты касания и записываем их в touchPoint
		getCamera().unproject(touchPoint.set(screenX,screenY,0));
		console.setText("touchPoint"+touchPoint.toString());
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		getCamera().unproject(touchPoint.set(screenX,screenY,0));
		console.setText("touchPoint"+touchPoint.toString());
		
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		getCamera().unproject(touchPoint.set(screenX,screenY,0));
		console.setText("touchPoint"+touchPoint.toString());
		
		return super.touchUp(screenX, screenY, pointer, button);
	}
	
}
