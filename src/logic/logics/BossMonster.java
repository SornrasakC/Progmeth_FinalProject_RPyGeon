package logic.logics;

import logic.base.Monster;
import logic.base.StatType;

public class BossMonster extends Monster
{

	public BossMonster(String name, int level, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk,
			int baseMaxMagAtk, int basePhyDef, int baseMagDef, int baseMaxHp, int baseMaxMp)
	{
		super(name, level, baseMinPhyAtk, baseMaxPhyAtk, baseMinMagAtk, baseMaxMagAtk, basePhyDef, baseMagDef,
				baseMaxHp, baseMaxMp);
	}

	@Override
	public int attack(Player player)
	{
		int phyDamage = randPhyAtk(), magDamage = randMagAtk();
		if((this.currentMp >= 5) && (magDamage - player.getMagDef() > phyDamage - player.getPhyDef()))
		{
			this.currentMp -= 5;
			player.receiveDamage(magDamage, StatType.MAGATK);
			return magDamage;
		}
		player.receiveDamage(phyDamage, StatType.PHYATK);
		return phyDamage;
	}
}
