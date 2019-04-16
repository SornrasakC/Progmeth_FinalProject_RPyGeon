package logic.logics;

import logic.base.FalseConstructionError;
import logic.base.LuckType;
import logic.base.Magic;
import logic.base.Monster;

public class OffensiveMagic extends Magic
{
	public OffensiveMagic(String name, String description, int manaCost, int multiplier, String luckType)
	{
		super(name, description, manaCost, multiplier, luckType);
	}
	public int use(Player player, Monster monster) throws FalseConstructionError // ALWAYS confirm canUse() before using
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
			throw new FalseConstructionError("Fail Offensive Magic");
		}
		monster.receiveDamage(damage);
		return damage;
	}
	public int getMultiplier()
	{
		return multiplier;
	}
	
}
