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
	
	protected Random rand = new Random();
	
	
	public Magic(String name, String description, int manaCost, int multiplier, LuckType luckType)
	{
		super();
		this.name = name;
		this.description = description;
		this.manaCost = manaCost;
		this.multiplier = multiplier;
		this.luckType = luckType;
	}

	@Override
	public int hashCode()
	{
		return name.hashCode() + description.hashCode();
	}
	
	public boolean canUse(Player player)
	{
		return player.getCurrentMp() >= this.manaCost;
	}
	public abstract int use(Player player, Monster monster);
}
