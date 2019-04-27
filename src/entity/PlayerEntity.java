package entity;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class PlayerEntity extends Entity {

	private int direction;
	private static final int MOVE_SPEED = 5;
	private boolean isFreeze = false;

	public PlayerEntity(double x, double y, int direction) {
		// 0 = north, 1 = east, 2 = south, 3 = west
		this.direction = direction;
		this.sprite = RenderableHolder.playerSpriteSouth;
		this.x = x;
		this.y = y;
		this.z = 9;
		this.visible = true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		switch (direction) {
		case 0:
			this.sprite = RenderableHolder.playerSpriteNorth;
			break;
		case 1:
			this.sprite = RenderableHolder.playerSpriteEast;
			break;
		case 2:
			this.sprite = RenderableHolder.playerSpriteSouth;
			break;
		case 3:
			this.sprite = RenderableHolder.playerSpriteWest;
			break;
		}
		gc.drawImage(this.sprite, this.x, this.y);
	}

	public void update() {
		if(!isFreeze) {
			if (InputUtility.getKeyPressed(KeyCode.A)) {
				moveLeft();
			}
			if (InputUtility.getKeyPressed(KeyCode.D)) {
				moveRight();
			}
			if (InputUtility.getKeyPressed(KeyCode.W)) {
				moveUp();
			}
			if (InputUtility.getKeyPressed(KeyCode.S)) {
				moveDown();
			}
			if (InputUtility.isLeftClickTriggered()) {
				this.x = InputUtility.mouseX;
				this.y = InputUtility.mouseY;
			}
		}
	}

	private void moveUp() {
		if(!isFreeze) {
			this.y -= MOVE_SPEED;
			this.direction = 0;
			System.out.println("move up");
		}
	}

	private void moveDown() {
		if(!isFreeze) {
			this.y += MOVE_SPEED;
			this.direction = 2;
			System.out.println("move down");
		}
	}

	private void moveLeft() {
		if(!isFreeze) {
			this.x -= MOVE_SPEED;
			this.direction = 3;
			System.out.println("move left");
		}
	}

	private void moveRight() {
		if(!isFreeze) {
			this.x += MOVE_SPEED;
			this.direction = 1;
			System.out.println("move right");
		}
	}
	
	public void freeze() {
		this.isFreeze = true;
	}
	
	public void unFreeze() {
		this.isFreeze = false;
	}
	
	public void teleportTo(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
