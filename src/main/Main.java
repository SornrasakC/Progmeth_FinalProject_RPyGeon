package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import logic.logics.BossMonster;


public class Main
{

	public Main()
	{
		// TODO Auto-generated constructor stub
	}
	public static <T> ArrayList<T> readJson(String filename, TypeToken<ArrayList<T>> typeToken) throws Exception
	{
		Scanner sc = new Scanner(new File(filename));
		String fileText = sc.useDelimiter("\\A").next();
		sc.close();
		ArrayList<T> jsonList = new Gson().fromJson(fileText, typeToken.getType() );
		return jsonList;
	}
	public static void main(String args[])
	{
		try
		{
			ArrayList<BossMonster> mons = readJson("res/BossMonsters.json", new TypeToken<ArrayList<BossMonster>>() {});
			System.out.println(mons.get(0).getName());
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
