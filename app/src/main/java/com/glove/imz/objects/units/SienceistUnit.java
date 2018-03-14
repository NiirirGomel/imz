package com.glove.imz.objects.units;
import com.glove.imz.objects.giu.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.glove.imz.utils.animation.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.glove.imz.objects.*;

public class SienceistUnit extends UnitObject
{
	
	public SienceistUnit(World world) {
		setSize(2f,2f);
		category = UnitCategory.HUMAN;
		
		animation = new ObjectAnimation(Gdx.files.internal("drawable/units/sinceist2.png"));
		animation.addAnimation("stay","",true,0,0,40,40,3,0.5f,true);
		animation.addAnimation("move","",true,0,40,40,40,3,0.5f,true);
		animation.addAnimation("attacked","",true,0,80,40,40,2,0.5f,false);
		animation.addAnimation("die","",true,0,120,40,40,3,0.2f,false);
		
		changeState(UnitState.STAY);
		
		createBody(world,BodyDef.BodyType.DynamicBody);
		addBoxFixture(1f, getHeight()/10, 0f, getHeight()/20);
		
		body.setLinearDamping(4f);
		
		body.setUserData(new ContactController() {
			@Override
			public void beginContact(Contact p1) {
				GameConsole.setText("beginContact");
			}

			@Override
			public void endContact(Contact p1) {
				GameConsole.setText("endContact");
			}
		});
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		// ai
		switch(state){
			case STAY:
			case MOVE:
				escape();
				break;
		}
	}
	
	public void escape() {
		Vector2 escape_direction = new Vector2();
		for(Actor actor: getParent().getChildren()){
			if(actor != this && actor instanceof UnitObject){
				UnitObject target = (UnitObject)actor;

				if(target.category == UnitCategory.ZOMBIE && target.state != UnitState.DEAD){
					if(Vector2.dst2(getX(),getY(),target.getX(),target.getY())-body_dist-target.body_dist<=view_dist){
						escape_direction.add(target.getX(),target.getY());
					}
				}
			}
		}
		if(!escape_direction.isZero()){
			moveStart(getX()-escape_direction.x,getY()-escape_direction.y);
		} else if(state == UnitState.MOVE) {
			moveStop(0,0);
		}
	}
	
	@Override
	public void changeState(UnitObject.UnitState state) {
		super.changeState(state);	
		switch(state){
			case STAY:
				animation.setAnimation("stay");
				break;
			case MOVE:
				animation.setAnimation("move");
				break;
			case ATTACKED:
				animation.setAnimation("attacked");
				break;
			case DEAD:
				animation.setAnimation("die");
		}
	}

}
