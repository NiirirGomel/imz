package com.glove.imz.objects.giu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;

public class BtnActor extends Group
{
	private TextActor text;
	public Texture texture;
	public Rectangle rect;
	
	public BtnActor(Viewport viewport, BitmapFont font){
		text = new TextActor(viewport, font);
		text.setAlign("center");
		addActor(text);
	}
	
	public void setText(CharSequence str) {
		text.setText(str);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(texture != null){
			batch.draw(texture,
				getX()+rect.x,
				getY()+rect.y,
				rect.width,
				rect.height);
		}
		super.draw(batch, parentAlpha);
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if(rect != null && rect.contains(x,y)){
			return this;
		} else {
			return super.hit(x, y, touchable);
		}
	}
	
}
