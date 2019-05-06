package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.base.Monster;
import logic.logics.Player;

public class  HpMpGaugeEntity extends Entity
{
	private boolean isFreeze = false;
	private logic.base.Character character;
	private int displayHP, displayMP;
	private static final int SPEED = 2;
	public HpMpGaugeEntity(int x, int y, Monster character)
	{
		super();
		this.x = x;
		this.y = y;
		this.character = character;
		displayHP = (int) (100.0 * character.getCurrentHp()) / character.getBaseMaxHp();
		displayMP = (character.getBaseMaxMp() == 0) ? 0 : (int) (100.0 * character.getCurrentMp()) / character.getBaseMaxMp();

	}
	public HpMpGaugeEntity(int x, int y, Player character)
	{
		super();
		this.x = x;
		this.y = y;
		this.character = character;
		displayHP = (int) (100.0 * character.getCurrentHp()) / character.getBaseMaxHp();
		displayMP = (character.getBaseMaxMp() == 0) ? 0 : (int) (100.0 * character.getCurrentMp()) / character.getBaseMaxMp();
	}
	
	@Override
	public void draw(GraphicsContext gc)
	{
		gc.setLineWidth(30);
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.RED);
		if(displayHP != character.getCurrentHp())
		{
			gc.strokeLine(x, y, x + 200 * (displayHP / 100.0), y);
		}
		
//		System.out.println(character.getName() + " HP : " + character.getCurrentHp());
//		System.out.println(displayHP);
	}
	public void update()
	{
		int currentHP = (int) (100.0 * character.getCurrentHp()) / character.getBaseMaxHp();
		int currentMP = (character.getBaseMaxMp() == 0) ? 0 : (int) (100.0 * character.getCurrentMp()) / character.getBaseMaxMp();
		if(!isFreeze)
		{
			int sign = (displayHP < currentHP) ? 1 : -1;
			displayHP = (displayHP + sign * SPEED - currentHP <= SPEED) ? currentHP : displayHP + sign * SPEED;
			displayMP = (displayMP + sign * SPEED - currentMP <= SPEED) ? currentMP : displayMP + sign * SPEED;
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
	public boolean isFreeze()
	{
		return isFreeze;
	}
	public void setFreeze(boolean isFreeze)
	{
		this.isFreeze = isFreeze;
	}
	public logic.base.Character getCharacter()
	{
		return character;
	}
	public void setCharacter(logic.base.Character character)
	{
		this.character = character;
	}
	public int getDisplayHP()
	{
		return displayHP;
	}
	public void setDisplayHP(int displayHP)
	{
		this.displayHP = displayHP;
	}
	public int getDisplayMP()
	{
		return displayMP;
	}
	public void setDisplayMP(int displayMP)
	{
		this.displayMP = displayMP;
	}
	
}
