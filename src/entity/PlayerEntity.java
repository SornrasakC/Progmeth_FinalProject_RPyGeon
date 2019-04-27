package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class PlayerEntity extends Entity {
	
	private int direction;
	private Image playerSprite;
	
	public PlayerEntity() {
		// 0 = north, 1 = east, 2 = south, 4 = west
		direction = 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		switch(direction) {
		case 0 : playerSprite = RenderableHolder.playerSpriteNorth;
				 break;
		case 1 : playerSprite = RenderableHolder.playerSpriteEast;
		 break;
		case 2 : playerSprite = RenderableHolder.playerSpriteSouth;
		 break;
		case 3 : playerSprite = RenderableHolder.playerSpriteWest;
		 break;
		}
		gc.drawImage(playerSprite, this.x, this.y);
	}

}
