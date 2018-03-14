package com.glove.imz.objects.units;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.glove.imz.objects.giu.*;
import com.badlogic.gdx.math.*;
import com.glove.imz.utils.animation.*;
import com.badlogic.gdx.utils.*;
import com.glove.imz.objects.layers.*;
import com.glove.imz.objects.*;

public class FaustUnit extends UnitObject
{
	private TargetMark mark;
	private BtnObject main_btn;
	
	private float damage = 1f;
	private float attack_dist = 1f;
	
	public FaustUnit(World world, GuiLayer gui_layer) {
		setSize(2f,2f);
		category = UnitCategory.ZOMBIE;
		
		moveSpeed = 5f;
		
		animation = new ObjectAnimation(Gdx.files.internal("drawable/units/zombie_faust2.png"));	
		animation.addAnimation("stay","",true,0,0,40,40,4,0.2f,true);
		animation.addAnimation("move","",true,0,40,40,40,4,0.2f,true);
		animation.addAnimation("attack","",true,0,80,40,40,3,0.2f,false);
		
		changeState(UnitState.STAY);
		
		createBody(world,BodyDef.BodyType.DynamicBody);
		addBoxFixture(1f, getHeight()/10, 0f, getHeight()/20);
		
		body.setLinearDamping(4f);
		
		mark = new TargetMark(
			gui_layer,
			new Vector2(1.5f,1.5f),
			new ObjectAnimation(Gdx.files.internal("drawable/gui/marks/target_mark.png")),
			null
		);
		mark.animation.addAnimation("select","",false,0,0,16,16,1,0f,true);
		mark.animation.setAnimation("select");
		
		main_btn = new BtnObject(
			gui_layer,
			new Vector2(gui_layer.getStage().getViewport().getWorldWidth()/2-2f,-gui_layer.getStage().getViewport().getWorldHeight()/2+2f),
			new Vector2(2f,2f),
			new ObjectAnimation(Gdx.files.internal("drawable/gui/btns/main_btn.png")),
			new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
					mbTouch();
					return true;
				}
			}
		);
		main_btn.animation.addAnimation("disable","",false,0,0,16,16,1,0f,true);
		main_btn.animation.addAnimation("attack","",false,0,16,16,16,1,0f,true);
		main_btn.animation.setAnimation("disable");
	}
	
	public void mbTouch() {
		switch(state){
			case STAY:
			case MOVE:
				if(mark.targets.size > 0){
					atack((UnitObject)mark.targets.get(0));
				}
				break;
		}
	}
	
	public void atack(UnitObject target) {
		changeState(UnitState.ATTACK);
		target.changeState(UnitState.ATTACKED);
		target.getDamage(damage);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		switch(state){
			case STAY:
			case MOVE:
				mark.targets = searchTarget();
				break;
		}
	}
	
	public SnapshotArray<ObjectActor> searchTarget() {
		SnapshotArray<ObjectActor> result = new SnapshotArray<ObjectActor>();
		float first_dist = 0;
		for(Actor actor: getParent().getChildren()){
			if(actor != this && actor instanceof UnitObject){
				UnitObject target = (UnitObject)actor;
				
				if(target.category == UnitCategory.HUMAN && target.state != UnitState.DEAD){
					float dist = Vector2.dst2(getX(),getY(),target.getX(),target.getY())-body_dist-target.body_dist;
					if(dist <= attack_dist){
						if(result.size == 0){
							first_dist = dist;
							result.add((ObjectActor)target);
						} else if(dist < first_dist){
							first_dist = dist;
							result.set(0,(ObjectActor)target);
						}
						if(main_btn.animation.name != "attack"){
							main_btn.animation.setAnimation("attack");
						}
					}
				}
			}
		}
		if(result.size == 0 && main_btn.animation.name != "disable"){
			main_btn.animation.setAnimation("disable");
		}
		return result;
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
			case ATTACK:
				animation.setAnimation("attack");
				break;
		}
		switch(state){
			case ATTACK:
			case ATTACKED:
			case DEAD:
				targetsClear();
				break;
		}
	}
	
	private void targetsClear() {
		if(mark.targets.size > 0){
			mark.targets.clear();
		}
		if(main_btn.animation.name != "disable"){
			main_btn.animation.setAnimation("disable");
		}
	}

}
