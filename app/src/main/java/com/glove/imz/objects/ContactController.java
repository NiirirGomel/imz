package com.glove.imz.objects;
import com.badlogic.gdx.physics.box2d.*;

public class ContactController
{
	public void beginContact(Contact p1) {}
	public void endContact(Contact p1) {}
	public void preSolve(Contact p1, Manifold p2) {}
	public void postSolve(Contact p1, ContactImpulse p2) {}
}
