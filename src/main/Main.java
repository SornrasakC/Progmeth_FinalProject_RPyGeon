package main;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import item.Weapon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.base.Potion;
import logic.logics.Dungeon;
import logic.logics.Player;
import scene.MainMenu;
import scene.SceneManager;
import shops.BlackSmith;
import shops.ItemShop;

public class Main extends Application
{
	
	public static Stage primaryStage;
	public static <T> ArrayList<T> readJson(String filename, TypeToken<ArrayList<T>> typeToken) throws Exception
	{
		Scanner sc = new Scanner(ClassLoader.getSystemResourceAsStream(filename));
		String fileText = sc.useDelimiter("\\A").next();
		sc.close();
		ArrayList<T> jsonList = new Gson().fromJson(fileText, typeToken.getType());
		return jsonList;
	}

	public static void main(String args[])
	{
		try
		{
			ItemShop shop = new ItemShop();
			Player m = new Player("name");
			Weapon wea1 = new Weapon("Wooden1 Stick", "Normal woody stick", 1, 1, 0, 0, 1);
			Weapon wea2 = new Weapon("Wooden2 Stick", "Normal woody stick", 1, 1, 0, 0, 1);
//			Dungeon.getMonsterList().forEach(x -> System.out.println(x));
			System.out.println(Dungeon.getMonsterList().size());
			launch(args);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setScene(SceneManager.mainScreenScene);
		primaryStage.show();
		
	}
	
	public static void changeScene(Scene scene){
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	
}
