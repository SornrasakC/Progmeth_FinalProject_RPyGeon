package logic.logics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import item.ChestArmour;
import item.PantsArmour;
import item.ShoesArmour;
import item.Weapon;
import logic.base.Character;
import logic.base.Magic;
import logic.base.Potion;
import magic.HealingMagic;
import magic.OffensiveMagic;

public class Player extends Character
{
	private String battleClass = "jobless";

	private int exp = 0;
	private int expToNextLevel = 100;
	private int money = 0;

	private Weapon startingWeapon = new Weapon("Wooden Stick", "Normal woody stick", 1, 1, 0, 0, 1);
	private PantsArmour startingPantsArmour = new PantsArmour("Generic Pants", "This is pants", 0, 0, 1);
	private ChestArmour startingChestArmour = new ChestArmour("Normal Cotton T-Shirt", "This is T-shirt", 0, 0, 1);
	private ShoesArmour startingShoesArmour = new ShoesArmour("Normal Shoes", "This is shoes", 0, 0, 1);

	private Weapon equipedWeapon = startingWeapon;
	private PantsArmour equipedPantsArmour = startingPantsArmour;
	private ChestArmour equipedChestArmour = startingChestArmour;
	private ShoesArmour equipedShoesArmour = startingShoesArmour;

	private ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
	private ArrayList<PantsArmour> pantsArmourInventory = new ArrayList<PantsArmour>();
	private ArrayList<ChestArmour> chestArmourInventory = new ArrayList<ChestArmour>();
	private ArrayList<ShoesArmour> shoesArmourInventory = new ArrayList<ShoesArmour>();

