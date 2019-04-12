package logic.logics;

import logic.base.LuckType;
import logic.base.Magic;

public class HealingMagic extends Magic
{
	private int multiplier;
	private LuckType luckType;
	
	public HealingMagic(String name, String description, int manaCost, int multiplier, LuckType luckType)
	{
		super(name, description, manaCost);
		this.multiplier = multiplier;
		this.luckType = luckType;
	}
	public int getMultiplier()
	{
		return multiplier;
	}
	public LuckType getLuckType()
	{
		return luckType;
	}
}
