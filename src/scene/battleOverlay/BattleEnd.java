package scene.battleOverlay;

import input.InputUtility;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.base.CustomException;
import logic.base.Droppable;
import logic.logics.Dungeon;
import logic.logics.Player;
import main.Main;
import scene.Battle;
import scene.Prologue;
import scene.SceneManager;
import sharedObject.BattleRenderableHolder;

public class BattleEnd extends StackPane
{
//	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	private int levelup = 0;
	private boolean leveluped = false;
	private boolean proceeded = false;
	private int moneyGain = 0;
	private String dropItemName = "NaN";
	private Label contLb, nameLb, expLb, moneyLb, dropLb, descLb;
	private VBox vBox;
	private Label[] statLb;
	public BattleEnd()
	{
		setBorder(new Border(new BorderStroke(Color.WHITESMOKE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));
		
		setBackground(new Background(new BackgroundImage(BattleRenderableHolder.battleEndBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT)));
		
		logicUpdate();
		nameLb = new Label(Battle.getMonster().getName() + " Has Been Defeated!");
		expLb = new Label("EXP gained : " + Player.player.getGainedEXP());
		moneyLb = new Label("MONEY gained : " + moneyGain);
		dropLb = new Label();
		
		contLb = new Label("Click Anywhere To Continue");
		contLbEff();
		if(!dropItemName.equals("NaN"))
		{
			dropLb = new Label(dropItemName + " GET!!");
		}
		expLb.setAlignment(Pos.CENTER);
		moneyLb.setAlignment(Pos.CENTER);
		dropLb.setAlignment(Pos.CENTER);
		
		vBox = new VBox();
		vBox.setSpacing(10);
		vBox.getChildren().addAll(expLb, moneyLb, dropLb);
		vBox.setAlignment(Pos.CENTER);
		addClass();
		
		getChildren().addAll(nameLb, vBox, contLb);
		StackPane.setAlignment(nameLb, Pos.TOP_CENTER);
		StackPane.setAlignment(contLb, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(vBox, Pos.CENTER);
		
		setOnMouseClicked
		(event->
			{
				if(!proceeded)
				{
					proceed();
					proceeded = true;
				}
			}
		);
	}
	private void logicUpdate()
	{
		if(Battle.getMonster().getLevel() == 12)
		{
			Main.changeScene(SceneManager.epilogueScene);
			SceneManager.epilogueRoot.startCall();
			Main.battleAnimation.stop();
			return;
		}
		moneyGain = Dungeon.dropMoney(Player.player, Battle.getMonster());
		levelup = Player.player.gainExp(Battle.getMonster());
		Object dropItem = Battle.getDungeon().dropItem();
		if(Player.player.gainItem(dropItem))
		{
			if(dropItem instanceof Droppable)
			{
				Droppable droppable = (Droppable) dropItem;
				dropItemName = droppable.getName();
			}
			else
			{
				new CustomException("dropItem not Droppable").printStackTrace();
			}
		}
	}
	
	private void proceed()
	{
		if(levelup > 0 || leveluped)
		{
			leveluped = true;
			this.getChildren().clear();
			vBox.getChildren().clear();
			nameLb = new Label("LEVEL UP!!");
			nameLb.getStyleClass().add("BattleEnd");
			
			initStatLb();
			descLb = new Label("Choose a stat for upgrade ( Points Left : " + levelup + " )");
			descLb.setAlignment(Pos.CENTER);
			descLb.getStyleClass().add("BattleEnd");
			
			vBox.getChildren().add(descLb);
			VBox.setMargin(descLb, new Insets(50, 0, 0, 0));
			for(Label i : statLb)
			{
				vBox.getChildren().add(i);
			}
			getChildren().addAll(nameLb, vBox);
			StackPane.setAlignment(nameLb, Pos.TOP_CENTER);
			StackPane.setAlignment(vBox, Pos.CENTER);
		}
		if(levelup <= 0)
		{
			if(Battle.getFightNumber() >= 3)
			{
				if(Prologue.inSlimeBattle)
				{
					SceneManager.prologueScene.setFill(Color.BLACK);
					Battle.getMonster().fullHeal();
					Player.player.fullHeal();
					InputUtility.clearInput();
					Main.battleAnimation.stop();
					PauseTransition temp = new PauseTransition(Duration.millis(1000));
					temp.setOnFinished(eventTemp -> Main.changeScene(SceneManager.prologueScene));
					temp.play();	
					Prologue.inSlimeBattle = false;
					Prologue.phase = 2;
					SceneManager.prologueRoot.call();
					return;
				}
				if(!Battle.getDungeon().isCleared())
				{
					Battle.getDungeon().setCleared(true);
					Player.player.setConqueredFloor(Player.player.getConqueredFloor() + 1);
				}
				Battle.backToVillage();
				return;
			}
			FadeTransition ft = new FadeTransition(Duration.millis(1000), SceneManager.battlePane);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(event -> proceed2());
			ft.play();
		}
	}
	private void proceed2()
	{
		FadeTransition ft = new FadeTransition(Duration.millis(1000), SceneManager.battlePane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
		Battle.setMonster(Battle.getDungeon().generateMonster());
		Main.getBattleLogic().renewMonster(Battle.getMonster());
		Battle.endOverlay();
		Battle.setPlayerTurn(true);
		Battle.setInAnimation(false); //???
		Battle.setFightNumber(Battle.getFightNumber() + 1);
	}
	private void addClass()
	{
		contLb.getStyleClass().add("BattleEnd");
		nameLb.getStyleClass().add("BattleEnd");
		expLb.getStyleClass().add("BattleEnd");
		moneyLb.getStyleClass().add("BattleEnd");
		dropLb.getStyleClass().add("BattleEnd");
		
	}
	private void initStatLb()
	{
		statLb = new Label[6];
		statLb[0] = new Label("+ Physical Attack(+1) " + (Player.player.getMinPhyAtk() - Player.player.getBoostMinPhyAtk()) + " ~ " + (Player.player.getMaxPhyAtk() - Player.player.getBoostMaxPhyAtk()));
		statLb[1] = new Label("+ Magical Attack(+1) " + (Player.player.getMinMagAtk() - Player.player.getBoostMinMagAtk()) + " ~ " + (Player.player.getMaxMagAtk() - Player.player.getBoostMaxMagAtk()));
		statLb[2] = new Label("+ Physical Defense(+1) " + (Player.player.getPhyDef() - Player.player.getBoostPhyDef()));
		statLb[3] = new Label("+ Magical Defense(+1) " + (Player.player.getMagDef() - Player.player.getBoostMagDef()));
		statLb[4] = new Label("+ Max HP(+16) " + Player.player.getMaxHp());
		statLb[5] = new Label("+ Max MP(+10) " + Player.player.getMaxMp());
		for(int i = 0; i < 6; i++)
		{
			final int ii = i;
			statLb[i].setAlignment(Pos.CENTER);
			statLb[i].getStyleClass().add("BattleEnd2");
			statLb[i].setOnMouseClicked
			(event->
				{
					if(levelup > 0)
					{
						Player.player.manualUpStat(ii);
						levelup--;
						proceed();
					}
				}
			);
		}
	}
	private void contLbEff()
	{
		Thread t = new Thread
		(()->
			{
				while(Battle.isInOverlay())
				{
					
					try
					{
						Thread.sleep(700);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Platform.runLater
					(()->
						{
							contLb.setOpacity(1 - contLb.getOpacity());
						}
					);
					
				}
			}
		);
		t.start();
	}
	
}
