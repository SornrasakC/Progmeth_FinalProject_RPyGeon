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
		case("Excalibur"): sprite = RenderableHolder.redCookie; break;
		case("Branch of Yggdrasil"): sprite = RenderableHolder.redCookie; break;
		case("Rusted Iron Sword"): sprite = RenderableHolder.redCookie; break;
		case("Fairly Normal MP Potion"): sprite = RenderableHolder.redCookie; break;
		case("Rotten Wooden Staff"): sprite = RenderableHolder.redCookie; break;
		case("Slightly Dull Steel Sword"): sprite = RenderableHolder.redCookie; break;
		case("Shiny Stone Rod"): sprite = RenderableHolder.redCookie; break;
		case("Polished Steel Sword"): sprite = RenderableHolder.redCookie; break;
		case("Ruby Staff"): sprite = RenderableHolder.redCookie; break;
		case("Enchanted Sword"): sprite = RenderableHolder.redCookie; break;
		case("Mythril Sword"): sprite = RenderableHolder.redCookie; break;
		case("Book of Power"): sprite = RenderableHolder.redCookie; break;
		case("Tellulam"): sprite = RenderableHolder.redCookie; break;
		case("Grimoir of Truth"): sprite = RenderableHolder.redCookie; break;
		case("Wooden Sword"): sprite = RenderableHolder.redCookie; break;
		case("Slightly Magical Stick"): sprite = RenderableHolder.redCookie; break;
		case("Iron Sword"): sprite = RenderableHolder.redCookie; break;
		case("Wooden Staff"): sprite = RenderableHolder.redCookie; break;
		case("Steel Sword"): sprite = RenderableHolder.redCookie; break;
		case("Emerald Staff"): sprite = RenderableHolder.redCookie; break;
		case("Palladium Sword"): sprite = RenderableHolder.redCookie; break;
		case("Caster's Ring"): sprite = RenderableHolder.redCookie; break;
		case("Chinese Adamantium Sword"): sprite = RenderableHolder.redCookie; break;
		case("Chinese Diamond Staff"): sprite = RenderableHolder.redCookie; break;
		default:sprite = RenderableHolder.redCookie;
		}
		return this.sprite;
	}
	
}
