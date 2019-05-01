package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class MonsterBattleEntity extends Entity
{
	private boolean isFreeze = false;
	private int frame = 1;
	private Thread thread;
	private Image sprite = RenderableHolder.emilia1;
	
	public MonsterBattleEntity()
	{
		super();
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		
	}
	
}
