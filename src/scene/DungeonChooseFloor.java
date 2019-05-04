package scene;

import entity.BattleEntityLogic;
import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import logic.base.Monster;
import logic.logics.Dungeon;
import main.Main;

public class DungeonChooseFloor extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	public DungeonChooseFloor()
	{
//		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResourceAsStream("chooseDungeon1.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT)));
		Dungeon.getAvailableFloor().forEach
		(dungeon ->
			{
				Button button = new Button(dungeon.getName());
				button.setAlignment(Pos.CENTER);
				button.setPrefWidth(WIDTH);
				button.setOnAction
				(
					new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent event)
						{
							Monster monster = dungeon.generateMonster();
							Battle.setMonster(monster);
							Battle.setDungeon(dungeon);
							Main.getBattleLogic().renewMonster(monster);
							Main.animation.stop();
							Main.battleAnimation.start();
							Main.changeScene(SceneManager.battleScene);
							
						}
					}
				);
				getChildren().add(button);
			}
		);
		Button button = new Button("RETURN");
		button.setAlignment(Pos.CENTER);
		button.setPrefWidth(WIDTH);
		button.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					VillageEntityLogic.exitDungeon();
					Main.changeScene(SceneManager.villageScene);
				}
			}
		);
		getChildren().add(button);
		setSpacing(10);

	}
	
}
