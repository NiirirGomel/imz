package com.glove.imz.objects.units;
import com.glove.imz.objects.*;
import com.glove.imz.objects.giu.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.*;
import com.glove.imz.objects.layers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;

public class UnitObject extends ObjectActor implements ObjController
{
	public enum UnitState {
		STAY,
		MOVE,
		ATTACK,
		ATTACKED,
		DEAD
	}
	public enum UnitCategory {
		ZOMBIE,
		HUMAN,
		ROBOT
	}
		
	public UnitState state;
	public UnitCategory category;
	public Vector2 moveVector = new Vector2();
	
	public float body_dist = 1f;
	public float view_dist = 5f;
	
	public float health = 2f;
	public float moveSpeed = 3f;

	@Override
	public void moveStart(float x, float y) {
		changeDirection(x,y);
	}
	
	@Override
	public void changeDirection(float x, float y) {
		if(readyMove(x,y)){
			moveVector.setLength(moveSpeed);
			if(animation != null){
				animation.changeDirection(moveVector.angle());
			}
		}	
	}

	@Override
	public void moveStop(float x, float y) {
		if(state == UnitState.MOVE){
			changeState(UnitState.STAY);
		}
	}

	public boolean readyMove(float x, float y) {
		moveVector.set(x,y);
		if(!moveVector.isZero()){
			if(state == UnitState.STAY){
				changeState(UnitState.MOVE);
			}
		} else {
			moveStop(0,0);
		}
		return state == UnitState.MOVE;
	}
	
	@Override
	public void act(float delta) {
		stateControl(delta);
		super.act(delta);
	}

	public void stateControl(float delta) {
		switch(state){
			case MOVE:
				move(delta);
				break;
			case ATTACK:
				if(animation != null){
					if(animation.finished()){
						changeState(UnitState.STAY);
					}
				} else {
					changeState(UnitState.STAY);
				}
				break;
			case ATTACKED:
				if(animation != null){
					if(animation.finished()){
						changeState(UnitState.STAY);
					}
				} else {
					changeState(UnitState.STAY);
				}
				break;
		}
	}

	public void changeState(UnitState state) {
		this.state = state;
	}
	
	public void move(float delta) {
		if(body != null) {
			body.setLinearVelocity(moveVector);
		} else {
			moveBy(moveVector.x*delta,moveVector.y*delta);
		}
	}
	
	public void getDamage(float damage) {
		health -= damage;
		if(health <= 0){
			health = 0;
			changeState(UnitState.DEAD);
			if(body != null){
				body.setActive(false);
			}
		}
	}

}
