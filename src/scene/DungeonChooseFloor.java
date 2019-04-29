package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import logic.logics.Dungeon;
import logic.logics.Player;
import main.Main;

public class DungeonChooseFloor extends VBox
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	public DungeonChooseFloor()
	{
//		Canvas canvas = new Canvas(WIDTH, HEIGHT);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		getChildren().add(canvas);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		Dungeon.getAvailableFloor().forEach
		(dungeon ->
			{
				UiButton button = new UiButton(dungeon.getName());
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
		UiButton button = new UiButton("RETURN");
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
		setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(20))));
	}

}
