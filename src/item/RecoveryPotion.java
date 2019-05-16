package item;

import logic.base.CustomException;
import logic.base.LuckType;
import logic.base.Potion;
import logic.logics.Player;
import logic.logics.Rand;

public class RecoveryPotion extends Potion
{
	private int hpRecoverPercent;
	private int mpRecoverPercent;
	private LuckType luckType;
	private String tempLuckType;
	public RecoveryPotion(String name, String description, int cost, int hpRecoverPercent, int mpRecoverPercent,
			String tempLuckType)
	{
		super(name, description, cost);
		this.hpRecoverPercent = hpRecoverPercent;
		this.mpRecoverPercent = mpRecoverPercent;
		this.tempLuckType = tempLuckType;
	}
	public void usePotion(Player player) //Can use potion that's not in the inventory
	{
		if(luckType == LuckType.NORMAL)
		{
			player.receiveHeal(player.getMaxHp() * hpRecoverPercent / 100);
			player.receiveMana(player.getMaxMp() * mpRecoverPercent / 100);
		}
		else if(luckType == LuckType.RANDOM)
		{
			player.receiveHeal(Rand.rand(player.getMaxHp() * hpRecoverPercent / 100));
			player.receiveMana(Rand.rand(player.getMaxMp() * mpRecoverPercent / 100));
		}
		else if(luckType == LuckType.BADRANDOM)
		{
			int hp = player.getMaxHp() * hpRecoverPercent / 100;
			int mp = player.getMaxMp() * mpRecoverPercent / 100;
			player.receiveHeal(Rand.randInclusive(-hp, hp));
			player.receiveMana(Rand.randInclusive(-mp, mp));
		}
		else
		{
			new CustomException("Fail Recovery Potion").printStackTrace();
		}
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
	@Override
	public void init()
	{
		this.luckType = LuckType.convert(tempLuckType);		
	}
	
}
