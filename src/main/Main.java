package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Main
{

	public Main()
	{
		// TODO Auto-generated constructor stub
	}
	public static <T> ArrayList<T> readJson(String filename, TypeToken<ArrayList<T>> typeToken) throws Exception
	{
		Scanner sc = new Scanner(new File(filename));
//		Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
		String fileText = sc.useDelimiter("\\A").next();
		sc.close();
		ArrayList<T> jsonList = new Gson().fromJson(fileText, typeToken.getType() );
		return jsonList;
	}
	public static void main(String args[])
	{
		try
		{
//			String filename = "file:/D:/Programming/EclipseWorkspace/Progmeth_FinalProject_RJyGeon/bin/ree.txt";
//			String filename = "file:/D:/Programming/EclipseWorkspace/Progmeth_FinalProject_RJyGeon/bin/BossMonsters.json";
//			String filename = "res/BossMonsters.json";
			String filename = ClassLoader.getSystemResource("ree.txt").toString();
//			new File(filename);
//			Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
			Scanner sc = new Scanner(new File(filename));
//			System.out.println(ClassLoader.getSystemResource("ree.txt"));
//			Dungeon.dungeonInit();
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
