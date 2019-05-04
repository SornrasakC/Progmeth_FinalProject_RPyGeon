package item;

import logic.base.Potion;
import logic.base.StatType;
import logic.logics.Player;

public class StatPotion extends Potion
{
	private StatType statType;
	private int modStat;
	private int modMaxStat = 0;
	private String tempStatType;
	

	public StatPotion(String name, String description, int cost, String tempStatType, int modStat)
	{
		super(name, description, cost);
//		this.statType = StatType.convert(statType);
		this.tempStatType = tempStatType;
		this.modStat = modStat;
	}

	public StatPotion(String name, String description, int cost, String statType, int modStat, int modMaxStat)
	{
		super(name, description, cost);
		this.statType = StatType.convert(statType);
		this.modStat = modStat;
		this.modMaxStat = modMaxStat;
	}

	@Override
	public void usePotion(Player player)
	{
		switch (statType)
		{
			case PHYATK:
				player.setModMinPhyAtk(player.getModMinPhyAtk() + modStat);
				player.setModMaxPhyAtk(player.getModMaxPhyAtk() + modMaxStat);
				
				player.setBoostMinPhyAtk(player.getBoostMinPhyAtk() + modStat);
				player.setBoostMaxPhyAtk(player.getBoostMaxPhyAtk() + modMaxStat);
				break;
			case MAGATK:
				player.setModMinMagAtk(player.getModMinMagAtk() + modStat);
				player.setModMaxMagAtk(player.getModMaxMagAtk() + modMaxStat);
				
				player.setBoostMinMagAtk(player.getBoostMinMagAtk() + modStat);
				player.setBoostMaxMagAtk(player.getBoostMaxMagAtk() + modMaxStat);
				break;
			case PHYDEF:
				player.setModPhyDef(player.getModPhyDef() + modStat);
				player.setBoostPhyDef(player.getBoostPhyDef() + modStat);
				break;
			case MAGDEF:
				player.setModMagDef(player.getModMagDef() + modStat);
				player.setBoostMagDef(player.getBoostMagDef() + modStat);
				break;
		}

	}

	public StatType getStatType()
	{
		return statType;
	}

	public int getModStat()
	{
		return modStat;
	}

	public int getModMaxStat()
	{
		return modMaxStat;
	}

	@Override
	public void init()
	{
		this.statType = StatType.convert(tempStatType);
		
	}

}
