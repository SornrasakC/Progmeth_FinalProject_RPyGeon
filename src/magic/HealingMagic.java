package magic;

import logic.base.CustomException;
import logic.base.LuckType;
import logic.base.Magic;
import logic.base.Monster;
import logic.logics.Player;

public class HealingMagic extends Magic
{
	

	public HealingMagic(String name, String description, int manaCost, int multiplier, String luckType, int level)
	{
		super(name, description, manaCost, multiplier, luckType, level);
	}

	public int use(Player player, Monster monster) throws CustomException
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
			throw new CustomException("Fail Healing Magic");
		}
		player.receiveHeal(heal);
		return heal;
	}
}