	private HashMap<Potion, Integer> potionInventory = new HashMap<Potion, Integer>();
	private ArrayList<Magic> magicInventory = new ArrayList<Magic>();
	private ArrayList<Magic> magicToLearn = new ArrayList<Magic>();
	@SuppressWarnings("unchecked")
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
		try
		{
			magicToLearn.addAll((ArrayList<Magic>) (ArrayList<?>) main.Main.readJson(ClassLoader.getSystemResource("OffensiveMagics.json").toString(),new TypeToken<ArrayList<OffensiveMagic>>(){}));
			magicToLearn.addAll((ArrayList<Magic>) (ArrayList<?>) main.Main.readJson(ClassLoader.getSystemResource("HealingMagics.json").toString(),new TypeToken<ArrayList<HealingMagic>>(){}));
			magicToLearn.sort
			(new Comparator<Magic>()
			{
				public int compare(Magic a, Magic b)
				{
					return a.getLevel() - b.getLevel();
				}
			}
			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void gainItem(Object item)
	{
		if(item == null)
			return;
		if(item instanceof Weapon)
		{
			Weapon weapon = (Weapon) item;
			if(!weaponInventory.contains(weapon))
			{
				weaponInventory.add(weapon);
			}
		}
		else if(item instanceof ChestArmour)
		{
			ChestArmour armour = (ChestArmour) item;
			if(!chestArmourInventory.contains(armour))
			{
				chestArmourInventory.add(armour);
			}
		}
		else if(item instanceof PantsArmour)
		{
			PantsArmour armour = (PantsArmour) item;
			if(!pantsArmourInventory.contains(armour))
			{
				pantsArmourInventory.add(armour);
			}
		}
		else if(item instanceof ShoesArmour)
		{
			ShoesArmour armour = (ShoesArmour) item;
			if(!shoesArmourInventory.contains(armour))
			{
				shoesArmourInventory.add(armour);
			}
		}
		else
		{
//			throw();
		}
	}
	public void equipItem(Object item)
	{
		if(item == null)
			return;
		if(item instanceof Weapon)
		{
			Weapon weapon = (Weapon) item;
			gainItem(equipedWeapon);
			equipedWeapon = weapon;
		}
		else if(item instanceof ChestArmour)
		{
			ChestArmour armour = (ChestArmour) item;
			gainItem(equipedChestArmour);
			equipedChestArmour = armour;
		}
		else if(item instanceof PantsArmour)
		{
			PantsArmour armour = (PantsArmour) item;
			gainItem(equipedPantsArmour);
			equipedPantsArmour = armour;
		}
		else if(item instanceof ShoesArmour)
		{
			ShoesArmour armour = (ShoesArmour) item;
			gainItem(equipedShoesArmour);
			equipedShoesArmour = armour;
		}
		else
		{
//			throw();
		}
	}
	public void gainPotion(Object item)
	{
		Potion potion;
		if(item == null)
			return;
		if(item instanceof Potion)
		{
			potion = (Potion) item;
		}
		else
		{
//			throw();
			return;
		}
		if(potionInventory.containsKey(potion))
		{
			potionInventory.put(potion, potionInventory.get(potion) + 1);
		}
		else
		{
			potionInventory.put(potion, 1);
		}
	}
	public boolean usePotion(Object item)
	{
		Potion potion;
		if(item == null)
			return false;
		if(item instanceof Potion)
		{
			potion = (Potion) item;
		}
		else
		{
//			throw(); (or no)
			return false;
		}
		if(potionInventory.containsKey(potion))
		{
			potion.usePotion(this);
			potionInventory.put(potion, potionInventory.get(potion) - 1);
			if(potionInventory.get(potion) == 0)
			{
				potionInventory.remove(potion);
			}
			return true;
		}
		return false;
	}
	

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



	public ChestArmour getStartingChestArmour()
	{
		return startingChestArmour;
	}

	public void setStartingChestArmour(ChestArmour startingChestArmour)
	{
		this.startingChestArmour = startingChestArmour;
	}


	public Weapon getEquipedWeapon()
	{
		return equipedWeapon;
	}

	public void setEquipedWeapon(Weapon equipedWeapon)
	{
		this.equipedWeapon = equipedWeapon;
	}


	public ChestArmour getEquipedChestArmour()
	{
		return equipedChestArmour;
	}

	public void setEquipedChestArmour(ChestArmour equipedChestArmour)
	{
		this.equipedChestArmour = equipedChestArmour;
	}

	
	public ArrayList<Weapon> getWeaponInventory()
	{
		return weaponInventory;
	}

	public PantsArmour getEquipedPantsArmour()
	{
		return equipedPantsArmour;
	}

	public void setEquipedPantsArmour(PantsArmour equipedPantsArmour)
	{
		this.equipedPantsArmour = equipedPantsArmour;
	}

	public ShoesArmour getEquipedShoesArmour()
	{
		return equipedShoesArmour;
	}

	public void setEquipedShoesArmour(ShoesArmour equipedShoesArmour)
	{
		this.equipedShoesArmour = equipedShoesArmour;
	}

	public ArrayList<PantsArmour> getPantsArmourInventory()
	{
		return pantsArmourInventory;
	}

	public void setPantsArmourInventory(ArrayList<PantsArmour> pantsArmourInventory)
	{
		this.pantsArmourInventory = pantsArmourInventory;
	}

	public ArrayList<ShoesArmour> getShoesArmourInventory()
	{
		return shoesArmourInventory;
	}

	public void setShoesArmourInventory(ArrayList<ShoesArmour> shoesArmourInventory)
	{
		this.shoesArmourInventory = shoesArmourInventory;
	}

	public void setWeaponInventory(ArrayList<Weapon> weaponInventory)
	{
		this.weaponInventory = weaponInventory;
	}


	public ArrayList<ChestArmour> getChestArmourInventory()
	{
		return chestArmourInventory;
	}

	public void setChestArmourInventory(ArrayList<ChestArmour> chestArmourInventory)
	{
		this.chestArmourInventory = chestArmourInventory;
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

	public ArrayList<Magic> getMagicToLearn()
	{
		return magicToLearn;
	}

}
