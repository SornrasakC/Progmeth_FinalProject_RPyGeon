package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class ShopEntity extends Entity {
	
	public ShopEntity(double x, double y) {
		this.x = x;
		this.y = y;
		this.sprite = RenderableHolder.itemshop;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, x, y);
	}

}
