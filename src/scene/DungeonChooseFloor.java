package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.logics.Dungeon;
import main.Main;

public class DungeonChooseFloor extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	public DungeonChooseFloor()
	{
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
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
							Battle.setMonster(dungeon.generateMonster());
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
