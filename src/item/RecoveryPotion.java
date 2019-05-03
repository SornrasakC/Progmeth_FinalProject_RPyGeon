package item;

import java.util.Random;

import logic.base.CustomException;
import logic.base.LuckType;
import logic.base.Potion;
import logic.logics.Player;

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
//		this.luckType = LuckType.convert(luckType);
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
			Random rand = new Random();
			player.receiveHeal(rand.nextInt(player.getMaxHp() * hpRecoverPercent / 100));
			player.receiveMana(rand.nextInt(player.getMaxMp() * mpRecoverPercent / 100));
		}
		else if(luckType == LuckType.BADRANDOM)
		{
			Random rand = new Random();
			int hp = player.getMaxHp() * hpRecoverPercent / 100;
			int mp = player.getMaxMp() * mpRecoverPercent / 100;
			player.receiveHeal(rand.nextInt(hp * 2) - hp);
			player.receiveMana(rand.nextInt(mp * 2) - mp);
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
