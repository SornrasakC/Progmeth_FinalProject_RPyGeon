package scene;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;

public class SceneManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	public static Village villagePane = new Village();
	public static Battle battlePane = new Battle();
	public static Epilogue epilogueRoot = new Epilogue();
	public static Prologue prologueRoot = new Prologue();
	public static ItemShop itemshopPane = new ItemShop();
	public static BlacksmithShop blacksmithPane = new BlacksmithShop();
	public static Base basePane = new Base();
//	public static BattleEnd battleEndPane = new BattleEnd();
	
	public static Scene villageScene = new Scene(villagePane, WIDTH, HEIGHT, Color.BLACK);
	public static Scene creditScene = new Scene(new Credit(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene mainScreenScene = new Scene(new MainMenu(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene startNamingScene = new Scene(new StartNaming(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene confirmPrologueScene = new Scene(new ConfirmPrologue(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene prologueScene = new Scene(prologueRoot, WIDTH, HEIGHT, Color.BLACK);
	public static Scene itemshopScene = new Scene(itemshopPane, WIDTH, HEIGHT, Color.BLACK);
	public static Scene blacksmithScene = new Scene(blacksmithPane, WIDTH, HEIGHT, Color.BLACK);
	public static Scene battleScene = new Scene(battlePane, WIDTH, HEIGHT, Color.BLACK);
	public static Scene dungeonChooseFloorScene = new Scene(new DungeonChooseFloor(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene blackScreenScene = new Scene(new BlackScreen(), WIDTH, HEIGHT, Color.BLACK);
	public static Scene epilogueScene = new Scene(epilogueRoot, WIDTH, HEIGHT, Color.BLACK);
	public static Scene baseScene = new Scene(basePane , WIDTH, HEIGHT, Color.BLACK);
	
	public SceneManager() 
	{
	}
	
	public static Village getVillagePane() 
	{
		return villagePane;
	}
	
	public static ItemShop getItemshopPane() {
		return itemshopPane;
	}
	
	public static Battle getBattlePane()
	{
		return battlePane;
	}
	public static void reDungeonChooseFloor()
	{
		dungeonChooseFloorScene = new Scene(new DungeonChooseFloor(), WIDTH, HEIGHT, Color.BLACK);
		dungeonChooseFloorScene.getStylesheets().add(SceneManager.class.getResource("DungeonChooseFloorStyle.css").toExternalForm());
	}
	public static void reBattle()
	{
		battlePane = new Battle();
		battleScene = new Scene(battlePane, WIDTH, HEIGHT, Color.BLACK);
		battleScene.getStylesheets().add(SceneManager.class.getResource("BattleStyle.css").toExternalForm());
	}
	static
	{
		battleScene.getStylesheets().add(SceneManager.class.getResource("BattleStyle.css").toExternalForm());
		dungeonChooseFloorScene.getStylesheets().add(SceneManager.class.getResource("DungeonChooseFloorStyle.css").toExternalForm());
		prologueScene.getStylesheets().add(SceneManager.class.getResource("Prologue.css").toExternalForm());
		epilogueScene.getStylesheets().add(SceneManager.class.getResource("Epilogue.css").toExternalForm());
		mainScreenScene.getStylesheets().add(SceneManager.class.getResource("MainMenu.css").toExternalForm());
		blacksmithScene.getStylesheets().add(SceneManager.class.getResource("Shop.css").toExternalForm());
		itemshopScene.getStylesheets().add(SceneManager.class.getResource("Shop.css").toExternalForm());
	}
	
	public static void changeSceneToBattle()
	{
		StackPane root = new StackPane();
		final Rectangle rectBg = new Rectangle(WIDTH, HEIGHT, Color.WHITE);
		root.getChildren().addAll(battlePane, rectBg);
		Scene tempBattleScene = new Scene(root, WIDTH, HEIGHT);
		final Scene scene2 = tempBattleScene;
		FadeTransition ft = new FadeTransition(Duration.millis(500), Main.primaryStage.getScene().getRoot());
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setOnFinished
		(event->
			{
				FadeTransition ft2 = new FadeTransition(Duration.millis(560), rectBg);
				ft2.setFromValue(0);
				ft2.setToValue(1);
				ft2.play();
				PauseTransition pt = new PauseTransition(Duration.millis(30));
				pt.setOnFinished
				(event2->
					{
						Main.primaryStage.setScene(scene2);
						Main.primaryStage.show();
					}
				);
				pt.play();
			}
		);
		ft.play();
	}
	
	public static BlacksmithShop getBlacksmithShopPane() {
		return blacksmithPane;
	}
	
	public static Base getBasePane() {
		return basePane;
	}
}
