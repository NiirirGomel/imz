package com.glove.imz.utils;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class CameraController
{
	private Camera camera;
	private Actor target;
	
	public CameraController(Camera camera) {
		this.camera = camera;
	}
	
	public void follow(Actor target) {
		this.target = target;
	}
	
	public void act(float delta) {
		if(target != null){
			camera.position.set(target.getX(),target.getY(),0);
		}
	}
}
