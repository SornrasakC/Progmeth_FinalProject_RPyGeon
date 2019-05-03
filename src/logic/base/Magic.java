package logic.base;

import logic.logics.Player;

public abstract class Magic implements Cloneable
{
	protected String name;
	protected String description;
	protected int manaCost;
	protected int multiplier;
	protected LuckType luckType;
	protected int level;
	protected String tempLuckType;
	
	
	public Magic(String name, String description, int manaCost, int multiplier, String tempLuckType, int level)
	{
		super();
		this.name = name;
		this.description = description;
		this.manaCost = manaCost;
		this.multiplier = multiplier;
		this.tempLuckType = tempLuckType;
		this.level = level;
	}
	
	public String toString()
	{
		return name + " : " + description; 
	}
	@Override
	public int hashCode()
	{
		return (name + description).hashCode();
	}
	public void initLuckType()
	{
		this.luckType = LuckType.convert(tempLuckType);
	}
	
	public boolean canUse(Player player)
	{
		return player.getCurrentMp() >= this.manaCost;
	}
	public abstract int use(Player player, Monster monster);

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getManaCost()
	{
		return manaCost;
	}

	public int getMultiplier()
	{
		return multiplier;
	}

	public LuckType getLuckType()
	{
		return luckType;
	}

	public int getLevel()
	{
		return level;
	}
	
	
}
