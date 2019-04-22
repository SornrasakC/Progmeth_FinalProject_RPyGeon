package logic.logics;

import java.util.ArrayList;

import logic.base.Monster;

public class Dungeon
{
	private String name;
	private int floor;
	public static ArrayList<Dungeon> dungeonList;
	
	
	public Dungeon(String name, int floor)
	{
		super();
		this.name = name;
		this.floor = floor;
	}

	private void dropMoney(Player player, Monster monster)
	{
		player.gainMoney(monster.getMoney());
	}
	public static void dungeonInit()
	{
		for(int i = 0; i < 5; i++)
		{
			dungeonList.add(new Dungeon("dun1", 1));
		}
	}
//	public Monster generateMonster()
//	{
//		
//	}
}
