package entity;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {

	protected double x,y;
	protected int z;
	protected boolean visible;
	
	protected Entity(){
		visible = true;
	}
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}
	
	protected boolean collideWith(Entity other){
		return ((this.x - other.x < 0) || (this.y - other.y < 0));
	}
}
