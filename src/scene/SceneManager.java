package scene;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class SceneManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	public static Village villagePane = new Village();
	public static Battle battlePane = new Battle();
	public static Epilogue epilogueRoot = new Epilogue();
//	public static BattleEnd battleEndPane = new BattleEnd();
	
	public static Scene villageScene = new Scene(villagePane, WIDTH, HEIGHT, Color.BLACK);
	public static Scene creditScene = new Scene(new Credit(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene mainScreenScene = new Scene(new MainMenu(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene startNamingScene = new Scene(new StartNaming(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene confirmPrologueScene = new Scene(new ConfirmPrologue(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene prologueScene = new Scene(new Prologue(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene itemshopScene = new Scene(new ItemShop(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene battleScene = new Scene(battlePane, WIDTH, HEIGHT, Color.BLACK);
	public static Scene dungeonChooseFloorScene = new Scene(new DungeonChooseFloor(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene blackScreenScene = new Scene(new BlackScreen(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene epilogueScene = new Scene(epilogueRoot, WIDTH, HEIGHT, Color.BLACK);
	
	public SceneManager() 
	{
	}
	
	public static Village getVillagePane() 
	{
		return villagePane;
	}
	
	public static Battle getBattlePane()
	{
		return battlePane;
	}
	public static void reDungeonChooseFloor()
	{
		dungeonChooseFloorScene = new Scene(new DungeonChooseFloor(), WIDTH, HEIGHT);
		dungeonChooseFloorScene.getStylesheets().add(SceneManager.class.getResource("DungeonChooseFloorStyle.css").toExternalForm());
	}
	public static void reBattle()
	{
		battlePane = new Battle();
		battleScene = new Scene(battlePane, WIDTH, HEIGHT);
		battleScene.getStylesheets().add(SceneManager.class.getResource("BattleStyle.css").toExternalForm());
	}
	static
	{
		battleScene.getStylesheets().add(SceneManager.class.getResource("BattleStyle.css").toExternalForm());
		dungeonChooseFloorScene.getStylesheets().add(SceneManager.class.getResource("DungeonChooseFloorStyle.css").toExternalForm());
		prologueScene.getStylesheets().add(SceneManager.class.getResource("Prologue.css").toExternalForm());
		epilogueScene.getStylesheets().add(SceneManager.class.getResource("Epilogue.css").toExternalForm());
	}

}
