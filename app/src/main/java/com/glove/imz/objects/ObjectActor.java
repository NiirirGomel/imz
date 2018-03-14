
package com.glove.imz.objects;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.*;
import com.glove.imz.utils.animation.*;

public class ObjectActor extends Group
{
	public TextureRegion texture_region;
	public ObjectAnimation animation;
	public Body body;

	public ObjectActor() {}
	public ObjectActor(World world, float x, float y, float width, float height) {
		super();
		//obj.textureRegion = new TextureRegion(new Texture(Gdx.files.internal("drawable/objects/decor/lab_level.png")));
		setBounds(x+width/2,y,width,height);
		createBody(world,BodyDef.BodyType.StaticBody);
		addBoxFixture(width, height, 0f, height/2);
		
		body.setTransform(getX(),getY(),0f);
	}
	
	public void createBody(World world, BodyDef.BodyType type) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		body = world.createBody(bodyDef);
	}
	
	public void addBoxFixture(float width, float height, float ofsetX, float ofsetY) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2, height/2, new Vector2(ofsetX,ofsetY),0f);
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		body.createFixture(fixture);
		shape.dispose();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (body != null) {
			setPosition(body.getPosition().x, body.getPosition().y);
			setRotation((float) Math.toDegrees(body.getAngle()));
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(animation != null){
			animation.draw(batch, getX()-getWidth()/2, getY(), getWidth(), getHeight(), getRotation());
		} else if(texture_region != null){
			batch.draw(
				texture_region.getTexture(),
				getX()-getWidth()/2, getY(),
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
