package entity;

import javafx.scene.canvas.GraphicsContext;
import logic.base.Monster;
import logic.logics.Player;

public class  HpMpGaugeEntity extends Entity
{
	private boolean isFreeze;
	private logic.base.Character character;
	public HpMpGaugeEntity(int x, int y, Monster character)
	{
		super();
		this.x = x;
		this.y = y;
		this.character = character;
	}
	public HpMpGaugeEntity(int x, int y, Player character)
	{
		super();
		this.x = x;
		this.y = y;
		this.character = character;
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		// TODO Auto-generated method stub
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
