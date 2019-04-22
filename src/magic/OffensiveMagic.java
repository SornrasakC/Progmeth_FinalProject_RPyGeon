package magic;

import logic.base.CustomException;
import logic.base.LuckType;
import logic.base.Magic;
import logic.base.Monster;
import logic.base.StatType;
import logic.logics.Player;

public class OffensiveMagic extends Magic
{
	public OffensiveMagic(String name, String description, int manaCost, int multiplier, String luckType, int level)
	{
		super(name, description, manaCost, multiplier, luckType, level);
	}
	public int use(Player player, Monster monster) throws CustomException // ALWAYS confirm canUse() before using
	{
		player.setCurrentMp(player.getCurrentMp() - this.manaCost);
		int damage;
		int max = player.getMaxMagAtk() * multiplier;
		int min = player.getMinMagAtk() * multiplier;
		if(luckType == LuckType.NORMAL)
		{
			damage = min + rand.nextInt(max - min);
		}
		else if(luckType == LuckType.RANDOM)
		{
			damage = rand.nextInt(max);
		}
		else
		{
			throw new CustomException("Fail Offensive Magic");
		}
		monster.receiveDamage(damage, StatType.MAGATK);
		return damage;
	}
	public int getMultiplier()
	{
		return multiplier;
	}
	
}
