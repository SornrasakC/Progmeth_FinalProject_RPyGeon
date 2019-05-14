package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Main;

public class ConfirmPrologue extends StackPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	public ConfirmPrologue()
	{
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getChildren().add(canvas);
		setCanvas(gc);
	}
	private void setCanvas(GraphicsContext gc)
	{
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		
		Label label = new Label("Skip the prologue?");
		label.setTextFill(Color.WHITE);
		label.setFont(new Font(30));
		label.setTranslateY(-HEIGHT/5);
		
		UiButton yesButton = new UiButton("YES");
		yesButton.setFont(new Font(30));
		yesButton.setTranslateX(-60);
		yesButton.setTextFill(Color.DARKRED);
		yesButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					Main.changeScene(SceneManager.villageScene);
					
				}
			}
		);
		
		UiButton noButton = new UiButton("NO");
		noButton.setFont(new Font(30));
		noButton.setTranslateX(60);
		noButton.setTextFill(Color.GREEN);
		noButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					Main.changeScene(SceneManager.prologueScene);
					SceneManager.prologueRoot.call();
				}
			}
		);
		
		getChildren().addAll(label, yesButton, noButton);
		StackPane.setAlignment(label, Pos.CENTER);
		StackPane.setAlignment(yesButton, Pos.CENTER);
		StackPane.setAlignment(noButton, Pos.CENTER);
	}
	
}
