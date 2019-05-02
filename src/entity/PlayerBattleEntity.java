package entity;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class PlayerBattleEntity extends Entity
{
	private boolean isFreeze = false;
	private int frame = 1;
	private Thread idleThread;
	
	
	public PlayerBattleEntity(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		this.z = 9;
		this.sprite = RenderableHolder.emiliaE1;
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
		gc.drawImage(sprite, this.x, this.y);
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
