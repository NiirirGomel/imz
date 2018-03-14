package com.glove.imz.screens.level_labs;
import com.glove.imz.objects.giu.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.*;
import com.glove.imz.objects.units.*;
import com.glove.imz.objects.maps.*;
import java.util.*;
import com.glove.imz.objects.layers.*;
import com.glove.imz.utils.*;
import com.glove.imz.utils.animation.*;
import com.glove.imz.objects.*;

public class LevelLabsStage extends Stage implements ContactListener
{
	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;
	private World world;
	private CameraController cameraController;
	private Box2DDebugRenderer renderer;
	
	private FaustUnit faust;
	private SienceistUnit sienceist;
	private LabsMap labsMap;
	
	private Vector3 touchPoint = new Vector3();
	public GuiLayer guiLayer;
	public JoyStickActor joyStick;

	public LevelLabsStage() {
		super(new ExtendViewport(10,10));
		GameConsole.inicialize(getViewport());
		
		getCamera().position.set(0,0,0);
		cameraController = new CameraController(getCamera());

		Gdx.input.setInputProcessor(this);
		
		world = new World(new Vector2(0, 0), true);
		world.setContactListener(this);
		renderer = new Box2DDebugRenderer();
		
		labsMap = new LabsMap(world);
		addActor(labsMap);
		
		guiLayer = new GuiLayer(getCamera());
		addActor(guiLayer);	
		
		faust = new FaustUnit(world,guiLayer);
		faust.body.setTransform(7.9f,65.5f,0f);
		labsMap.middleLayer.addActor(faust);
		
		sienceist = new SienceistUnit(world);
		sienceist.body.setTransform(4.6f,58.4f,0f);
		labsMap.middleLayer.addActor(sienceist);
		
		sienceist = new SienceistUnit(world);
		sienceist.body.setTransform(6.2f,58.5f,0f);
		labsMap.middleLayer.addActor(sienceist);
		
//		SienceistForDronUnit sinceist_for_dron = new SienceistForDronUnit(world);
//		sinceist_for_dron.body.setTransform(13.8f,49.5f,0f);
//		labsMap.middleLayer.addActor(sinceist_for_dron);
//		
//		DronUnit dron = new DronUnit(world);
//		dron.body.setTransform(10f,46f,0f);
//		labsMap.middleLayer.addActor(dron);
		
		//joyStick = new JoyStickActor(new Rectangle(-getViewport().getWorldWidth()/2,-getViewport().getWorldHeight()/2,6f,getViewport().getWorldHeight()));
		joyStick = new JoyStickActor(new Vector2(-getViewport().getWorldWidth()/2+2f,-getViewport().getWorldHeight()/2+2f));
		guiLayer.addActor(joyStick);
		
		cameraController.follow(faust);
		joyStick.setTarget(faust);
		
		GameConsole.output(guiLayer);
		
		// debug
		//whisperDebug();
	}
	public void whisperDebug() {
		Whisper whisper = new Whisper(world);
		whisper.body.setTransform(8f,65f,0f);
		addActor(whisper);
		joyStick.setTarget(whisper);
		cameraController.follow(whisper);
	}

	@Override
	public void act(float delta) {
		Array<Body> bodies = new Array<Body>(world.getBodyCount());
		world.getBodies(bodies);

		for(Body body: bodies) {
			update(body);
		}

		// Fixed timestep
		accumulator += delta;
		while (accumulator >= delta) {
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
		}

		super.act(delta);
		cameraController.act(delta);
	}
	
	private void update(Body body) {

	}
	
	
	@Override
	public void draw() {
		super.draw();
		// отрисовка всех коллайдеров на стейдже
		renderer.render(world,getCamera().combined);
	}

	@Override
	public Actor hit(float stageX, float stageY, boolean touchable)
	{
		Actor target = super.hit(stageX, stageY, touchable);
		return target;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// расшифровываем координаты касания и записываем их в touchPoint
		//getCamera().unproject(touchPoint.set(screenX,screenY,0));
		//console.setText("touchPoint"+touchPoint.toString());
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		//getCamera().unproject(touchPoint.set(screenX,screenY,0));
		//GameConsole.setText("touchPoint"+touchPoint.toString());
		
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//getCamera().unproject(touchPoint.set(screenX,screenY,0));
		//console.setText("touchPoint"+touchPoint.toString());

		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public void beginContact(Contact p1) {
		Object userObjectA = p1.getFixtureA().getBody().getUserData();
		Object userObjectB = p1.getFixtureB().getBody().getUserData();
		if(userObjectA instanceof ContactController) {
			((ContactController)userObjectA).beginContact(p1);
			GameConsole.setText("begin contact stage p1");
		}
		if(userObjectB instanceof ContactController) {
			((ContactController)userObjectB).beginContact(p1);
			GameConsole.setText("begin contact stage p2");
		}
	}

	@Override
	public void endContact(Contact p1) {
		Object userObjectA = p1.getFixtureA().getBody().getUserData();
		Object userObjectB = p1.getFixtureB().getBody().getUserData();
		if(userObjectA instanceof ContactController) {
			((ContactController)userObjectA).endContact(p1);
			GameConsole.setText("end contact stage p2");
		}
		if(userObjectB instanceof ContactController) {
			((ContactController)userObjectB).endContact(p1);
			GameConsole.setText("end contact stage p2");
		}
	}

	@Override
	public void preSolve(Contact p1, Manifold p2) {
		Object userObjectA = p1.getFixtureA().getBody().getUserData();
		Object userObjectB = p1.getFixtureB().getBody().getUserData();
		if(userObjectA instanceof ContactController) {
			((ContactController)userObjectA).preSolve(p1,p2);
		}
		if(userObjectB instanceof ContactController) {
			((ContactController)userObjectB).preSolve(p1,p2);
		}
	}

	@Override
	public void postSolve(Contact p1, ContactImpulse p2) {
		Object userObjectA = p1.getFixtureA().getBody().getUserData();
		Object userObjectB = p1.getFixtureB().getBody().getUserData();
		if(userObjectA instanceof ContactController) {
			((ContactController)userObjectA).postSolve(p1,p2);
		}
		if(userObjectB instanceof ContactController) {
			((ContactController)userObjectB).postSolve(p1,p2);
		}
	}
}
