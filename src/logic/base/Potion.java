package logic.base;

import logic.logics.Player;

public abstract class Potion implements Cloneable
{
	protected String name;
	protected String description;
	
	protected int cost;
	protected int priority;

	public Potion(String name, String description, int cost)
	{
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	public abstract void usePotion(Player player);
	public abstract void init();
	
	public String toString()
	{
		if(name.equals("Low Quality Super Duper Lucky Randomly Recovering Potion") || name.equals("High Quality Super Duper Lucky Randomly Recovering Potion"))
			return name;
		return name + " {" + description + "}";
	}
	@Override
	public int hashCode()
	{
		return (name + description).hashCode();
	}
	
	@Override
	public boolean equals(Object arg0)
	{
		if(arg0 == null)
			return false;
		Potion potion = (Potion) arg0;
		return name.equals(potion.getName());
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
	public int getPriority()
	{
		return priority;
	}
	
	
}
