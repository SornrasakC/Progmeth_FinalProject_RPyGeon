package scene;

import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Pair;
import main.Main;
import sharedObject.RenderableHolder;

public class MainMenu extends Pane
{

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

//	private Label menuTitle = new Label("Main Menu");
	private VBox menuBox = new VBox(15);

	private List<Pair<String, Runnable>> menuList;

	public MainMenu() {
		
//		setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
//		setId("background");
//		Pane bgPane = new Pane();
//		BackgroundImage bgImage = new BackgroundImage(RenderableHolder.mainMenuBackground, null, null, null, new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));
//		bgPane.setBackground(new Background(bgImage));
//		bgPane.setEffect(new GaussianBlur());
//		this.getChildren().add(bgPane);
		
		menuList = Arrays.asList(new Pair<String, Runnable>("Start", () -> Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
//				Main.changeScene(SceneManager.villageScene);
				Main.changeScene(SceneManager.startNamingScene);
			}
		})), new Pair<String, Runnable>("Credits", () -> Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				Main.changeScene(SceneManager.creditScene);
			}
		})), new Pair<String, Runnable>("Exit", Platform::exit));
		
		addMenu(480, 300);
	} 
	
	private void addMenu(double x, double y)
	{	
		menuBox.setTranslateX(x);
		menuBox.setTranslateY(y);
		menuList.forEach
		(data -> 
			{
				MainMenuItem item = new MainMenuItem(data.getKey());
				item.setOnAction(data.getValue());
				Rectangle clip = new Rectangle(300, 30);
				item.setClip(clip);
	
				menuBox.getChildren().addAll(item);
			}
		);

		Label title = new Label("ReeJyGeon");
		title.setFont(new Font(50));
		title.setPrefWidth(WIDTH);
		title.setAlignment(Pos.CENTER);
//        title.setTranslateX(WIDTH / 2 - title.getWidth() / 2);
		title.setTranslateY(HEIGHT / 4);
//		Rectangle rectBG = new Rectangle(WIDTH, HEIGHT, Color.TRANSPARENT);
		ImageView imageBG = new ImageView(RenderableHolder.mainMenuBackground);
		imageBG.setFitHeight(HEIGHT);
		imageBG.setFitWidth(WIDTH);
		imageBG.setEffect(new GaussianBlur());
		this.getChildren().add(imageBG);
		this.getChildren().add(title);
		this.getChildren().add(menuBox);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception
//	{
//		this.primaryStage = primaryStage;
//		menuList = Arrays.asList(new Pair<String, Runnable>("Start", () -> Platform.runLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				primaryStage.setScene(villageScene);
//				primaryStage.show();
//			}
//		})), new Pair<String, Runnable>("Credits", () -> Platform.runLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				primaryStage.setScene(creditScene);
//				primaryStage.show();
//			}
//		})), new Pair<String, Runnable>("Exit", Platform::exit));
//		addMenu(480, 300);
//		Scene scene = new Scene(root);
//		Scene scene = menuScene();
//		primaryStage.setTitle("RJyGeon");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//	
//	public static void main(String[] args)
//	{
//		launch(args);
//	}
//	
//	public static Scene menuScene()
//	{
//		Label menuTitle = new Label("Main Menu");
//		Pane root = new Pane();
//		VBox menuBox = new VBox(15);
//		Scene villageScene = new Scene(new Village());
//		Scene creditScene = new Scene(new Credit());
//
//		
////		addMenu(480, 300);
//		menuBox.setTranslateX(480);
//		menuBox.setTranslateY(300);
//		menuList.forEach
//		(data -> 
//			{
//				MainMenuItem item = new MainMenuItem(data.getKey());
//				item.setOnAction(data.getValue());
//				Rectangle clip = new Rectangle(300, 30);
//				item.setClip(clip);
//	
//				menuBox.getChildren().addAll(item);
//			}
//		);
//
//		Label title = new Label("RJyGeon");
//		title.setFont(new Font(50));
//		title.setPrefWidth(WIDTH);
//		title.setAlignment(Pos.CENTER);
//		title.setTranslateY(HEIGHT / 4);
//		Rectangle rectBG = new Rectangle(WIDTH, HEIGHT, Color.WHITE);
//		root.getChildren().add(rectBG);
//		root.getChildren().add(title);
//		root.getChildren().add(menuBox);
//		return new Scene(root);
//	}
//
//	public static Stage getPrimaryStage()
//	{
//		return primaryStage;
//	}
//	
}
