package logic.base;

public abstract class Magic implements Cloneable
{
	protected String name;
	protected String description;
	protected int manaCost;
	@Override
	public int hashCode()
	{
		return name.hashCode() + description.hashCode();
	}
	public Magic(String name, String description, int manaCost)
	{
		super();
		this.name = name;
		this.description = description;
		this.manaCost = manaCost;
	}
	
}
