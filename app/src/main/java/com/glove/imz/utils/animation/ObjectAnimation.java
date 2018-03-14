package com.glove.imz.utils.animation;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.glove.imz.objects.giu.*;

public class ObjectAnimation
{
	private Texture texture;
	private Map<String, ArrayList<AnimationFrame>> textureMap;

	public String name = "";
	public String param = "";
	private AnimationFrame frame;

	private float curTime;
	private boolean finish;

	public ObjectAnimation(FileHandle textureHandle) {
		texture = new Texture(textureHandle);
		// незнаю почему, но флипы работуют только с отзкркалеными текстурами
		texture.setWrap(Texture.TextureWrap.MirroredRepeat,Texture.TextureWrap.MirroredRepeat);
		textureMap = new HashMap<String, ArrayList<AnimationFrame>>();
	}

	public void addAnimation(String name, String param, boolean mirror, int u, int v, int width, int height, int count, float time, boolean repeat) {
		if(mirror){
			if(!textureMap.containsKey(name+param)){
				textureMap.put(name+param, new ArrayList<AnimationFrame>());
			}
			if(!textureMap.containsKey(name+param+"mirror")){
				textureMap.put(name+param+"mirror", new ArrayList<AnimationFrame>());
			}
			for(int i = 0; i < count; i++){
				AnimationFrame af1 = new AnimationFrame(texture,u+i*width,v,width,height,false,time);
				AnimationFrame af2 = new AnimationFrame(texture,u+i*width,v,width,height,true,time);
				if(repeat && i+1 == count){
					af1.repeat = true;
					af2.repeat = true;
				}
				textureMap.get(name+param).add(af1);
				textureMap.get(name+param+"mirror").add(af2);
			}
		} else {
			if(!textureMap.containsKey(name+param)){
				textureMap.put(name+param, new ArrayList<AnimationFrame>());
			}
			for(int i = 0; i < count; i++){
				AnimationFrame af = new AnimationFrame(texture,u+i*width,v,width,height,false,time);
				if(repeat && i+1 == count){
					af.repeat = true;
				}
				textureMap.get(name+param).add(af);
			}
		}
	}

	public void setAnimation(String name, String param) {
		if(textureMap.containsKey(name+param)){
			frame = textureMap.get(name+param).get(0);
			this.name = name;
			this.param = param;
			curTime = 0;
			finish = false;
		}
	}

	public void setAnimation(String name) {
		if(textureMap.containsKey(name+param)){
			frame = textureMap.get(name+param).get(0);
			this.name = name;
			curTime = 0;
			finish = false;
		}
	}
	
	public void changeDirection(float angle) {
		if(angle > 270 || angle < 90){
			setParam("");
		} else if(angle > 90 && angle < 270){
			setParam("mirror");
		}
//		if(angle > 315 || angle < 45){
//			animation.setParam("right");
//		} else if(angle > 45 && angle < 135){
//			animation.setParam("down");
//		} else if(angle > 135 && angle < 225){
//			animation.setParam("left");
//		} else if(angle > 225 && angle < 315){
//			animation.setParam("down");
//		}
	}
	
	public void setParam(String param) {
		if(this.param != param){
			if(textureMap.containsKey(name+param)){
				int frameIndex = textureMap.get(name+this.param).indexOf(frame);
				if(frameIndex < textureMap.get(name+param).size()) {
					frame = textureMap.get(name+param).get(frameIndex);
				} else {
					frame = textureMap.get(name+param).get(0);
					curTime = 0;
				}
				this.param = param;
			}
		}
	}

	public boolean finished() {
		return finish;
	}

	private void run(float deltaTime) {
		if(!finish){
			if((curTime += deltaTime) > frame.time){
				curTime -= frame.time;
				int frameIndex = textureMap.get(name+param).indexOf(frame);
				if((frameIndex+1) < textureMap.get(name+param).size()){
					frame = textureMap.get(name+param).get(frameIndex+1);
				} else {
					if(frame.repeat){
						frame = textureMap.get(name+param).get(0);
					} else {
						finish = true;
					}
				}
			}
		}
	}

	public void draw(Batch batch, float x, float y, float width, float height, float rotation) {
		if(frame != null){
			run(Gdx.graphics.getDeltaTime());
			batch.draw(
				frame.getTexture(),
				x, y,
				width/2, height/2,
				width, height,
				1f, 1f,
				rotation,
				frame.getRegionX(),
				frame.getRegionY(),
				frame.getRegionWidth(),
				frame.getRegionHeight(),
				false, false
			);
		}
	}

	public class AnimationFrame extends TextureRegion
	{
		public float time;
		public boolean repeat;

		public AnimationFrame(Texture texture, int u, int v, int width, int height, boolean mirror, float time) {
			super(texture,u,v,width,height);
			this.time = time;
			if(mirror){
				int x = u*2+width;
				setRegionWidth(width-x);
				setRegionX(u-x);
			}
		}
		
	}
}
