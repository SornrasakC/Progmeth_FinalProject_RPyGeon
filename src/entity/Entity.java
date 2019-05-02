package entity;



import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
		Rectangle r1 = new Rectangle((int) this.x, (int) this.y, (int) (sprite.getWidth()), (int) (sprite.getHeight()));
		Rectangle r2 = new Rectangle((int) other.x, (int) other.y, (int) (other.sprite.getWidth()), (int)(other.sprite.getHeight()));
		Shape intersectedShape = Shape.intersect(r1, r2);
		return intersectedShape.getBoundsInLocal().getWidth() != -1;
	}

	public Image getSprite()
	{
		return sprite;
	}

	public void setSprite(Image sprite)
	{
		this.sprite = sprite;
	}
	
}
