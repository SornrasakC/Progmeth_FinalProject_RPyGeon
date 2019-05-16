package scene.battleOverlay;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import logic.base.StatType;
import logic.logics.Player;
import scene.Battle;
import sharedObject.BattleRenderableHolder;
import sharedObject.RenderableHolder;

public class BattleAnimation extends Pane{
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private static final int IDLE_X = 270;
	private static final int IDLE_Y = 270;
	
	private ImageView playerSprite; 
	private ImageView playerWeaponSprite;
	private Group playerSpriteGroup;
	private ImageView spellEffectSprite;
	
	private Thread idleThread;
	private int frame = 1;
	private TranslateTransition playerAttackMoveTransition;
	private ScaleTransition playerAttackScaleTransition;
	private RotateTransition playerAttackRotateTransition;
	private ParallelTransition playerAttackAnimation;
	private Timeline itemAnimation;
	
	private ParallelTransition playerSpellTransition;
	private RotateTransition playerSpellRotateTransition;
	private FadeTransition playerSpellFadeTransition;
	private ScaleTransition playerSpellScaleTransition;
	
	private boolean isAnimating = false;
	
	//to use in item eff
	private static int indexItem;
	
	public BattleAnimation() {
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		playerSpriteGroup = new Group();
		
		spellEffectSprite = new ImageView(BattleRenderableHolder.spellEffect);
		playerSprite = new ImageView(RenderableHolder.emiliaE1);
		playerSpriteGroup.getChildren().add(playerSprite);
		this.getChildren().add(playerSpriteGroup);
		playerSpriteGroup.setTranslateX(IDLE_X);
		playerSpriteGroup.setTranslateY(IDLE_Y);
		spellEffectSprite.setTranslateX(IDLE_X - (spellEffectSprite.getImage().getWidth() - playerSprite.getImage().getWidth()) / 2);
		spellEffectSprite.setTranslateY(IDLE_Y - (spellEffectSprite.getImage().getHeight() - playerSprite.getImage().getHeight()) / 2);
		
		//initialize attack animation
		playerAttackMoveTransition = new TranslateTransition();
		playerAttackMoveTransition.setFromX(IDLE_X);
		playerAttackMoveTransition.setFromY(IDLE_Y);
		playerAttackMoveTransition.setToX(IDLE_X + 400);
//		playerAttackMoveTransition.setToY(IDLE_Y + 300);
		playerAttackMoveTransition.setDuration(Duration.seconds(0.5));
		playerAttackMoveTransition.setAutoReverse(true);
		playerAttackMoveTransition.setCycleCount(2);

		
		playerAttackRotateTransition = new RotateTransition();
		playerAttackRotateTransition.setToAngle(60);
		playerAttackRotateTransition.setDuration(Duration.seconds(0.5));
		playerAttackRotateTransition.setCycleCount(2);
		playerAttackRotateTransition.setAutoReverse(true);

		
		playerAttackScaleTransition = new ScaleTransition();
		playerAttackScaleTransition.setByX(10);
		playerAttackScaleTransition.setByY(10);
		playerAttackScaleTransition.setDuration(Duration.seconds(0.5));
		playerAttackScaleTransition.setCycleCount(2);
		playerAttackScaleTransition.setAutoReverse(true);

		
		playerAttackAnimation = new ParallelTransition(playerAttackMoveTransition, playerAttackRotateTransition, playerAttackScaleTransition);
		playerAttackAnimation.setNode(playerSprite);
		playerAttackAnimation.setOnFinished
		(event->
			{
				isAnimating = false;
//				Battle.setInAnimation(true);
				PauseTransition pauseForAnimation = new PauseTransition(Duration.millis(500));
				pauseForAnimation.setOnFinished(pause -> Battle.setPlayerTurn(false));
				pauseForAnimation.play();
//				playerTurn = false;
				
				int damage = Battle.getMonster().receiveDamage(Player.player.randPhyAtk(), StatType.PHYATK);
				Battle.playerReport(Player.player.getName() + " deals " + damage + " damage!");
			}
		);
		
		//initialize spell animation
		KeyValue scaleKVx = new KeyValue(spellEffectSprite.scaleXProperty(), 3);
		KeyValue scaleKVy = new KeyValue(spellEffectSprite.scaleYProperty(), 3);
		KeyFrame scaleKF = new KeyFrame(Duration.seconds(0.5), scaleKVx, scaleKVy);
		Timeline scaleup = new Timeline();
		scaleup.getKeyFrames().add(scaleKF);
		
		playerSpellRotateTransition = new RotateTransition(Duration.seconds(0.5));
		playerSpellRotateTransition.setInterpolator(Interpolator.LINEAR);
		playerSpellRotateTransition.setByAngle(360);
		
		playerSpellFadeTransition = new FadeTransition(Duration.seconds(0.5));
		playerSpellFadeTransition.setFromValue(1.0);
		playerSpellFadeTransition.setToValue(0.2);
		
		playerSpellTransition = new ParallelTransition(scaleup, playerSpellRotateTransition, playerSpellFadeTransition);
		playerSpellTransition.setNode(spellEffectSprite);
		playerSpellTransition.setOnFinished(event -> {
			isAnimating = false;
			spellEffectSprite.setVisible(false);
			
			PauseTransition pauseForAnimation = new PauseTransition(Duration.millis(500));
			pauseForAnimation.setOnFinished(pause -> Battle.setPlayerTurn(false));
			pauseForAnimation.play();
		});
		
		itemAnimation = new Timeline();
		itemAnimation.setCycleCount(4);
        KeyFrame kf = new KeyFrame
        		(Duration.millis(250),
	                event-> 
	        		{
	        			getChildren().clear();
	        			playerSpriteGroup.getChildren().clear();
	        			playerSpriteGroup.getChildren().addAll(playerSprite, playerWeaponSprite, BattleRenderableHolder.itemAnimations[indexItem]);
	        			getChildren().add(playerSpriteGroup);
	        			indexItem++;
		            }
        		);
        itemAnimation.getKeyFrames().add(kf);
		itemAnimation.setOnFinished(event-> isAnimating = false);
		
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
				changeSprite(RenderableHolder.emiliaE1);
				System.out.println("idle 1");
				break;
			case(2):
				changeSprite(RenderableHolder.emiliaE2);
				System.out.println("idle 2");
				break;
			}
		}
		
	}
	
	public void playAttackAnimation() {
		spellEffectSprite.setVisible(true);
		changeSprite(RenderableHolder.emiliaAttack);
		playerAttackAnimation.setNode(playerSpriteGroup);
		playerAttackAnimation.play();
		isAnimating = true;
		//fix
		getChildren().clear();
		playerSpriteGroup.getChildren().clear();
		playerSpriteGroup.getChildren().addAll(playerSprite, playerWeaponSprite);
		getChildren().add(playerSpriteGroup);

		System.out.println("play attack animation");
	}
	
	public void playSpellAnimation() {
		playerSpellTransition.setNode(spellEffectSprite);
		playerSpellTransition.play();
		isAnimating = true;
		
		//fix
		getChildren().clear();
		playerSpriteGroup.getChildren().clear();
		playerSpriteGroup.getChildren().addAll(playerSprite, playerWeaponSprite, spellEffectSprite);
		getChildren().add(playerSpriteGroup);

		System.out.println("play spell animation");
	}
	
	private void changeSprite(Image image) {
		getChildren().clear();
//		imageView = new ImageView(image); 
		playerSprite = new ImageView(image);
		playerWeaponSprite = new ImageView(Player.player.getEquippedWeapon().getSprite());
		playerWeaponSprite.setTranslateX(playerSprite.getImage().getWidth() - 15);
		if(!isAnimating)
		{
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
		}
		else 
		{
			playerWeaponSprite.setTranslateY(-12);
		}
		playerSpriteGroup.getChildren().clear();
		playerSpriteGroup.getChildren().addAll(playerSprite, playerWeaponSprite);
		getChildren().add(playerSpriteGroup);
//		this.getChildren().add(new ImageView(image));
//		this.getChildren().add(spellEffectSprite);
	}
	public void playItemAnimation()
	{
		isAnimating = true;
		indexItem = 0;
        itemAnimation.play();
	}
}
