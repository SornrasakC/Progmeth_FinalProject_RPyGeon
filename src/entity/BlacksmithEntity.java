package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class BlacksmithEntity extends Entity {
	
	public BlacksmithEntity(double x, double y) {
		this.x = x;
		this.y = y;
		this.sprite = RenderableHolder.blacksmith;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, x, y);
	}

}
