package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class DungeonEntity extends Entity
{

	public DungeonEntity(double x, double y) {
		this.x = x;
		this.y = y;
		this.sprite = RenderableHolder.genericVendor;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, x, y);
	}
	
}
