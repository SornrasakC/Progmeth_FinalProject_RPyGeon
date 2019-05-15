package entity;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class PlayerBattleEntity extends Entity
{
	private boolean isFreeze = false;
	private int frame = 1;
	private Thread idleThread;
	private int animationType;
	private int isAnimating;
	
	
	public PlayerBattleEntity(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		this.z = 9;
		this.sprite = RenderableHolder.emiliaE1;
		this.animationType = 0; // 0 = idle, 1 = attack
		this.visible = false;
		
		
		idleThread = new Thread(new Runnable()
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
		idleThread.start();
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		switch(frame)
		{
			case(1):
				sprite = RenderableHolder.emiliaE1;
				break;
			case(2):
				sprite = RenderableHolder.emiliaE2;
				break;
		}
		gc.drawImage(sprite, this.x, this.y, 100, 100);
	}
	
	public void update()
	{
		if(!isFreeze)
		{
			
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
}
