package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class PlayerBattleEntity extends Entity
{
	private boolean isFreeze = false;
	private int frame = 1;
	private Thread thread;
	private Image sprite = RenderableHolder.emilia1;
	
	public PlayerBattleEntity()
	{
		super();
		
	}

	@Override
	public void draw(GraphicsContext gc)
	{
		
	}
	
}
