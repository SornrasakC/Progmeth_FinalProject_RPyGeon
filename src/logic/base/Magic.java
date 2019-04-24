package logic.base;

import java.util.Random;

import logic.logics.Player;

public abstract class Magic implements Cloneable
{
	protected String name;
	protected String description;
	protected int manaCost;
	protected int multiplier;
	protected LuckType luckType;
	protected int level;
	
	protected Random rand = new Random();
	
	
	public Magic(String name, String description, int manaCost, int multiplier, String luckType, int level)
	{
		super();
		this.name = name;
		this.description = description;
		this.manaCost = manaCost;
		this.multiplier = multiplier;
		this.luckType = LuckType.convert(luckType);
		this.level = level;
	}
	
	public String toString()
	{
		return this.name + " level : " + level; 
	}
	@Override
	public int hashCode()
	{
		return (name + description).hashCode();
	}
	
	
	public boolean canUse(Player player)
	{
		return player.getCurrentMp() >= this.manaCost;
	}
	public abstract int use(Player player, Monster monster) throws CustomException;

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
