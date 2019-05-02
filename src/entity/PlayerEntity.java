package entity;

import input.InputUtility;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class PlayerEntity extends Entity
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private int direction;
	private static final int MOVE_SPEED = 5;
	private boolean isFreeze = false;
	private int frame = 1;
	private Thread thread;
	private double destinateX, destinateY, hypote;
	private boolean isInputed = false;

	public PlayerEntity(double x, double y, int direction)
	{
		super();
		// 0 = north, 1 = east, 2 = south, 3 = west
		this.direction = direction;
		this.sprite = RenderableHolder.playerSpriteSouth1;
		this.x = x;
		this.y = y;
		destinateX = x;
		destinateY = y;
		this.z = 9;
		

		thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(700);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Platform.runLater(() -> frame = 3 - frame);
				}
			}
		});
		thread.start();
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		switch (direction)
		{
			case 0:
				if (frame == 1)
				{
					this.sprite = RenderableHolder.playerSpriteNorth1;
				}
				else
				{
					this.sprite = RenderableHolder.playerSpriteNorth2;
				}
				break;
			case 1:
				if (frame == 1)
				{
					this.sprite = RenderableHolder.playerSpriteEast1;
				}
				else
				{
					this.sprite = RenderableHolder.playerSpriteEast2;
				}
				break;
			case 2:
				if (frame == 1)
				{
//				this.sprite = RenderableHolder.playerSpriteSouth1;
					this.sprite = RenderableHolder.emiliaE1;
				}
				else
				{
//				this.sprite = RenderableHolder.playerSpriteSouth2;
					this.sprite = RenderableHolder.emiliaE2;
				}
				break;
			case 3:
				if (frame == 1)
				{
//					this.sprite = RenderableHolder.playerSpriteWest1;
					this.sprite = RenderableHolder.emiliaW1;
				}
				else
				{
//					this.sprite = RenderableHolder.playerSpriteWest2;
					this.sprite = RenderableHolder.emiliaW2;
				}
				break;
		}
		gc.drawImage(this.sprite, this.x, this.y);
	}

	public void update()
	{
		isInputed = false;
		if (!isFreeze)
		{
			if (InputUtility.getKeyPressed(KeyCode.A))
			{
				moveLeft();
				isInputed = true;
			}
			if (InputUtility.getKeyPressed(KeyCode.D))
			{
				moveRight();
				isInputed = true;
			}
			if (InputUtility.getKeyPressed(KeyCode.W))
			{
				moveUp();
				isInputed = true;
			}
			if (InputUtility.getKeyPressed(KeyCode.S))
			{
				moveDown();
				isInputed = true;
			}
			if (InputUtility.isLeftClickTriggered())
			{
				destinateX = InputUtility.mouseX;
				destinateY = InputUtility.mouseY;			
			}
			if(isInputed)
			{
				destinateX = x;
				destinateY = y;
			}
			if(!isInputed && !(Math.abs(destinateX - x) < 5 && Math.abs(destinateY - y) < 5))
			{
				moveToMouse();
			}
			boundBlock();
		}
	}
	
	private void moveToMouse()
	{
//		if(destinateX == 0 && destinateY == 0) return;
		double delX = destinateX - x, delY = destinateY - y;
		hypote = Math.hypot(delX, delY);
		if(hypote == 0)
			return;
		x += MOVE_SPEED * delX / hypote;
		y += MOVE_SPEED * delY / hypote;
		if(Math.abs(delX) > Math.abs(delY))
		{
			if(delX > 0)
			{
				direction = 1;
			}
			else
			{
				direction = 3;
			}
		}
		else
		{
			if(delY > 0)
			{
				direction = 2;
			}
			else
			{
				direction = 0;
			}
		}
		System.out.println("move to destination");
	}
	private void moveUp()
	{
		if (!isFreeze)
		{
			this.y -= MOVE_SPEED;
			this.direction = 0;
			System.out.println("move up");
		}
	}

	private void moveDown()
	{
		if (!isFreeze)
		{
			this.y += MOVE_SPEED;
			this.direction = 2;
			System.out.println("move down");
		}
	}

	private void moveLeft()
	{
		if (!isFreeze)
		{
			this.x -= MOVE_SPEED;
			this.direction = 3;
			System.out.println("move left");
		}
	}

	private void moveRight()
	{
		if (!isFreeze)
		{
			this.x += MOVE_SPEED;
			this.direction = 1;
			System.out.println("move right");
		}
	}
	private void boundBlock()
	{
		if(y < 0)
		{
			y = 0;
		}
		if(x < 0)
		{
			x = 0;
		}
		if(x + sprite.getWidth() > WIDTH)
		{
			x = WIDTH - sprite.getWidth();
		}
		if(y + sprite.getHeight() > HEIGHT)
		{
			y = HEIGHT - sprite.getHeight();
		}
	}
	public void freeze()
	{
		this.isFreeze = true;
	}

	public void unFreeze()
	{
		this.isFreeze = false;
	}

	public void teleportTo(double x, double y)
	{
		this.x = x;
		this.y = y;
		destinateX = x;
		destinateY = y;
	}

}
