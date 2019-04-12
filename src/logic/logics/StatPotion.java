package logic.logics;

import logic.base.LuckType;
import logic.base.Potion;
import logic.base.StatType;

public class StatPotion extends Potion
{
	private StatType statType;
	private int modStat;
	private LuckType lucktype;
	public StatPotion(String name, String description, int cost, StatType statType, int modStat, LuckType lucktype)
	{
		super(name, description, cost);
		this.statType = statType;
		this.modStat = modStat;
		this.lucktype = lucktype;
	}
	public StatType getStatType()
	{
		return statType;
	}
	public int getModStat()
	{
		return modStat;
	}
	public LuckType getLucktype()
	{
		return lucktype;
	}
	
}
