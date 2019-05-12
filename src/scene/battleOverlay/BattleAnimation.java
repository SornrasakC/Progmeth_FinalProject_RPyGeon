package scene.battleOverlay;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import sharedObject.RenderableHolder;

public class BattleAnimation extends Pane{
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private static final int IDLE_X = 270;
	private static final int IDLE_Y = 270;
	
	private ImageView playerSprite; 
	private ImageView MonsterSprite;
	
	private Thread idleThread;
	private int frame = 1;
	private TranslateTransition testTransition;
	
	private boolean isAnimating = false;
	private boolean isTestFin = false;
	
	
	public BattleAnimation() {
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		
		playerSprite = new ImageView(RenderableHolder.emiliaE1);
		this.getChildren().add(playerSprite);
		playerSprite.setTranslateX(IDLE_X);
		playerSprite.setTranslateY(IDLE_Y);
		
		testTransition = new TranslateTransition();
		testTransition.setFromX(IDLE_X);
		testTransition.setFromY(IDLE_Y);
		testTransition.setToX(IDLE_X + 300);
		testTransition.setToY(IDLE_Y + 300);
		testTransition.setDuration(Duration.seconds(0.5));
		testTransition.setAutoReverse(true);
		testTransition.setCycleCount(2);
		testTransition.setNode(playerSprite);
		testTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				isAnimating = false;
			}
		});
		
		idleThread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(700);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Platform.runLater(() -> frame = 3 - frame);
				}
			}
		});
		idleThread.start();
	}
	
	public void update()
	{
		if(!isAnimating){
			//idle animation
			switch(frame)
			{
			case(1):
				changeSprite(playerSprite, RenderableHolder.emiliaE1);
				playerSprite.setTranslateX(IDLE_X);
				playerSprite.setTranslateY(IDLE_Y);
				break;
			case(2):
				changeSprite(playerSprite, RenderableHolder.emiliaE2);
				playerSprite.setTranslateX(IDLE_X);
				playerSprite.setTranslateY(IDLE_Y);
				break;
			}
		}
	}
	
	public void playAttackAnimation() {
		changeSprite(playerSprite, RenderableHolder.genericVendor);
		testTransition.setNode(playerSprite);
		testTransition.play();
		isAnimating = true;
		System.out.println("play attack animation");
	}
	
	private void changeSprite(ImageView imageView, Image image) {
		this.getChildren().clear();
		imageView = new ImageView(image); 
		this.getChildren().add(imageView);
		
	}

}
