package logic.base;

import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public abstract class Armour implements Cloneable, Droppable
{
	protected String name;
	protected String description;
	protected int basePhyDef;
	protected int baseMagDef;
	protected int floor;
	protected Image sprite;
	public Armour(String name, String description, int basePhyDef, int baseMagDef, int floor)
	{
		super();
		this.name = name;
		this.description = description;
		this.basePhyDef = basePhyDef;
		this.baseMagDef = baseMagDef;
		this.floor = floor;
	}
//	@Override
//	protected Object clone() throws CloneNotSupportedException
//	{
//		return super.clone();
//	}
	@Override
	public int hashCode()
	{
		return (name + description).hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Armour))
		{
			return false;
		}
		Armour armour = (Armour) obj;
		return name.equals(armour.getName());
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public int getBasePhyDef()
	{
		return basePhyDef;
	}
	public int getBaseMagDef()
	{
		return baseMagDef;
	}
	public int getFloor()
	{
		return floor;
	}
	
	public Image getSprite() {
		switch(this.getName()) {
		case("Normal Cotton T-Shirt"): sprite = RenderableHolder.startingShirt; break;
		case("Generic Pants"): sprite = RenderableHolder.startingPants; break;
		case("Normal Shoes"): sprite = RenderableHolder.startingBoots; break;
		case("Tattered Leather Armour"): sprite = RenderableHolder.leatherShirt; break;
		case("Leather Pants With Holes"): sprite = RenderableHolder.leatherPants; break;
		case("Sturdy Shoes"): sprite = RenderableHolder.leatherBoots; break;
		case("Chain Shirt"): sprite = RenderableHolder.chainShirt; break;
		case("Chain Pants"): sprite = RenderableHolder.chainPants; break;
		case("Chain Greaves"): sprite = RenderableHolder.chainBoots; break;
		case("Steel Breastplate"): sprite = RenderableHolder.steelShirt; break;
		case("Metal Pants"): sprite = RenderableHolder.steelPants; break;
		case("Greaves"): sprite = RenderableHolder.steelBoots; break;
		case("Magic Steel Armour"): sprite = RenderableHolder.magicMetalShirt; break;
		case("Mithril Pants"): sprite = RenderableHolder.magicMetalPants; break;
		case("Boots Of Protection"): sprite = RenderableHolder.magicMetalBoots; break;
		case("Adamantium Breastplate"): sprite = RenderableHolder.adamantiumShirt; break;
		case("Orichalcum Pants"): sprite = RenderableHolder.adamantiumPants; break;
		case("Boots Of Magic Resistance"): sprite = RenderableHolder.adamantiumBoots; break;

		default:sprite = RenderableHolder.placeHolder;
		}
		return this.sprite;
	}
	
}
