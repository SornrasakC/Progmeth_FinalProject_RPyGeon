package main;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import item.Weapon;
import logic.base.Potion;
import logic.logics.Dungeon;
import logic.logics.Player;
import shops.BlackSmith;
import shops.ItemShop;


public class Main
{
	public static <T> ArrayList<T> readJson(String filename, TypeToken<ArrayList<T>> typeToken) throws Exception
	{
		Scanner sc = new Scanner(ClassLoader.getSystemResourceAsStream(filename));
		String fileText = sc.useDelimiter("\\A").next();
		sc.close();
		ArrayList<T> jsonList = new Gson().fromJson(fileText, typeToken.getType() );
		return jsonList;
	}
	public static void main(String args[])
	{
		try
		{
			Dungeon.dungeonInit();
			ItemShop shop = new ItemShop();
			Player m = new Player("name");
			Weapon wea1 = new Weapon("Wooden1 Stick", "Normal woody stick", 1, 1, 0, 0, 1);
			Weapon wea2 = new Weapon("Wooden2 Stick", "Normal woody stick", 1, 1, 0, 0, 1);
			m.equipItem(wea1);
			m.equipItem(wea2);
			System.out.println(m.getWeaponInventory().size());
			BlackSmith bs = new BlackSmith();
			for(Potion i : shop.getPotionList())
			{
				System.out.println(i);
			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
