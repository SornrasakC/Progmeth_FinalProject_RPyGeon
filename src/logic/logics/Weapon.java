package logic.logics;

public class Weapon
{
	private String name;
	private String description;
	private int baseMinPhyAtk;
	private int baseMaxPhyAtk;
	
	private int baseMinMagAtk;
	private int baseMaxMagAtk;
	public Weapon(String name, String description, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk,
			int baseMaxMagAtk)
	{
		super();
		this.name = name;
		this.description = description; //No description yet
		this.baseMinPhyAtk = baseMinPhyAtk;
		this.baseMaxPhyAtk = baseMaxPhyAtk;
		this.baseMinMagAtk = baseMinMagAtk;
		this.baseMaxMagAtk = baseMaxMagAtk;
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public int getBaseMinPhyAtk()
	{
		return baseMinPhyAtk;
	}
	public int getBaseMaxPhyAtk()
	{
		return baseMaxPhyAtk;
	}
	public int getBaseMinMagAtk()
	{
		return baseMinMagAtk;
	}
	public int getBaseMaxMagAtk()
	{
		return baseMaxMagAtk;
	}
	
}
