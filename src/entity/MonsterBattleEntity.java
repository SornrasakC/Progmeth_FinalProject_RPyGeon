package entity;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.base.Monster;
import logic.logics.Dungeon;
import scene.Battle;
import sharedObject.RenderableHolder;

public class MonsterBattleEntity extends Entity
{
	private Monster monster;
	private boolean isFreeze = false;
	private int frame = 1;
	private Thread idleThread;

	
	public MonsterBattleEntity(int x, int y, Monster monster)
	{
		super();
		this.monster = Battle.getMonster();
		this.x = x;
		this.y = y;
		this.z = 8;
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
				this.sprite = monster.getSprite();
				break;
				
			case(2):
				this.sprite = monster.getSpriteAnimation();
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

	public Monster getMonster()
	{
		return monster;
	}

	public void setMonster(Monster monster)
	{
		this.monster = monster;
	}

	public boolean isFreeze()
	{
		return isFreeze;
	}

	public void setFreeze(boolean isFreeze)
	{
		this.isFreeze = isFreeze;
	}
	
}
