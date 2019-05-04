package main;


import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entity.BattleEntityLogic;
import entity.VillageEntityLogic;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.logics.Player;
import scene.SceneManager;
import shops.ItemShop;

public class Main extends Application
{

	public static Stage primaryStage;
	public static AnimationTimer animation, battleAnimation;
	private static VillageEntityLogic villageLogic;
	private static BattleEntityLogic battleLogic;

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
			Player.player.levelUp();
			Player.player.levelUp();
			ItemShop itemShop = new ItemShop();
			
			Player.player.gainPotion(itemShop.getPotionList().get(0));
			Player.player.gainPotion(itemShop.getPotionList().get(0));
			Player.player.gainPotion(itemShop.getPotionList().get(0));
			Player.player.gainPotion(itemShop.getPotionList().get(1));
			Player.player.gainPotion(itemShop.getPotionList().get(2));
			Player.player.setConqueredFloor(8);
			System.out.println(Player.player.getPotionInventory().keySet());

			launch(args);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Main.primaryStage = primaryStage;
		primaryStage.setTitle("RJyGeon");
		primaryStage.setScene(SceneManager.mainScreenScene);
		primaryStage.show();

		villageLogic = new VillageEntityLogic();
		battleLogic = new BattleEntityLogic();

		animation = new AnimationTimer()
		{
			public void handle(long now)
			{
				// village
				SceneManager.getVillagePane().drawBackground();
				villageLogic.logicUpdate();
				SceneManager.getVillagePane().paintCanvas();
				InputUtility.updateInputState();
				SceneManager.getVillagePane().getCanvas().requestFocus();
			}
		};
		animation.start();
		
		battleAnimation = new AnimationTimer()
		{
			@Override
			public void handle(long now)
			{
				SceneManager.getBattlePane().drawBackground();
				battleLogic.logicUpdate();
				SceneManager.getBattlePane().paintCanvas();
				InputUtility.updateInputState();
				SceneManager.getBattlePane().getBattleCanvas().requestFocus();
			}
		};
		

	}

	public static void changeScene(Scene scene)
	{
		if(scene.equals(SceneManager.dungeonChooseFloorScene))
		{
			SceneManager.reDungeonChooseFloor();
			scene = SceneManager.dungeonChooseFloorScene;
		}
		FadeTransition ft = new FadeTransition(Duration.millis(500), primaryStage.getScene().getRoot());
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
		FadeTransition ft2 = new FadeTransition(Duration.millis(500), scene.getRoot());
		ft2.setFromValue(0);
		ft2.setToValue(1);
		ft2.play();
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public static BattleEntityLogic getBattleLogic()
	{
		return battleLogic;
	}
	

}
