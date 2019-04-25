package main;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import logic.logics.Dungeon;


public class Main
{

	public Main()
	{
		// TODO Auto-generated constructor stub
	}
	public static <T> ArrayList<T> readJson(String filename, TypeToken<ArrayList<T>> typeToken) throws Exception
	{
		System.out.println(filename);
//		File file = new File("bin");
//		for(String fileNames : file.list()) System.out.println(fileNames);
//		Scanner sc = new Scanner(new File(filename.substring(6)));
//		Scanner sc = new Scanner(new File(filename));
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
//			String filename = "MagicalMonsters.json";
//			Scanner sc = new Scanner(new File(filename));
			System.out.println(ClassLoader.getSystemResource("MagicalMonsters.json").toString());
			Dungeon.dungeonInit();
//			Player m = new Player("name");
//			for(Magic i : m.getMagicToLearn())
//			{
//				System.out.println(i);
//			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
