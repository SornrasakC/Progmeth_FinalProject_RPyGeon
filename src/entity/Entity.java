package entity;

import java.awt.Rectangle;

import javafx.scene.image.Image;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable
{

	protected double x, y;
	protected int z;
	protected boolean visible;
	protected Image sprite;

	protected Entity()
	{
		visible = true;
	}

	@Override
	public boolean isVisible()
	{
		return visible;
	}

	@Override
	public int getZ()
	{
		return z;
	}

	protected boolean collideWith(Entity other)
	{
//		System.out.println("shop sprite " + other.sprite.getHeight() + "," + other.sprite.getWidth());
//		System.out.println("player sprite " + this.sprite.getHeight() + "," + this.sprite.getWidth());
		Rectangle r1 = new Rectangle((int) this.x, (int) this.y, (int) (this.x + sprite.getWidth()), (int) (this.y + sprite.getHeight()));
		Rectangle r2 = new Rectangle((int) other.x, (int) other.y, (int) (other.x + other.sprite.getWidth()), (int)(other.y + other.sprite.getHeight()));
		return r1.intersects(r2);
	}
}
