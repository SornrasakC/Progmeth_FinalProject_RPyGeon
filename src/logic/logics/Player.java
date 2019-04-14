package logic.logics;
import java.util.ArrayList;
import java.util.HashMap;

import logic.base.Character;
import logic.base.Magic;
import logic.base.Potion;

public class Player extends Character
{
	private String battleClass = "jobless";
	
	private int exp = 0;
	private int expToNextLevel = 100;
	
	private Weapon startingWeapon = new Weapon("Wooden Stick", "Normal woody stick", 1, 1, 0, 0);
	private HeadArmour startingHeadArmour = new HeadArmour("Normal Cotton T-Shirt", "This is T-Shirt", 0, 0);
	private ChestArmour startingChestArmour = new ChestArmour("Generic Pants", "This is pants", 0, 0);
	private LegArmour startingLegArmour = new LegArmour("Normal Shoes", "This is shoes", 0, 0);
	
	private Weapon equipedWeapon = startingWeapon;
	private HeadArmour equipedHeadArmour = startingHeadArmour;
	private ChestArmour equipedChestArmour = startingChestArmour;
	private LegArmour equipedLegArmour = startingLegArmour;
	
	private ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
	private ArrayList<HeadArmour> headArmourInventory = new ArrayList<HeadArmour>();
	private ArrayList<ChestArmour> chestArmourInventory = new ArrayList<ChestArmour>();
	private ArrayList<LegArmour> legArmourInventory = new ArrayList<LegArmour>();
	
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
	
}
