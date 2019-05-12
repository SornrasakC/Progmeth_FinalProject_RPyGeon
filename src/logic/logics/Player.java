package logic.logics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import item.ChestArmour;
import item.PantsArmour;
import item.ShoesArmour;
import item.Weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.base.Character;
import logic.base.CustomException;
import logic.base.Magic;
import logic.base.Monster;
import logic.base.Potion;
import magic.HealingMagic;
import magic.OffensiveMagic;
import shops.BlackSmith;
import shops.ItemShop;

public class Player extends Character
{
	public static Player player;
	private String battleClass = "jobless";

	private int exp = 0;
	private int expToNextLevel = 100;
	private int money = 0;
	private int conqueredFloor = 0;

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
	
	private int boostMinPhyAtk = 0;
	private int boostMaxPhyAtk = 0;
	private int boostMinMagAtk = 0;
	private int boostMaxMagAtk = 0;
	private int boostPhyDef = 0;
	private int boostMagDef = 0;
	
	private int gainedEXP = 0;

	static
	{
		player = new Player();
	}
	@SuppressWarnings("unchecked")
	public Player()
	{
		super();
		this.sprite = new Image(ClassLoader.getSystemResourceAsStream("player1.png"));
		this.spriteAnimation = new Image(ClassLoader.getSystemResourceAsStream("player2.png"));
		this.name = "Name";
		this.level = 1;

		this.baseMaxPhyAtk = 1; //testing
		this.baseMinPhyAtk = 1; //testing
		this.baseMaxMagAtk = 2;
		this.baseMinMagAtk = 1;
		this.basePhyDef = 0;
		this.baseMagDef = 0;
		this.baseMaxHp = 500;
		this.baseMaxMp = 5;

		this.modMaxPhyAtk = 1;
		this.modMinPhyAtk = 1;
		fullHeal();
		try
		{
			magicToLearn.addAll((ArrayList<Magic>) (ArrayList<?>) main.Main.readJson("OffensiveMagics.json",
					new TypeToken<ArrayList<OffensiveMagic>>()
					{
					}));
			magicToLearn.addAll((ArrayList<Magic>) (ArrayList<?>) main.Main.readJson("HealingMagics.json",
					new TypeToken<ArrayList<HealingMagic>>()
					{
					}));
			magicToLearn.sort(new Comparator<Magic>()
			{
				public int compare(Magic a, Magic b)
				{
					return a.getLevel() - b.getLevel();
				}
			});
			magicToLearn.forEach
			(
				(magic) ->
				{
					magic.initLuckType();
				}
			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Potion buyPotion(ItemShop itemShop, Potion potion)
	{
		if (potion == null) return null;
		if (itemShop.getPotionList().contains(potion))
		{
			if(potion.getCost() > money)
			{
				return null;
			}
			money -= potion.getCost();
			gainPotion(potion);
			try
			{
				return (Potion) potion.clone();
			}
			catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public Weapon buyWeapon(BlackSmith blackSmith, Weapon weapon)
	{
		if (weapon == null) return null;
		if (blackSmith.getWeaponAvailableList(this).contains(weapon))
		{
			if (weapon.getPrice() > money)
			{
				return null;
			}
			money -= weapon.getPrice();
			gainItem(weapon);
			return (Weapon) pop(weapon, blackSmith.getWeaponAvailableList(this));
		}
		return null;
	}

	public int gainExp(int exp) // return how many level up
	{
		if (exp < 0) return 0;
		this.exp += exp;
		int count = 0;
		while (this.exp > this.expToNextLevel)
		{
			expToNextLevel = 2 * expToNextLevel + 150;
			levelUp();
			count++;
		}
		return count;
	}
	public int gainExp(Monster monster) // return how many level up
	{
		int exp = 0;
		double levelDiff = monster.getLevel() - this.level;
		double bonusPercent = 1 + levelDiff / 4;
		exp = (int) Math.round(Rand.rand(monster.getLevel() * 100, monster.getLevel() * 120) * bonusPercent);
		
		if (exp < 0) 
		{
			gainedEXP = 0;
			return 0;
		}
		this.exp += exp;
		gainedEXP = exp;
		int count = 0;
		while (this.exp > this.expToNextLevel)
		{
			expToNextLevel = 2 * expToNextLevel + 150;
			levelUp();
			count++;
		}
		return count;
	}
	

	public void levelUp()
	{
		Object magic = pop(0, magicToLearn);
		if(magic != null)
			magicInventory.add((Magic) magic);
		level += 1;
		baseMaxPhyAtk += 1;
		baseMinPhyAtk += 1;
		baseMaxMagAtk += 1;
		baseMinMagAtk += 1;
		basePhyDef += 1;
		baseMagDef += 1;
		baseMaxHp += 16;
		baseMaxMp += 10;
		currentHp += 16;
		currentMp += 10;
	}

	public void manualUpStat(int point)
	{
		switch (point)
		{
			case (0):
				baseMaxPhyAtk += 1;
				baseMinPhyAtk += 1;
				break;
			case (1):
				baseMaxMagAtk += 1;
				baseMinMagAtk += 1;
				break;
			case (2):
				basePhyDef += 1;
				break;
			case (3):
				baseMagDef += 1;
				break;
			case (4):
				baseMaxHp += 16;
				currentHp += 16;
				break;
			case (5):
				baseMaxMp += 10;
				currentMp += 10;
				break;
		}
	}

	public boolean gainItem(Object item)
	{
		if (item == null) return false;
		if (item instanceof Weapon)
		{
			Weapon weapon = (Weapon) item;
			if (!weaponInventory.contains(weapon))
			{
				weaponInventory.add(weapon);
				return true;
			}
			return false;
		}
		if (item instanceof ChestArmour)
		{
			ChestArmour armour = (ChestArmour) item;
			if (!chestArmourInventory.contains(armour))
			{
				chestArmourInventory.add(armour);
				return true;
			}
			return false;
		}
		if (item instanceof PantsArmour)
		{
			PantsArmour armour = (PantsArmour) item;
			if (!pantsArmourInventory.contains(armour))
			{
				pantsArmourInventory.add(armour);
				return true;
			}
			return false;
		}
		if (item instanceof ShoesArmour)
		{
			ShoesArmour armour = (ShoesArmour) item;
			if (!shoesArmourInventory.contains(armour))
			{
				shoesArmourInventory.add(armour);
				return true;
			}
			return false;
		}
		return false;
	}

	public void equipItem(Object item)
	{
		if (item == null) return;
		if (item instanceof Weapon)
		{
			Weapon weapon = (Weapon) item;
			gainItem(equipedWeapon);
			modMinPhyAtk -= equipedWeapon.getBaseMinPhyAtk();
			modMaxPhyAtk -= equipedWeapon.getBaseMaxPhyAtk();
			modMinMagAtk -= equipedWeapon.getBaseMinMagAtk();
			modMinMagAtk -= equipedWeapon.getBaseMinMagAtk();
			equipedWeapon = weapon;
			modMinPhyAtk += equipedWeapon.getBaseMinPhyAtk();
			modMaxPhyAtk += equipedWeapon.getBaseMaxPhyAtk();
			modMinMagAtk += equipedWeapon.getBaseMinMagAtk();
			modMinMagAtk += equipedWeapon.getBaseMinMagAtk();
		}
		else if (item instanceof ChestArmour)
		{
			ChestArmour armour = (ChestArmour) item;
			gainItem(equipedChestArmour);
			modPhyDef -= equipedChestArmour.getBasePhyDef();
			modMagDef -= equipedChestArmour.getBaseMagDef();
			equipedChestArmour = armour;
			modPhyDef += equipedChestArmour.getBasePhyDef();
			modMagDef += equipedChestArmour.getBaseMagDef();
		}
		else if (item instanceof PantsArmour)
		{
			PantsArmour armour = (PantsArmour) item;
			gainItem(equipedPantsArmour);
			modPhyDef -= equipedPantsArmour.getBasePhyDef();
			modMagDef -= equipedPantsArmour.getBaseMagDef();
			equipedPantsArmour = armour;
			modPhyDef += equipedPantsArmour.getBasePhyDef();
			modMagDef += equipedPantsArmour.getBaseMagDef();
		}
		else if (item instanceof ShoesArmour)
		{
			ShoesArmour armour = (ShoesArmour) item;
			gainItem(equipedShoesArmour);
			modPhyDef -= equipedShoesArmour.getBasePhyDef();
			modMagDef -= equipedShoesArmour.getBaseMagDef();
			equipedShoesArmour = armour;
			modPhyDef += equipedShoesArmour.getBasePhyDef();
			modMagDef += equipedShoesArmour.getBaseMagDef();
		}
	}

	public boolean gainPotion(Object item)
	{
		Potion potion;
		if (item == null) return false;
		if (item instanceof Potion)
		{
			potion = (Potion) item;
		}
		else
		{
			return false;
		}
		if (potionInventory.containsKey(potion))
		{
			potionInventory.put(potion, potionInventory.get(potion) + 1);
		}
		else
		{
			potionInventory.put(potion, 1);
		}
		return true;
	}

	public boolean usePotion(Object item)
	{
		Potion potion;
		if (item == null) return false;
		if (item instanceof Potion)
		{
			potion = (Potion) item;
		}
		else
		{
			new CustomException("UsePotion didn't receive potion").printStackTrace();
			return false;
		}
		if (potionInventory.containsKey(potion))
		{
			potion.usePotion(this);
			potionInventory.put(potion, potionInventory.get(potion) - 1);
			if (potionInventory.get(potion) == 0)
			{
				potionInventory.remove(potion);
			}
			return true;
		}
		return false;
	}

	public <T> Object pop(Object item, ArrayList<T> itemList)
	{
		if (itemList.contains(item))
		{
			itemList.remove(item);
			return item;
		}
		return null;
	}

	public <T> Object pop(int index, ArrayList<T> itemList)
	{
		if (index >= 0 && index < itemList.size())
		{
			Object object = itemList.get(index);
			itemList.remove(index);
			return object;
		}
		return null;
	}

	public void gainMoney(int money)
	{
		if (money < 0) return;
		this.money += money;
	}
	public void resetStatPotionEffects()
	{
		modMinPhyAtk -= boostMinPhyAtk;
		modMaxPhyAtk -= boostMaxPhyAtk;
		modMinMagAtk -= boostMinMagAtk;
		modMaxMagAtk -= boostMaxMagAtk;
		modPhyDef -= boostPhyDef;
		modMagDef -= boostMagDef;
		boostMinPhyAtk = 0;
		boostMaxPhyAtk = 0;
		boostMinMagAtk = 0;
		boostMaxMagAtk = 0;
		boostPhyDef = 0;
		boostMagDef = 0;
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

	public int getConqueredFloor()
	{
		return conqueredFloor;
	}

	public void setConqueredFloor(int conqueredFloor)
	{
		this.conqueredFloor = conqueredFloor;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Add animation
		gc.drawImage(sprite, x, y);
		
	}

	public int getBoostMinPhyAtk()
	{
		return boostMinPhyAtk;
	}

	public void setBoostMinPhyAtk(int boostMinPhyAtk)
	{
		this.boostMinPhyAtk = boostMinPhyAtk;
	}

	public int getBoostMaxPhyAtk()
	{
		return boostMaxPhyAtk;
	}

	public void setBoostMaxPhyAtk(int boostMaxPhyAtk)
	{
		this.boostMaxPhyAtk = boostMaxPhyAtk;
	}

	public int getBoostMinMagAtk()
	{
		return boostMinMagAtk;
	}

	public void setBoostMinMagAtk(int boostMinMagAtk)
	{
		this.boostMinMagAtk = boostMinMagAtk;
	}

	public int getBoostMaxMagAtk()
	{
		return boostMaxMagAtk;
	}

	public void setBoostMaxMagAtk(int boostMaxMagAtk)
	{
		this.boostMaxMagAtk = boostMaxMagAtk;
	}

	public int getBoostPhyDef()
	{
		return boostPhyDef;
	}

	public void setBoostPhyDef(int boostPhyDef)
	{
		this.boostPhyDef = boostPhyDef;
	}

	public int getBoostMagDef()
	{
		return boostMagDef;
	}

	public void setBoostMagDef(int boostMagDef)
	{
		this.boostMagDef = boostMagDef;
	}

	public int getGainedEXP()
	{
		return gainedEXP;
	}

	public void setGainedEXP(int gainedEXP)
	{
		this.gainedEXP = gainedEXP;
	}
	
	
}
