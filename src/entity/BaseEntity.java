package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class BaseEntity extends Entity
{

	public BaseEntity(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.sprite = RenderableHolder.genericVendor;
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		gc.drawImage(sprite, x, y);

	}

}
