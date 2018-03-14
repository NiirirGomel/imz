package com.glove.imz.objects.giu;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;

public class TextActor extends Group
{
	private TextLine textLine;

	public TextActor(Viewport viewport) {
		textLine = new TextLine();
		scaleForGame(viewport);
		addActor(textLine);
	}
	
	public TextActor(Viewport viewport, BitmapFont bitmapFont) {
		textLine = new TextLine(bitmapFont);
		scaleForGame(viewport);
		addActor(textLine);
	}
	
	private void scaleForGame(Viewport viewport) {
		setScale(viewport.getWorldHeight()/viewport.getScreenHeight());
	}
	
	public void setText(CharSequence str) {
		textLine.text = str;
	}
	
	public void setAlign(String mode) {
		textLine.setAlign(mode);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
	}
	
	public class TextLine extends Actor
	{
		protected BitmapFont font;
		protected CharSequence text;
		public GlyphLayout layout;
		private String align = "none";
		
		public TextLine(){
			// inicialize font
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/tahoma.ttf"));
			FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
			parameter.size = 48;
			font = generator.generateFont(parameter); // font size 12 pixels
			generator.dispose(); // don't forget to dispose to avoid memory leaks!
		}
		
		public TextLine(BitmapFont bitmapFont) {
			font = bitmapFont;
		}
		
		public void setAlign(String mode) {
			align = mode;
		}

		@Override
		public void act(float delta) {
			super.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			layout = font.draw(batch,text,getX(),getY());
			if(align == "center"){
				setPosition(-layout.width/2, layout.height/2);
			}
		}
	}
}
