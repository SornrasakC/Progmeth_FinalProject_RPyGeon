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
	public static void dungeonInit() //start game
	{
		
	}
//	public Monster generateMonster()
//	{
//		
//	}
}
