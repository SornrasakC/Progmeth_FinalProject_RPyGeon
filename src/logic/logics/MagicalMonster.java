package logic.logics;

import logic.base.Monster;
import logic.base.StatType;

public class MagicalMonster extends Monster
{

	public MagicalMonster(String name, int level, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk,
			int baseMaxMagAtk, int basePhyDef, int baseMagDef, int baseMaxHp, int baseMaxMp)
	{
		super(name, level, baseMinPhyAtk, baseMaxPhyAtk, baseMinMagAtk, baseMaxMagAtk, basePhyDef, baseMagDef,
				baseMaxHp, baseMaxMp);
	}

	@Override
	public int attack(Player player)
	{
		int damage;
		if(this.currentMp >= 5)
		{
			this.currentMp -= 5;
			damage = randMagAtk();
			player.receiveDamage(damage, StatType.MAGATK);
		}
		else
		{
			damage = randPhyAtk();
			player.receiveDamage(damage, StatType.PHYATK);
		}
		return damage;
	}

}
