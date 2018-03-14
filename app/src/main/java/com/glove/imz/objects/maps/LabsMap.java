package com.glove.imz.objects.maps;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.physics.box2d.*;
import com.glove.imz.objects.*;
import com.glove.imz.objects.layers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class LabsMap extends Group
{
	private Stage stage;
	private World world;
	
	public Group backLayer;
	public SortedGroup middleLayer;
	public Group frontLayer; 
	
	public LabsMap(World world) {
		this.world = world;
		// слои
		addActor(backLayer = new Group());
		addActor(middleLayer = new SortedGroup());
		addActor(frontLayer = new Group());
		
		ObjectActor obj;
		
		obj = new ObjectActor();
		obj.texture_region = new TextureRegion(new Texture(Gdx.files.internal("drawable/objects/decor/lab_level.png")));
		obj.setBounds(35f,0f,70f,70f);
		backLayer.addActor(obj);
		
		// первая комната
		// стены
		backLayer.addActor(new ObjectActor(world,0.424f,67.4f,9.971f,0.4f));
		backLayer.addActor(new ObjectActor(world,0.424f,59.75f,0.53f,7.65f));
		backLayer.addActor(new ObjectActor(world,9.85f,59.75f,0.54f,7.65f));
		backLayer.addActor(new ObjectActor(world,0.956f,59.75f,3.56f,0.4f));
		backLayer.addActor(new ObjectActor(world,6.3f,59.75f,3.56f,0.4f));
		// клетка
		backLayer.addActor(new ObjectActor(world,1.23f,66.43f,4.66f,0.97f));
		// капсула
		backLayer.addActor(new ObjectActor(world,6f,66.16f,3.3f,1.24f));
		// стол
		backLayer.addActor(new ObjectActor(world,1.1f,62.88f,1.5f,2.5f));
		
		
		// вторая комната
		// стены
		backLayer.addActor(new ObjectActor(world,3.27f,51f,0.43f,8.75f));
		backLayer.addActor(new ObjectActor(world,7.12f,51f,0.405f,8.75f));
		// ящик
		backLayer.addActor(new ObjectActor(world,3.7f,54.55f,1.5f,0.96f));
		
		// третья комната
		// стены
		backLayer.addActor(new ObjectActor(world,0.424f,50.58f,4.076f,0.41f));
		backLayer.addActor(new ObjectActor(world,6.3f,50.58f,8.88f,0.41f));
		backLayer.addActor(new ObjectActor(world,0.424f,43.89f,0.532f,6.7f));
		backLayer.addActor(new ObjectActor(world,0.956f,43.89f,14.224f,0.41f));
		// ящики
		backLayer.addActor(new ObjectActor(world,7.12f,49.49f,1.5f,0.96f));
		backLayer.addActor(new ObjectActor(world,4.37f,46.35f,1.5f,0.96f));
		backLayer.addActor(new ObjectActor(world,10.95f,47.44f,1.5f,0.96f));
		// столбы
		backLayer.addActor(new ObjectActor(world,1.37f,48.66f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,6.16f,48.66f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,11.5f,48.66f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,3.7f,47.44f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,8.75f,47.44f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,1.24f,45.66f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,6.03f,45.66f,0.68f,0.14f));
		backLayer.addActor(new ObjectActor(world,11.37f,45.66f,0.68f,0.14f));
		
		// четвертая комната
		// стены
		backLayer.addActor(new ObjectActor(world,15.18f,48.55f,0.55f,6.68f));
		backLayer.addActor(new ObjectActor(world,15.18f,39.9f,0.55f,6.85f));
		backLayer.addActor(new ObjectActor(world,15.73f,54.82f,8.87f,0.41f));
		backLayer.addActor(new ObjectActor(world,24.6f,39.9f,0.55f,15.33f));
		backLayer.addActor(new ObjectActor(world,15.18f,39.65f,3.4f,0.25f));
		backLayer.addActor(new ObjectActor(world,21.75f,39.65f,3.4f,0.25f));
		// ящики
		backLayer.addActor(new ObjectActor(world,21.04f,53.73f,1.5f,0.96f));
		backLayer.addActor(new ObjectActor(world,21.6f,52.64f,1.5f,0.96f));
		backLayer.addActor(new ObjectActor(world,23.1f,51.96f,1.5f,0.96f));
		backLayer.addActor(new ObjectActor(world,16.13f,45.25f,1.5f,0.96f));
		backLayer.addActor(new ObjectActor(world,22.56f,45.38f,1.5f,0.96f));
		// стол и лампа
		backLayer.addActor(new ObjectActor(world,18.33f,47.99f,3.42f,0.96f));
		backLayer.addActor(new ObjectActor(world,21.73f,48.81f,0.97f,0.26f));
		//компьютеры
		backLayer.addActor(new ObjectActor(world,17.23f,42.65f,2.06f,1.37f));
		backLayer.addActor(new ObjectActor(world,20.91f,42.93f,1.79f,1.51f));
		//капсула
		backLayer.addActor(new ObjectActor(world,17.36f,51.4f,1.78f,0.55f));
		
		//лифт
		//стены
		backLayer.addActor(new ObjectActor(world,15.18f,39.65f,3.43f,0.25f));
		backLayer.addActor(new ObjectActor(world,21.72f,39.65f,3.43f,0.25f));
		backLayer.addActor(new ObjectActor(world,15.18f,33.78f,0.55f,5.87f));
		backLayer.addActor(new ObjectActor(world,24.6f,33.78f,0.55f,5.87f));
		backLayer.addActor(new ObjectActor(world,15.72f,33.78f,8.88f,0.25f));
		
		
		//obj.setPosition(0.424f,-2.574f);
		//floor = new TileActor();
		//floor.setSize(10f,10f);
		//backLayer.addActor(floor);
		
		//table = new TableActor(world);
		//table.body.setTransform(3f,3f,0f);
		//middleLayer.addActor(table);
		
		//table = new TableActor(world);
		//table.body.setTransform(-3f,3f,0f);
		//middleLayer.addActor(table);
		
		
		
	}
}
