package logic.base;

import logic.logics.Player;

public abstract class Potion implements Cloneable
{
	protected String name;
	protected String description;
	
	protected int cost;

	public Potion(String name, String description, int cost)
	{
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	public abstract void usePotion(Player player);

	@Override
	public int hashCode()
	{
		return name.hashCode() + description.hashCode();
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getCost()
	{
		return cost;
	}
	
}
