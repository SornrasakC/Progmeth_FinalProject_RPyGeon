package magic;

import logic.base.CustomException;
import logic.base.LuckType;
import logic.base.Magic;
import logic.base.Monster;
import logic.logics.Player;
import logic.logics.Rand;

public class HealingMagic extends Magic
{
	

	public HealingMagic(String name, String description, int manaCost, int multiplier, String luckType, int level)
	{
		super(name, description, manaCost, multiplier, luckType, level);
	}

	public int use(Player player, Monster monster)
	{
		player.setCurrentMp(player.getCurrentMp() - this.manaCost);
		int heal = 0;
		int max = player.getMaxMagAtk() * multiplier;
		int min = player.getMinMagAtk() * multiplier;
		if(luckType == LuckType.NORMAL)
		{
			heal = Rand.randInclusive(min, max);
		}
		else if(luckType == LuckType.RANDOM)
		{
			heal = Rand.rand(max);
		}
		else
		{
			new CustomException("Fail Healing Magic").printStackTrace();
		}
		player.receiveHeal(heal);
		return heal;
	}
}
