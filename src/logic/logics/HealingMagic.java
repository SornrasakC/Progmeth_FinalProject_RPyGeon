package logic.logics;

import logic.base.LuckType;
import logic.base.Magic;
import logic.base.Monster;

public class HealingMagic extends Magic
{
	

	public HealingMagic(String name, String description, int manaCost, int multiplier, LuckType luckType)
	{
		super(name, description, manaCost, multiplier, luckType);
	}

	public int use(Player player, Monster monster)
	{
		player.setCurrentMp(player.getCurrentMp() - this.manaCost);
		int heal;
		int max = player.getMaxMagAtk() * multiplier;
		int min = player.getMinMagAtk() * multiplier;
		if(luckType == LuckType.NORMAL)
		{
			heal = min + rand.nextInt(max - min);
		}
		else if(luckType == LuckType.RANDOM)
		{
			heal = rand.nextInt(max);
		}
		else
		{
			heal = 0;
		}
		player.receiveHeal(heal);
		return heal;
	}
}