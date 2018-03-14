package com.glove.imz.objects.giu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.glove.imz.objects.units.*;

public class JoyStickActor extends Group
{
	private Vector2 position;
	private Rectangle iniciateRect;
	
	private int cur_pointer = -1; // ready
	private ObjController target;
	
	private Texture fon;
	private Texture btn;
	private Vector2 btnSize;
	public Vector2 btnPosition = new Vector2();
	
	public JoyStickActor() {
		CreateJoyStick();
	}
	public JoyStickActor(Vector2 pos) {
		position = pos;
		setPosition(pos.x,pos.y);
		btnPosition.set(pos.x,pos.y);
		iniciateRect = new Rectangle(-1.5f,-1.5f,3f,3f);
		CreateJoyStick();
	}
	public JoyStickActor(Rectangle rect) {
		iniciateRect = rect;
		CreateJoyStick();
	}

	private void CreateJoyStick() {
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(cur_pointer == -1){
					cur_pointer = pointer;
					
					if(position == null){
						setPosition(x,y);
						if(target != null){
							target.moveStart(0,0);
						}
					} else {
						if(target != null){
							target.moveStart(x,y);
						}
					}
					setBtnPosition(x,y);
					return true;
				}
				return false;
			}
			
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if(pointer == cur_pointer){
					setBtnPosition(x,y);
					if(target != null){
						target.changeDirection(x,y);
					}
				}
				super.touchDragged(event, x, y, pointer);
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(pointer == cur_pointer){
					if(position == null){
						setPosition(0f,0f);
					} else {
						btnPosition.set(position.x,position.y);
					}
					cur_pointer = -1;
					if(target != null){
						target.moveStop(x,y);
					}
				}
				super.touchUp(event, x, y, pointer, button);
			}
		});
		
		fon = new Texture(Gdx.files.internal("drawable/gui/joystick/fon.png"));
		btn = new Texture(Gdx.files.internal("drawable/gui/joystick/btn.png"));
		setSize(3f,3f);
		btnSize = new Vector2(2f,2f);
	}

	@Override
	public Actor hit(float x, float y, boolean touchable)
	{
		if(iniciateRect != null
		&& (x < iniciateRect.x
			|| x > iniciateRect.x+iniciateRect.width
			|| y < iniciateRect.y
			|| y > iniciateRect.y+iniciateRect.height
			)
		) {
			return null;
		}
		return this;
	}
	
	public void setTarget(ObjController target) {
		this.target = target;
	}
	
	public void setBtnPosition(float x, float y) {
		btnPosition.set(x,y);
		btnPosition.limit(1f);
		btnPosition.add(getX(),getY());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(position != null || cur_pointer != -1){
			if(fon != null){
				batch.draw(fon,getX()-getWidth()/2,getY()-getHeight()/2, getWidth()/2, getHeight()/2,getWidth(),getHeight(), 1f, 1f, getRotation(), 0, 0, fon.getWidth(),fon.getHeight(), false, false);
			}
			if(btn != null){
				batch.draw(btn,btnPosition.x-btnSize.x/2,btnPosition.y-btnSize.y/2, btnSize.x/2, btnSize.y/2,btnSize.x,btnSize.y, 1f, 1f, getRotation(), 0, 0, btn.getWidth(),btn.getHeight(), false, false);
			}
		}
	}

}
