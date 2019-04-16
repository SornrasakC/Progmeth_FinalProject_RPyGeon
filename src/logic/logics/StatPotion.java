package logic.logics;

import logic.base.Potion;
import logic.base.StatType;

public class StatPotion extends Potion
{
	private StatType statType;
	private int modStat;
	private int modMaxStat = 0;
	public StatPotion(String name, String description, int cost, StatType statType, int modStat)
	{
		super(name, description, cost);
		this.statType = statType;
		this.modStat = modStat;
	}
	public StatPotion(String name, String description, int cost, StatType statType, int modStat, int modMaxStat)
	{
		super(name, description, cost);
		this.statType = statType;
		this.modStat = modStat;
		this.modMaxStat = modMaxStat;
	}
	public StatType getStatType()
	{
		return statType;
	}
	public int getModStat()
	{
		return modStat;
	}
	public int getModMaxStat()
	{
		return modMaxStat;
	}
}
