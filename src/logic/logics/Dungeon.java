package logic.logics;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;

import item.ChestArmour;
import item.PantsArmour;
import item.ShoesArmour;
import item.Weapon;
import logic.base.Droppable;
import logic.base.Monster;

public class Dungeon
{
	private String name;
	private int floor;
	private boolean cleared;

	private static ArrayList<Dungeon> dungeonList;
	private static ArrayList<Monster> monsterList;
	private static ArrayList<Droppable> dropList;

	public Dungeon(String name, int floor)
	{
		super();
		this.name = name;
		this.floor = floor;
		this.cleared = false;
	}

	public static void dropMoney(Player player, Monster monster)
	{
		player.gainMoney(monster.getMoney());
	}

	@SuppressWarnings("unchecked")
	public static void dungeonInit() // start game
	{
		dungeonList = new ArrayList<Dungeon>();
		dungeonList.add(new Dungeon("Dark Cave", 1));
		dungeonList.add(new Dungeon("Kobold Lair", 2));
		dungeonList.add(new Dungeon("Fortnite's Ruin", 3));
		dungeonList.add(new Dungeon("Valve's HQ", 4));
		dungeonList.add(new Dungeon("O-10 Facility", 5));
		dungeonList.add(new Dungeon("The Gate", 6));
		try
		{
			monsterList = new ArrayList<Monster>();
			monsterList.addAll((ArrayList<Monster>) (ArrayList<?>) main.Main.readJson(
					ClassLoader.getSystemResource("BossMonsters.json").toString(),
					new TypeToken<ArrayList<BossMonster>>()
					{
					}));
			monsterList.addAll((ArrayList<Monster>) (ArrayList<?>) main.Main.readJson(
					ClassLoader.getSystemResource("PhysicalMonsters.json").toString(),
					new TypeToken<ArrayList<PhysicalMonster>>()
					{
					}));
			monsterList.addAll((ArrayList<Monster>) (ArrayList<?>) main.Main.readJson(
					ClassLoader.getSystemResource("MagicalMonsters.json").toString(),
					new TypeToken<ArrayList<MagicalMonster>>()
					{
					}));

			dropList = new ArrayList<Droppable>();
			dropList.addAll(main.Main.readJson(ClassLoader.getSystemResource("dropWeapons.json").toString(),
					new TypeToken<ArrayList<Weapon>>()
					{
					}));
			dropList.addAll(main.Main.readJson(ClassLoader.getSystemResource("ChestArmours.json").toString(),
					new TypeToken<ArrayList<ChestArmour>>()
					{
					}));
			dropList.addAll(main.Main.readJson(ClassLoader.getSystemResource("PantsArmours.json").toString(),
					new TypeToken<ArrayList<PantsArmour>>()
					{
					}));
			dropList.addAll(main.Main.readJson(ClassLoader.getSystemResource("ShoesArmours.json").toString(),
					new TypeToken<ArrayList<ShoesArmour>>()
					{
					}));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Monster generateMonster()
	{

		int rangeMin = floor * 2 - 1, rangeMax = floor * 2;
		if (floor == 6)
		{
			Monster monster = monsterList.stream().filter(x -> x.getLevel() == 11).findAny().get();
			if (monster.isDead())
			{
				return monsterList.stream().filter(x -> x.getLevel() == 12).findAny().get();
			}
			return monster;
		}
		ArrayList<Monster> tempMonsterList = monsterList.stream()
				.filter(x -> x.getLevel() >= rangeMin && x.getLevel() <= rangeMax)
				.collect(Collectors.toCollection(ArrayList::new));
		return (Monster) Rand.rand(tempMonsterList);
	}

	public Object dropItem()
	{
		if (Rand.chance(1))
		{
			ArrayList<Droppable> tempDropList = dropList.stream().filter(x -> x.getFloor() == 0)
					.collect(Collectors.toCollection(ArrayList::new));
			return Rand.rand(tempDropList);
		}
		if (Rand.chance(25))
		{
			ArrayList<Droppable> tempDropList = dropList.stream().filter(x -> x.getFloor() == floor)
					.collect(Collectors.toCollection(ArrayList::new));
			return Rand.rand(tempDropList);
		}
		return null;
	}

	public String getName()
	{
		return name;
	}

	public int getFloor()
	{
		return floor;
	}

	public static ArrayList<Dungeon> getDungeonList()
	{
		return dungeonList;
	}

	public static ArrayList<Monster> getMonsterList()
	{
		return monsterList;
	}

	public boolean isCleared()
	{
		return cleared;
	}

	public static ArrayList<Droppable> getDropList()
	{
		return dropList;
	}

	public void setCleared(boolean cleared)
	{
		this.cleared = cleared;
	}
}
