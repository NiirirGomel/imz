package com.glove.imz.objects.giu;
import com.glove.imz.objects.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.glove.imz.objects.layers.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.glove.imz.utils.animation.*;
import com.badlogic.gdx.utils.viewport.*;

public class BtnObject extends ObjectActor
{
	private Viewport viewport;

	public BtnObject(GuiLayer gui_layer, Vector2 position, Vector2 size, TextureRegion texture_region, InputListener listener) {
		setBounds(position.x,position.y,size.x,size.y);
		this.texture_region = texture_region;
		viewport = gui_layer.getStage().getViewport();
		if(listener != null){
			addListener(listener);
		}
		gui_layer.addActor(this);
	}

	public BtnObject(GuiLayer gui_layer, Vector2 position, Vector2 size, ObjectAnimation animation, InputListener listener) {
		setBounds(position.x,position.y,size.x,size.y);
		this.animation = animation;
		viewport = gui_layer.getStage().getViewport();
		if(listener != null){
			addListener(listener);
		}
		gui_layer.addActor(this);
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if(x > -getWidth()/2
		&& x < getWidth()/2
		&& y > -getHeight()/2
		&& y < getHeight()/2){
				return this;
		}
		
		return super.hit(x, y, touchable);
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		if(animation != null){
			animation.draw(batch, getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight(), getRotation());
		} else if(texture_region != null){
			batch.draw(
				texture_region.getTexture(),
				getX()-getWidth()/2, getY()-getHeight()/2,
				getWidth()/2, getHeight()/2,
				getWidth(), getHeight(),
				1f, 1f,
				getRotation(),
				texture_region.getRegionX(),
				texture_region.getRegionY(),
				texture_region.getRegionWidth(),
				texture_region.getRegionHeight(),
				false, false
			);
		}
	}

}
