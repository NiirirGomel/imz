package com.glove.imz.objects.layers;
import com.badlogic.gdx.scenes.scene2d.*;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;

public class SortedGroup extends Group
{
	private ActorComparator actorComparator = new ActorComparator();
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		getChildren().sort(actorComparator);
		super.draw(batch, parentAlpha);
	}
	
	public class ActorComparator implements Comparator<Actor>
	{
		@Override
		public int compare(Actor p1, Actor p2) {
			return (p2.getY() - p1.getY()) >= 0 ? 1 : -1;
		}
	}
}
