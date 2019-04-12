package logic.base;

public abstract class Armour implements Cloneable
{
	protected String name;
	protected String description;
	protected int basePhyDef;
	protected int baseMagDef;
	public Armour(String name, String description, int basePhyDef, int baseMagDef)
	{
		super();
		this.name = name;
		this.description = description;
		this.basePhyDef = basePhyDef;
		this.baseMagDef = baseMagDef;
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
	
}
