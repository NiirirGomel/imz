package com.glove.imz.objects.giu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.*;

public final class GameConsole
{
	private static BitmapFont font;
	private static TextActor text;
	
	private GameConsole () {
		throw new AssertionError();
	}
	public static void inicialize(Viewport viewport) {
		// inicialize font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/tahoma.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 48;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		text = new TextActor(viewport);
		text.setPosition(-viewport.getWorldWidth()/2,viewport.getWorldHeight()/2);
		text.setText("");
		//addActor(text);
	}
	
	public static void output(Group group) {
		group.addActor(text);
	}
	
	// пока будет эта херня
	public static void setText(CharSequence str) {
		text.setText(str);
	}
}
