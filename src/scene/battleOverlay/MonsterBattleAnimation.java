package scene.battleOverlay;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import logic.logics.Player;
import scene.Battle;

public class MonsterBattleAnimation extends Pane
{

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	private static final int IDLE_X = 770;
	private static final int IDLE_Y = 270;

	private ImageView monsterSprite;
	private Group monsterSpriteGroup;

	private Thread idleThread;
	private int frame = 1;
	private TranslateTransition monsterAttackTransition;

	private boolean isAnimating = false;

	public MonsterBattleAnimation()
	{
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);

		monsterSprite = new ImageView(Battle.getMonster().getSprite());
		
		monsterSpriteGroup = new Group();
		monsterSpriteGroup.setTranslateX(IDLE_X);
		monsterSpriteGroup.setTranslateY(IDLE_Y);
		
		monsterSpriteGroup.getChildren().add(monsterSprite);

		monsterAttackTransition = new TranslateTransition();
		monsterAttackTransition.setFromX(IDLE_X);
		monsterAttackTransition.setFromY(IDLE_Y);
		monsterAttackTransition.setToX(IDLE_X - 300);
//		monsterAttackTransition.setToY(IDLE_Y + 300);
		monsterAttackTransition.setDuration(Duration.seconds(0.3));
		monsterAttackTransition.setAutoReverse(true);
		monsterAttackTransition.setCycleCount(2);

		monsterAttackTransition.setNode(monsterSpriteGroup);
		monsterAttackTransition.setOnFinished(event ->
			{
				isAnimating = false;
				Battle.setInAnimation(false); 
				Battle.setPlayerTurn(true);
				int damage = Battle.getMonster().attack(Player.player);
				Battle.monsterReport(Battle.getMonster().getName() + " attacked " + Player.player.getName() + " for " + damage + "!");
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
		if (!isAnimating)
		{
			// idle animation
			switch (frame)
			{
				case (1):
					changeSprite(Battle.getMonster().getSprite());
					break;
				case (2):
					changeSprite(Battle.getMonster().getSpriteAnimation());
					break;
			}
		}
		if(!Battle.isPlayerTurn() && !Battle.getMonster().isDead())
		{
			if(!isAnimating)
			{
				isAnimating = true;
				playAttackAnimation();
			}
		}
	}

	public void playAttackAnimation()
	{
//		changeSprite( Battle.getMonster());
		monsterAttackTransition.play();
		isAnimating = true;
		System.out.println("play attack animation");
	}

	private void changeSprite(Image image)
	{
		getChildren().clear();
//		getChildren().clear();
//		imageView = new ImageView(image); 
		
//			playerSpriteGroup.getChildren().clear();
//			playerSpriteGroup.getChildren().addAll(playerSprite, playerWeaponSprite);
//			this.getChildren().add(playerSpriteGroup);
		monsterSprite = new ImageView(image);
		monsterSpriteGroup.getChildren().clear();
		monsterSpriteGroup.getChildren().add(monsterSprite);
		getChildren().add(monsterSpriteGroup);
//		getChildren().add(monsterSprite);
//		this.getChildren().add(new ImageView(image));

	}

}
