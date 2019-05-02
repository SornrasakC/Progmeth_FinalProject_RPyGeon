package scene;

import javafx.scene.Scene;

public class SceneManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	public static Village villagePane = new Village();
	public static Battle battlePane = new Battle();
	
	public static Scene villageScene = new Scene(villagePane, WIDTH, HEIGHT);
	public static Scene creditScene = new Scene(new Credit(), WIDTH, HEIGHT);
	public static Scene mainScreenScene = new Scene(new MainMenu(), WIDTH, HEIGHT);
	public static Scene startNamingScene = new Scene(new StartNaming(), WIDTH, HEIGHT);
	public static Scene confirmPrologueScene = new Scene(new ConfirmPrologue(), WIDTH, HEIGHT);
	public static Scene prologueScene = new Scene(new Prologue(), WIDTH, HEIGHT);
	public static Scene itemshopScene = new Scene(new ItemShop(), WIDTH, HEIGHT);
	public static Scene battleScene = new Scene(battlePane, WIDTH, HEIGHT);
	public static Scene dungeonChooseFloorScene = new Scene(new DungeonChooseFloor(), WIDTH, HEIGHT);
	
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

	static
	{
		dungeonChooseFloorScene.getStylesheets().add(ClassLoader.getSystemResource("DungeonChooseFloorStyle.css").toString());
	}

}
