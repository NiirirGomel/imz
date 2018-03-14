package com.glove.imz.objects.units;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.glove.imz.objects.giu.*;

public class Whisper extends UnitObject {
	
	public Vector2 cursorOfset = new Vector2();
	
	public Whisper(World world) {
		texture_region = new TextureRegion(new Texture(Gdx.files.internal("drawable/units/whisper.png")));
		setSize(1f,1f);
		moveSpeed = 5f;
		changeState(UnitState.STAY);

		createBody(world, BodyDef.BodyType.DynamicBody);
	}

	@Override
	public void moveStop(float x, float y) {
		super.moveStop(x,y);
		body.setLinearVelocity(0,0);
	}

	@Override
	public boolean readyMove(float x, float y) {
		cursorOfset.set(x,y);
		moveVector.set(x,y);
		if(moveVector.len2() < 2) {
			x = y = 0;
		}
		return super.readyMove(x, y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		GameConsole.setText("cursor("+(getX()+cursorOfset.x)+", "+(getY()+cursorOfset.y)+")");
		batch.draw(
			texture_region.getTexture(),
			(getX()+cursorOfset.x)-getWidth()/2, (getY()+cursorOfset.y)-getHeight()/2,
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
