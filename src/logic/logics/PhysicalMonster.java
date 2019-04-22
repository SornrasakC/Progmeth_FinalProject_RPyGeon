package logic.logics;

import logic.base.CustomException;
import logic.base.Monster;
import logic.base.StatType;

public class PhysicalMonster extends Monster
{

	public PhysicalMonster(String name, int level, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk,
			int baseMaxMagAtk, int basePhyDef, int baseMagDef, int baseMaxHp, int baseMaxMp)
	{
		super(name, level, baseMinPhyAtk, baseMaxPhyAtk, baseMinMagAtk, baseMaxMagAtk, basePhyDef, baseMagDef, baseMaxHp,
				baseMaxMp);
		
	}

	@Override
	public int attack(Player player) throws CustomException
	{
		int damage = randPhyAtk();
		player.receiveDamage(damage, StatType.PHYATK);
		return damage;
	}

}
