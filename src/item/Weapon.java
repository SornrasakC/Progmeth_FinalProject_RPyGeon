package item;

import javafx.scene.image.Image;
import logic.base.Droppable;
import sharedObject.RenderableHolder;

public class Weapon implements Droppable
{
	private String name;
	private String description;
	private int baseMinPhyAtk;
	private int baseMaxPhyAtk;
	
	private int baseMinMagAtk;
	private int baseMaxMagAtk;
	
	private boolean isBought = false;
	private int price = 0;
	private int floor;
	private Image sprite;
	public Weapon(String name, String description, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk,
			int baseMaxMagAtk, int floor)
	{
		super();
		this.name = name;
		this.description = description; //No description yet
		this.baseMinPhyAtk = baseMinPhyAtk;
		this.baseMaxPhyAtk = baseMaxPhyAtk;
		this.baseMinMagAtk = baseMinMagAtk;
		this.baseMaxMagAtk = baseMaxMagAtk;
		this.floor = floor;
	}
	public Weapon(String name, String description, int baseMinPhyAtk, int baseMaxPhyAtk, int baseMinMagAtk,
			int baseMaxMagAtk, int floor, int price)
	{
		super();
		this.name = name;
		this.baseMinPhyAtk = baseMinPhyAtk;
		this.baseMaxPhyAtk = baseMaxPhyAtk;
		this.baseMinMagAtk = baseMinMagAtk;
		this.baseMaxMagAtk = baseMaxMagAtk;
		this.price = price;
		this.floor = floor;
	}
	public String toString()
	{
		return name + ", floor :" + floor;
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Weapon))
		{
			return false;
		}
		Weapon weapon = (Weapon) obj;
		return name.equals(weapon.getName());
	}
	
	@Override
	public int hashCode()
	{
		return (name + description).hashCode();
	}
	public boolean isBought()
	{
		return isBought;
	}
	public void setBought(boolean isBought)
	{
		this.isBought = isBought;
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public int getBaseMinPhyAtk()
	{
		return baseMinPhyAtk;
	}
	public int getBaseMaxPhyAtk()
	{
		return baseMaxPhyAtk;
	}
	public int getBaseMinMagAtk()
	{
		return baseMinMagAtk;
	}
	public int getBaseMaxMagAtk()
	{
		return baseMaxMagAtk;
	}
	public int getFloor()
	{
		return floor;
	}
	public int getPrice()
	{
		return price;
	}
	
	public Image getSprite() {
		switch(this.getName()) {
		case("Wooden Stick"): sprite = RenderableHolder.woodenStick; break;
		case("Excalibur"): sprite = RenderableHolder.excalibur; break;
		case("Branch of Yggdrasil"): sprite = RenderableHolder.yggdrasilBranch; break;
		case("Rusted Iron Sword"): sprite = RenderableHolder.rustedIronSword; break;
		case("Rotten Wooden Staff"): sprite = RenderableHolder.rottenWoodStaff; break;
		case("Slightly Dull Steel Sword"): sprite = RenderableHolder.slightlyDullSteelSword; break;
		case("Shiny Stone Rod"): sprite = RenderableHolder.shinyStoneRod; break;
		case("Polished Steel Sword"): sprite = RenderableHolder.polishedSteelSword; break;
		case("Ruby Staff"): sprite = RenderableHolder.rubyStaff; break;
		case("Fiery Blade"): sprite = RenderableHolder.fieryBlade; break;
		case("Mythril Sword"): sprite = RenderableHolder.mythrilSword; break;
		case("Book of Power"): sprite = RenderableHolder.bookOfPower; break;
		case("Tellulam"): sprite = RenderableHolder.tellulam; break;
		case("Grimoir of Truth"): sprite = RenderableHolder.grimoirOfTruth; break;
		case("Wooden Sword"): sprite = RenderableHolder.woodenSword; break;
		case("Slightly Magical Stick"): sprite = RenderableHolder.slightlyMagicalStick; break;
		case("Iron Sword"): sprite = RenderableHolder.ironSword; break;
		case("Wooden Staff"): sprite = RenderableHolder.woodenStaff; break;
		case("Steel Sword"): sprite = RenderableHolder.steelSword; break;
		case("Emerald Staff"): sprite = RenderableHolder.emeraldStaff; break;
		case("Palladium Sword"): sprite = RenderableHolder.palladiumSword; break;
		case("Caster's Ring"): sprite = RenderableHolder.casterRing; break;
		case("Chinese Adamantium Sword"): sprite = RenderableHolder.chineseAdamantiumSword; break;
		case("Chinese Diamond Staff"): sprite = RenderableHolder.chineseDiamondStaff; break;
		default:sprite = RenderableHolder.placeHolder;
		}
		return this.sprite;
	}
	
}
