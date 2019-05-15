package scene.battleOverlay;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import logic.logics.Player;
import sharedObject.RenderableHolder;

public class BattleAnimation extends Pane{
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private static final int IDLE_X = 270;
	private static final int IDLE_Y = 270;
	
	private ImageView playerSprite; 
	private ImageView MonsterSprite;
	private ImageView playerWeaponSprite;
	private Group playerSpriteGroup;
	
	private Thread idleThread;
	private int frame = 1;
	private TranslateTransition playerAttackMoveTransition;
	private ScaleTransition playerAttackScaleTransition;
	private RotateTransition playerAttackRotateTransition;
	private ParallelTransition playerAttackAnimation;
	
	private boolean isAnimating = false;
	
	
	public BattleAnimation() {
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		playerSpriteGroup = new Group();
		
		playerSprite = new ImageView(RenderableHolder.emiliaE1);
		playerSpriteGroup.getChildren().add(playerSprite);
		this.getChildren().add(playerSpriteGroup);
		playerSpriteGroup.setTranslateX(IDLE_X);
		playerSpriteGroup.setTranslateY(IDLE_Y);
		
		playerAttackMoveTransition = new TranslateTransition();
		playerAttackMoveTransition.setFromX(IDLE_X);
		playerAttackMoveTransition.setFromY(IDLE_Y);
		playerAttackMoveTransition.setToX(IDLE_X + 400);
//		playerAttackMoveTransition.setToY(IDLE_Y + 300);
		playerAttackMoveTransition.setDuration(Duration.seconds(0.5));
		playerAttackMoveTransition.setAutoReverse(true);
		playerAttackMoveTransition.setCycleCount(2);
//		playerAttackMoveTransition.setNode(playerSprite);
		playerAttackMoveTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				isAnimating = false;
			}
		});
		
		playerAttackRotateTransition = new RotateTransition();
		playerAttackRotateTransition.setToAngle(60);
		playerAttackRotateTransition.setDuration(Duration.seconds(0.5));
		playerAttackRotateTransition.setCycleCount(2);
		playerAttackRotateTransition.setAutoReverse(true);
//		playerAttackRotateTransition.setNode(playerSprite);
		playerAttackRotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				isAnimating = false;
			}
		});
		
		playerAttackScaleTransition = new ScaleTransition();
		playerAttackScaleTransition.setByX(10);
		playerAttackScaleTransition.setByY(10);
		playerAttackScaleTransition.setDuration(Duration.seconds(0.5));
		playerAttackScaleTransition.setCycleCount(2);
		playerAttackScaleTransition.setAutoReverse(true);
//		playerAttackScaleTransition.setNode(playerSprite);
		playerAttackScaleTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				isAnimating = false;
			}
		});
		
		playerAttackAnimation = new ParallelTransition(playerAttackMoveTransition, playerAttackRotateTransition, playerAttackScaleTransition);
		playerAttackAnimation.setNode(playerSprite);
		
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
				break;
			case(2):
				changeSprite(playerSprite, RenderableHolder.emiliaE2);
				break;
			}
		}
	}
	
	public void playAttackAnimation() {
		changeSprite(playerSprite, RenderableHolder.emiliaAttack);
		playerAttackAnimation.setNode(playerSpriteGroup);
		playerAttackAnimation.play();
		isAnimating = true;
		System.out.println("play attack animation");
	}
	
	private void changeSprite(ImageView imageView, Image image) {
		getChildren().clear();
//		imageView = new ImageView(image); 
		if(imageView.equals(playerSprite))
		{
			playerSprite = new ImageView(image);
			playerWeaponSprite = new ImageView(Player.player.getEquippedWeapon().getSprite());
			playerWeaponSprite.setTranslateX(playerSprite.getImage().getWidth() - 15);
			if(!isAnimating){
				//idle animation
				switch(frame)
				{
				case(1):
					playerWeaponSprite.setTranslateY(-12);
					break;
				case(2):
					playerWeaponSprite.setTranslateY(-10);
					break;
				}
			}else {
				playerWeaponSprite.setTranslateY(-12);
			}
			playerSpriteGroup.getChildren().clear();
			playerSpriteGroup.getChildren().addAll(playerSprite, playerWeaponSprite);
			this.getChildren().add(playerSpriteGroup);
		}
		else
		{
			MonsterSprite = new ImageView(image); 
			getChildren().add(MonsterSprite);
		}
//		this.getChildren().add(new ImageView(image));
		
	}

}
