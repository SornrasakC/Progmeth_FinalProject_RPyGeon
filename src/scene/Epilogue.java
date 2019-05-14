package scene;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sharedObject.RenderableHolder;

public class Epilogue extends StackPane
{
	private int index = -1;
	private ArrayList<Label> listLb = new ArrayList<Label>();
	private Label lb = new Label();
	private boolean calling = true;
	private PauseTransition pt = new PauseTransition(Duration.millis(4500));
	private TranslateTransition tt = new TranslateTransition(Duration.seconds(6.5 * 7));
	private ImageView background = new ImageView(RenderableHolder.epilogueBackground); // 1080 -> 720
	public Epilogue()
	{
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		tt.setNode(background);
		tt.setFromY(-360);
		tt.setToY(0);
		pt.setOnFinished
		(event->
			{
				if(!calling && index < 9 && index > -1)
					call();
			}
		);
		getChildren().addAll(background, lb);
		StackPane.setAlignment(background, Pos.TOP_LEFT);
		StackPane.setAlignment(lb, Pos.CENTER);
		initListLb();
		setOnMouseClicked
		(event->
			{
				if(!calling && index < 10)
					call();
			}
		);
	}

	private void initListLb()
	{
		listLb.add(init("As the Guardian Gate fallen you hear a loud creaking sound"));//0
		listLb.add(init("You saw the gate at the end of the room\n slowly opening, swinging toward you"));
		listLb.add(init("You walked toward the opening gate"));
		listLb.add(init("You can see the light shining through the gap of the door"));//3
		listLb.add(init("You've walked through the door, the prairie's now before your eyes"));
		listLb.add(init("You turned back\n and the gate that you escaped from is now nowhere to be seen"));
		listLb.add(init("But now that you're free,\n you're no longer confined under the prison of dirt"));//6
		listLb.add(init(
				"There is so much to be done, so much to be discovered,\n you started walking toward the setting sun..."));
		listLb.add(init("Congratulation! You've finished the game"));
		listLb.add(init("CLICK TO EXIT"));//9
	}

	private Label init(String content)
	{
		Label lb = new Label(content);
		lb.getStyleClass().add("epilogue");
		return lb;
	}

	public void call()
	{
		System.out.println("CALLED");
		calling = true;
		pt.stop();
		pt.play();
		FadeTransition ft1 = new FadeTransition(Duration.millis(1000), lb);
		ft1.setFromValue(1);
		ft1.setToValue(0);
		ft1.setOnFinished
		(event->
			{
				index++;
				if(index >= 10)
				{
					System.exit(0);
				}
				lb = listLb.get(index);
				FadeTransition ft2 = new FadeTransition(Duration.millis(1000), lb);
				ft2.setFromValue(0);
				ft2.setToValue(1);
				ft2.setOnFinished
				(event2->
					{
						calling = false;
					}
				);
				ft2.play();
				PauseTransition tempTime = new PauseTransition(Duration.millis(30));
				tempTime.setOnFinished
				(event3->
					{
						try
						{
							getChildren().remove(1);
						}catch(Exception e) {}
						getChildren().add(lb);
						StackPane.setAlignment(lb, Pos.CENTER);
					}
				);
				tempTime.play();
			}
		);
		ft1.play();
	}
	public void startCall()
	{
		call();
		tt.play();
	}
}
