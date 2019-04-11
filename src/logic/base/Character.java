package logic.base;

public abstract class Character
{
	protected String name;
	
	protected int level;
	
	protected int baseMinPhyAtk;
	protected int baseMaxPhyAtk;
	protected int baseMinMagAtk;
	protected int baseMaxMagAtk;
	protected int basePhyDef;
	protected int baseMagDef;
	protected int baseMaxHp;
	protected int baseMaxMp;
	protected int currentHp;
	protected int currentMP;
	
	protected int modMinPhyAtk = 0;
	protected int modMaxPhyAtk = 0;
	protected int modMinMagAtk = 0;
	protected int modMaxMagAtk = 0;
	protected int modPhyDef = 0;
	protected int modMagDef = 0;
	protected int modMaxHp = 0;
	protected int modMaxMp = 0;
	
}
