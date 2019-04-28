package logic.base;

import java.util.Random;

import entity.Entity;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public abstract class Character extends Entity
{
	protected String name;
	protected Image spriteAnimation;
	
	protected int level;
//	protected boolean isDead = false;
	protected int money = 0;
	
	public Character() {
		this.sprite = new Image(ClassLoader.getSystemResourceAsStream(this.name + "1.png"));
		this.spriteAnimation = new Image(ClassLoader.getSystemResourceAsStream(this.name + "2.png"));
	}
	
	
	public int getMoney()
	{
		return money;
	}
	public void setMoney(int money)
	{
		this.money = money;
	}
	protected int baseMinPhyAtk;
	protected int baseMaxPhyAtk;
	protected int baseMinMagAtk;
	protected int baseMaxMagAtk;
	protected int basePhyDef;
	protected int baseMagDef;
	protected int baseMaxHp;
	protected int baseMaxMp;
	protected int currentHp;
	protected int currentMp;
	
	protected int modMinPhyAtk = 0;
	protected int modMaxPhyAtk = 0;
	protected int modMinMagAtk = 0;
	protected int modMaxMagAtk = 0;
	protected int modPhyDef = 0;
	protected int modMagDef = 0;
	protected int modMaxHp = 0;
	protected int modMaxMp = 0;
	public void receiveDamage(int damage, StatType atkType)
	{
		if(atkType.equals(StatType.PHYATK))
		{
			damage = (damage < getPhyDef()) ? 0 : damage - getPhyDef();
		}
		else if (atkType.equals(StatType.MAGATK))
		{
			damage = (damage < getMagDef()) ? 0 : damage - getMagDef();
		}
		else
		{
			new CustomException("Fail Receive Damage").printStackTrace();
		}
		this.currentHp = (this.currentHp < damage) ? 0 : this.currentHp - damage;
	}
	public void receiveHeal(int heal)
	{
		currentHp += heal;
		currentHp = (currentHp < 0) ? 0 : (currentHp > baseMaxHp) ? baseMaxHp : currentHp;
	}
	public void receiveMana(int mana)
	{
		currentMp += mana;
		currentMp = (currentMp < 0) ? 0 : (currentMp > baseMaxMp) ? baseMaxMp : currentMp;
	}
	public void fullHeal()
	{
		currentHp = baseMaxHp;
		currentMp = baseMaxMp;
	}
	public int getMinPhyAtk()
	{
		return this.baseMinPhyAtk + this.modMinPhyAtk;
	}
	public int getMaxPhyAtk()
	{
		return this.baseMaxPhyAtk + this.modMaxPhyAtk;
	}
	public int getMinMagAtk()
	{
		return this.baseMinMagAtk + this.modMinMagAtk;
	}
	public int getMaxMagAtk()
	{
		return this.baseMaxMagAtk + this.modMaxMagAtk;
	}
	public int getPhyDef()
	{
		return this.basePhyDef + this.modPhyDef;
	}
	public int getMagDef()
	{
		return this.baseMagDef + this.modMagDef;
	}
	public int getMaxHp()
	{
		return this.baseMaxHp + this.modMaxHp;
	}
	public int getMaxMp()
	{
		return this.baseMaxMp + this.modMaxMp;
	}
	public int randPhyAtk()
	{
		Random rand = new Random();
		return rand.nextInt(this.baseMaxPhyAtk - this.baseMinPhyAtk) + this.baseMinPhyAtk;
	}
	public int randMagAtk()
	{
		Random rand = new Random();
		return rand.nextInt(this.baseMaxMagAtk - this.baseMinMagAtk) + this.baseMinMagAtk;
	}
	public void resetModStat()
	{
		modMinPhyAtk = 0;
		modMaxPhyAtk = 0;
		modMinMagAtk = 0;
		modMaxMagAtk = 0;
		modPhyDef = 0;
		modMagDef = 0;
		modMaxHp = 0;
		modMaxMp = 0;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public boolean isDead()
	{
		return currentHp <= 0;
	}
//	public void setDead(boolean isDead)
//	{
//		this.isDead = isDead;
//	}
	public int getBaseMinPhyAtk()
	{
		return baseMinPhyAtk;
	}
	public void setBaseMinPhyAtk(int baseMinPhyAtk)
	{
		this.baseMinPhyAtk = baseMinPhyAtk;
	}
	public int getBaseMaxPhyAtk()
	{
		return baseMaxPhyAtk;
	}
	public void setBaseMaxPhyAtk(int baseMaxPhyAtk)
	{
		this.baseMaxPhyAtk = baseMaxPhyAtk;
	}
	public int getBaseMinMagAtk()
	{
		return baseMinMagAtk;
	}
	public void setBaseMinMagAtk(int baseMinMagAtk)
	{
		this.baseMinMagAtk = baseMinMagAtk;
	}
	public int getBaseMaxMagAtk()
	{
		return baseMaxMagAtk;
	}
	public void setBaseMaxMagAtk(int baseMaxMagAtk)
	{
		this.baseMaxMagAtk = baseMaxMagAtk;
	}
	public int getBasePhyDef()
	{
		return basePhyDef;
	}
	public void setBasePhyDef(int basePhyDef)
	{
		this.basePhyDef = basePhyDef;
	}
	public int getBaseMagDef()
	{
		return baseMagDef;
	}
	public void setBaseMagDef(int baseMagDef)
	{
		this.baseMagDef = baseMagDef;
	}
	public int getBaseMaxHp()
	{
		return baseMaxHp;
	}
	public void setBaseMaxHp(int baseMaxHp)
	{
		this.baseMaxHp = baseMaxHp;
	}
	public int getBaseMaxMp()
	{
		return baseMaxMp;
	}
	public void setBaseMaxMp(int baseMaxMp)
	{
		this.baseMaxMp = baseMaxMp;
	}
	public int getCurrentHp()
	{
		return currentHp;
	}
	public void setCurrentHp(int currentHp)
	{
		this.currentHp = (currentHp < 0) ? 0 : currentHp;
	}
	public int getCurrentMp()
	{
		return currentMp;
	}
	public void setCurrentMp(int currentMp)
	{
		this.currentMp = (currentMp < 0) ? 0 : currentMp;
	}
	public int getModMinPhyAtk()
	{
		return modMinPhyAtk;
	}
	public void setModMinPhyAtk(int modMinPhyAtk)
	{
		this.modMinPhyAtk = modMinPhyAtk;
	}
	public int getModMaxPhyAtk()
	{
		return modMaxPhyAtk;
	}
	public void setModMaxPhyAtk(int modMaxPhyAtk)
	{
		this.modMaxPhyAtk = modMaxPhyAtk;
	}
	public int getModMinMagAtk()
	{
		return modMinMagAtk;
	}
	public void setModMinMagAtk(int modMinMagAtk)
	{
		this.modMinMagAtk = modMinMagAtk;
	}
	public int getModMaxMagAtk()
	{
		return modMaxMagAtk;
	}
	public void setModMaxMagAtk(int modMaxMagAtk)
	{
		this.modMaxMagAtk = modMaxMagAtk;
	}
	public int getModPhyDef()
	{
		return modPhyDef;
	}
	public void setModPhyDef(int modPhyDef)
	{
		this.modPhyDef = modPhyDef;
	}
	public int getModMagDef()
	{
		return modMagDef;
	}
	public void setModMagDef(int modMagDef)
	{
		this.modMagDef = modMagDef;
	}
	public int getModMaxHp()
	{
		return modMaxHp;
	}
	public void setModMaxHp(int modMaxHp)
	{
		this.modMaxHp = modMaxHp;
	}
	public int getModMaxMp()
	{
		return modMaxMp;
	}
	public void setModMaxMp(int modMaxMp)
	{
		this.modMaxMp = modMaxMp;
	}
	
}
