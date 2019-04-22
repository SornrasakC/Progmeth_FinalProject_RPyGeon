package logic.logics;

import java.util.ArrayList;
import java.util.HashMap;

import item.ChestArmour;
import item.PantsArmour;
import item.ShoesArmour;
import item.Weapon;
import logic.base.Character;
import logic.base.Magic;
import logic.base.Potion;

public class Player extends Character
{
	private String battleClass = "jobless";

	private int exp = 0;
	private int expToNextLevel = 100;
	private int money = 0;

	private Weapon startingWeapon = new Weapon("Wooden Stick", "Normal woody stick", 1, 1, 0, 0, 1);
	private PantsArmour startingHeadArmour = new PantsArmour("Normal Cotton T-Shirt", "This is T-Shirt", 0, 0, 1);
	private ChestArmour startingChestArmour = new ChestArmour("Generic Pants", "This is pants", 0, 0, 1);
	private ShoesArmour startingLegArmour = new ShoesArmour("Normal Shoes", "This is shoes", 0, 0, 1);

	private Weapon equipedWeapon = startingWeapon;
	private PantsArmour equipedHeadArmour = startingHeadArmour;
	private ChestArmour equipedChestArmour = startingChestArmour;
	private ShoesArmour equipedLegArmour = startingLegArmour;

	private ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
	private ArrayList<PantsArmour> headArmourInventory = new ArrayList<PantsArmour>();
	private ArrayList<ChestArmour> chestArmourInventory = new ArrayList<ChestArmour>();
	private ArrayList<ShoesArmour> legArmourInventory = new ArrayList<ShoesArmour>();

	private HashMap<Potion, Integer> potionInventory = new HashMap<Potion, Integer>();
	private ArrayList<Magic> magicInventory = new ArrayList<Magic>();

	public Player(String name)
	{
		super();
		this.name = name;
		this.level = 1;

		this.baseMaxPhyAtk = 2;
		this.baseMinPhyAtk = 1;
		this.baseMaxMagAtk = 2;
		this.baseMinMagAtk = 1;
		this.basePhyDef = 0;
		this.baseMagDef = 0;
		this.baseMaxHp = 10;
		this.baseMaxMp = 5;

		this.modMaxPhyAtk = 1;
		this.modMinPhyAtk = 1;
		fullHeal();
	}
	
//	public void gain()

	public void gainMoney(int money)
	{
		if (money < 0) return;
		this.money += money;
	}

	public int getExp()
	{
		return exp;
	}

	public void setExp(int exp)
	{
		this.exp = exp;
	}

	public int getExpToNextLevel()
	{
		return expToNextLevel;
	}

	public void setExpToNextLevel(int expToNextLevel)
	{
		this.expToNextLevel = expToNextLevel;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public String getBattleClass()
	{
		return battleClass;
	}

	public void setBattleClass(String battleClass)
	{
		this.battleClass = battleClass;
	}

	public Weapon getStartingWeapon()
	{
		return startingWeapon;
	}

	public void setStartingWeapon(Weapon startingWeapon)
	{
		this.startingWeapon = startingWeapon;
	}

	public PantsArmour getStartingHeadArmour()
	{
		return startingHeadArmour;
	}

	public void setStartingHeadArmour(PantsArmour startingHeadArmour)
	{
		this.startingHeadArmour = startingHeadArmour;
	}

	public ChestArmour getStartingChestArmour()
	{
		return startingChestArmour;
	}

	public void setStartingChestArmour(ChestArmour startingChestArmour)
	{
		this.startingChestArmour = startingChestArmour;
	}

	public ShoesArmour getStartingLegArmour()
	{
		return startingLegArmour;
	}

	public void setStartingLegArmour(ShoesArmour startingLegArmour)
	{
		this.startingLegArmour = startingLegArmour;
	}

	public Weapon getEquipedWeapon()
	{
		return equipedWeapon;
	}

	public void setEquipedWeapon(Weapon equipedWeapon)
	{
		this.equipedWeapon = equipedWeapon;
	}

	public PantsArmour getEquipedHeadArmour()
	{
		return equipedHeadArmour;
	}

	public void setEquipedHeadArmour(PantsArmour equipedHeadArmour)
	{
		this.equipedHeadArmour = equipedHeadArmour;
	}

	public ChestArmour getEquipedChestArmour()
	{
		return equipedChestArmour;
	}

	public void setEquipedChestArmour(ChestArmour equipedChestArmour)
	{
		this.equipedChestArmour = equipedChestArmour;
	}

	public ShoesArmour getEquipedLegArmour()
	{
		return equipedLegArmour;
	}

	public void setEquipedLegArmour(ShoesArmour equipedLegArmour)
	{
		this.equipedLegArmour = equipedLegArmour;
	}

	public ArrayList<Weapon> getWeaponInventory()
	{
		return weaponInventory;
	}

	public void setWeaponInventory(ArrayList<Weapon> weaponInventory)
	{
		this.weaponInventory = weaponInventory;
	}

	public ArrayList<PantsArmour> getHeadArmourInventory()
	{
		return headArmourInventory;
	}

	public void setHeadArmourInventory(ArrayList<PantsArmour> headArmourInventory)
	{
		this.headArmourInventory = headArmourInventory;
	}

	public ArrayList<ChestArmour> getChestArmourInventory()
	{
		return chestArmourInventory;
	}

	public void setChestArmourInventory(ArrayList<ChestArmour> chestArmourInventory)
	{
		this.chestArmourInventory = chestArmourInventory;
	}

	public ArrayList<ShoesArmour> getLegArmourInventory()
	{
		return legArmourInventory;
	}

	public void setLegArmourInventory(ArrayList<ShoesArmour> legArmourInventory)
	{
		this.legArmourInventory = legArmourInventory;
	}

	public HashMap<Potion, Integer> getPotionInventory()
	{
		return potionInventory;
	}

	public void setPotionInventory(HashMap<Potion, Integer> potionInventory)
	{
		this.potionInventory = potionInventory;
	}

	public ArrayList<Magic> getMagicInventory()
	{
		return magicInventory;
	}

	public void setMagicInventory(ArrayList<Magic> magicInventory)
	{
		this.magicInventory = magicInventory;
	}

}
