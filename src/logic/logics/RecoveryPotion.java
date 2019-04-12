package logic.logics;

import logic.base.LuckType;
import logic.base.Potion;

public class RecoveryPotion extends Potion
{
	private int hpRecoverPercent;
	private int mpRecoverPercent;
	private LuckType luckType;
	public RecoveryPotion(String name, String description, int cost, int hpRecoverPercent, int mpRecoverPercent,
			LuckType luckType)
	{
		super(name, description, cost);
		this.hpRecoverPercent = hpRecoverPercent;
		this.mpRecoverPercent = mpRecoverPercent;
		this.luckType = luckType;
	}
	public int getHpRecoverPercent()
	{
		return hpRecoverPercent;
	}
	public int getMpRecoverPercent()
	{
		return mpRecoverPercent;
	}
	public LuckType getLuckType()
	{
		return luckType;
	}
	
}
