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

	public String toString()
	{
		return name + " des: " + description;
	}
	@Override
	public int hashCode()
	{
		return (name + description).hashCode();
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
	@Override
	public Potion clone() throws CloneNotSupportedException
	{
		return (Potion) super.clone();
	}
	
	
}
