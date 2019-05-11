package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import logic.base.Monster;
import logic.logics.Player;
import sharedObject.BattleRenderableHolder;

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
		gc.setStroke(Color.GHOSTWHITE);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setFont(new Font("Helvetica", 40));
		if(character instanceof Player)
		{
			gc.setFill(Color.NAVAJOWHITE);
			gc.drawImage(BattleRenderableHolder.gaugeBackground, x - 10, y - 80);
			gc.fillText(character.getName(), x + 10, y - 40);
		}
		else
		{
			gc.setFill(Color.ORANGERED);
			gc.drawImage(BattleRenderableHolder.gaugeBackground, x - 440, y - 80);
			gc.fillText(character.getName(), x - (character.getName().length() * 22), y - 40);
		}
		
		gc.setLineWidth(30);
		gc.setStroke(Color.RED);
		if(displayHP != character.getCurrentHp())
		{
			if(character instanceof Player)
			{
				gc.strokeLine(x, y, x + 400 * (displayHP / 100.0), y);
			}
			if(character instanceof Monster)
			{
				gc.strokeLine(x - 400 * (displayHP / 100.0), y, x, y);
			}
		}
		gc.setStroke(Color.BLUE);
		if(displayMP != character.getCurrentMp() && character.getBaseMaxMp() != 0)
		{
			if(character instanceof Player)
			{
				gc.strokeLine(x, y + 30, x + 300 * (displayMP / 100.0), y + 30);
			}
			if(character instanceof Monster)
			{
				gc.strokeLine(x - 300 * (displayMP / 100.0), y + 30, x, y + 30);
			}
		}
		gc.setFont(Font.getDefault());
		gc.setStroke(Color.GHOSTWHITE);
		gc.setLineWidth(2);
		int rawDisplayHp = (int) ((displayHP / 100.0) * character.getMaxHp());
		int rawDisplayMp = (int) ((displayMP / 100.0) * character.getMaxMp());
		String HpString = rawDisplayHp + " / " + character.getMaxHp();
		String MpString = rawDisplayMp + " / " + character.getMaxMp();
		int surplusHp = (character instanceof Monster) ? HpString.length() * 5 : 0, surplusMp = (character instanceof Monster) ? MpString.length() * 5 : 0;
		gc.strokeText(HpString, x - surplusHp, y);
		gc.strokeText(MpString, x - surplusMp, y + 30);
		
	}
	public void update()
	{
		int currentHP = (int) (100.0 * character.getCurrentHp()) / character.getBaseMaxHp();
		int currentMP = (character.getBaseMaxMp() == 0) ? 0 : (int) (100.0 * character.getCurrentMp()) / character.getBaseMaxMp();
		if(!isFreeze)
		{
			int signHp = (displayHP <= currentHP) ? 1 : -1;
			int signMp = (displayMP <= currentMP) ? 1 : -1;
			displayHP = (Math.abs(displayHP - currentHP) < SPEED) ? currentHP : displayHP + signHp * SPEED;
			displayMP = (Math.abs(displayMP - currentMP) < SPEED) ? currentMP : displayMP + signMp * SPEED;
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
		displayHP = (int) (100.0 * character.getCurrentHp()) / character.getBaseMaxHp();
		displayMP = (character.getBaseMaxMp() == 0) ? 0 : (int) (100.0 * character.getCurrentMp()) / character.getBaseMaxMp();
		BattleEntityLogic.getPlayerGauge().setDisplayHP((int) (100.0 * Player.player.getCurrentHp()) / Player.player.getBaseMaxHp());
		BattleEntityLogic.getPlayerGauge().setDisplayMP((int) (100.0 * Player.player.getCurrentMp()) / Player.player.getBaseMaxMp());
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
