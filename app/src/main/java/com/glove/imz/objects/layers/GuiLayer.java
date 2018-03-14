package com.glove.imz.objects.layers;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;

public class GuiLayer extends Group
{
	private Camera camera;
	
	public GuiLayer(Camera camera) {
		this.camera = camera;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		setPosition(camera.position.x,camera.position.y);
		super.draw(batch, parentAlpha);
	}
}
