package com.glove.imz.objects.giu;
import com.glove.imz.objects.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.*;
import com.glove.imz.objects.layers.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.glove.imz.utils.animation.*;

public class TargetMark extends ObjectActor
{
	public SnapshotArray<ObjectActor> targets = new SnapshotArray<ObjectActor>();
	ObjectActor focus_target;
	private Camera camera;

	public TargetMark(GuiLayer gui_layer, Vector2 size, TextureRegion texture_region, InputListener listener) {
		setSize(size.x,size.y);
		this.texture_region = texture_region;
		camera = gui_layer.getStage().getCamera();
		if(listener != null){
			addListener(listener);
		}
		gui_layer.addActor(this);
	}
	
	public TargetMark(GuiLayer gui_layer, Vector2 size, ObjectAnimation animation, InputListener listener) {
		setSize(size.x,size.y);
		this.animation = animation;
		camera = gui_layer.getStage().getCamera();
		if(listener != null){
			addListener(listener);
		}
		gui_layer.addActor(this);
	}
	
	public ObjectActor getTarget() {
		return focus_target;
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		for(ObjectActor target : targets){
			if(x > target.getX()-camera.position.x-getWidth()/2
			   && x < target.getX()-camera.position.x+getWidth()/2
			   && y > target.getY()+target.getHeight()/2-camera.position.y-getHeight()/2
			   && y < target.getY()+target.getHeight()/2-camera.position.y+getHeight()/2){
				focus_target = target;
				return this;
			}
		}
		return super.hit(x,y,touchable);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for(ObjectActor target : targets){
			if(animation != null){
				animation.draw(batch, target.getX()-camera.position.x-getWidth()/2, target.getY()+target.getHeight()/2-camera.position.y-getHeight()/2, getWidth(), getHeight(), getRotation());
			} else if(texture_region != null){
				batch.draw(
					texture_region.getTexture(),
					target.getX()-camera.position.x-getWidth()/2, target.getY()+target.getHeight()/2-camera.position.y-getHeight()/2,
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

}
