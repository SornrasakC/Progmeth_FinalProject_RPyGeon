package logic.base;

public abstract class Armour implements Cloneable, Droppable
{
	protected String name;
	protected String description;
	protected int basePhyDef;
	protected int baseMagDef;
	protected int floor;
	public Armour(String name, String description, int basePhyDef, int baseMagDef, int floor)
	{
		super();
		this.name = name;
		this.description = description;
		this.basePhyDef = basePhyDef;
		this.baseMagDef = baseMagDef;
		this.floor = floor;
	}
//	@Override
//	protected Object clone() throws CloneNotSupportedException
//	{
//		return super.clone();
//	}
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
	public int getBasePhyDef()
	{
		return basePhyDef;
	}
	public int getBaseMagDef()
	{
		return baseMagDef;
	}
	public int getFloor()
	{
		return floor;
	}
	
}
