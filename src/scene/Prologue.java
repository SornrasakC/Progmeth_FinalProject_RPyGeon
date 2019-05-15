package scene;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.logics.Dungeon;
import main.Main;

public class Prologue extends StackPane
{
	public static int phase = 1;
	public static boolean inSlimeBattle = false;
	private int index1 = -1, index2 = -1;
	private ArrayList<Label> listLb1 = new ArrayList<Label>(), listLb2 = new ArrayList<Label>();
	private Label lb = new Label();
	private boolean calling = true, finish = false;
	private PauseTransition pt = new PauseTransition(Duration.millis(5500));
	public Prologue()
	{
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		pt.setOnFinished
		(event->
			{
				if(inSlimeBattle)
					return;
				if(phase == 1)
				{
					if(!calling && index1 < 5 && index1 > -1)
						call();
				}
				if(phase == 2)
				{
					if(!calling && index2 < 9 && index2 > -1)
						call();
				}
			}
		);
		getChildren().add(lb);
		StackPane.setAlignment(lb, Pos.CENTER);
		initListLb();
		setOnMouseClicked
		(event->
			{
				if(inSlimeBattle)
					return;
				if(phase == 1)
				{
					if(!calling && index1 < 5)
						call();
				}
				if(phase == 2)
				{
					if(!calling && index2 < 9)
						call();
				}
			}
		);
	}
	private void initListLb()
	{
		listLb1.add(init("You've woken up in an unknown place, dimly lit with a campfire"));//0
		listLb1.add(init("You looked around.... the only way you see is to go through a cave"));
		listLb1.add(init("You peeked around a corner,\nyou saw a squirming puddle of purple substance"));
		listLb1.add(init("It noticed you!\nThe mysterious puddle is dashing toward you at uncanny speed"));//3
		listLb1.add(init("You grabbed a wooden twig nearby and get ready for a fight!"));
		
		
		listLb2.add(init("It was a tough fight, but at least you survived... for now"));
		listLb2.add(init("You keep following a path\nuntil you found an opening area underground"));
		listLb2.add(init("You saw a small hamlet in the middle of the opening area,\na couple silhouettes shuffling about"));
		listLb2.add(init("You carefully approached the settlement,\nthe villagers look at you pitifully"));//3
		listLb2.add(init("You inquire them about what is this place and why are they here"));
		listLb2.add(init("The villagers say nobody in this village knows why are they here"));
		listLb2.add(init("The only thing they know\nis that there's a stair to go up inside the dungeon besides the village"));//6
		listLb2.add(init("They say that there might be a way out of this place\nat the end of that dungeon"));
		listLb2.add(init("You decided to use this village as your base\nand try to find a way back to the outside world"));//8
	}

	private Label init(String content)
	{
		Label lb = new Label(content);
		lb.getStyleClass().add("prologue");
		return lb;
	}

	public void call()
	{
		System.out.println("CALLED");
		calling = true;
		pt.stop();
		if(!finish)
			pt.play();
		FadeTransition ft1 = new FadeTransition(Duration.millis(1000), lb);
		ft1.setFromValue(1);
		ft1.setToValue(0);
		ft1.setOnFinished
		(event->
			{
				if(inSlimeBattle)
					return;
				if(phase == 1)
				{
					index1++;
					if(index1 >= 5)
					{
						SceneManager.prologueScene.setFill(Color.WHITE);
						inSlimeBattle = true;
						SceneManager.reBattle();
						Battle.setDungeon(Dungeon.getDungeonList().get(0));
						Battle.setMonster(Dungeon.generateSlime());
						Main.getBattleLogic().renewMonster(Battle.getMonster());
						Main.animation.stop();
						Main.battleAnimation.start();
						Battle.setPlayerTurn(true);
						Battle.setInAnimation(false); //???
						Battle.setFightNumber(3);
						Main.changeScene(SceneManager.battleScene);
						return;
					}
					lb = listLb1.get(index1);
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
								getChildren().remove(0);
							}catch(Exception e) {}
							getChildren().add(lb);
							StackPane.setAlignment(lb, Pos.CENTER);
						}
					);
					tempTime.play();
				}
				if(phase == 2)
				{
					index2++;
					if(index2 >= 9)
					{
						SceneManager.villageScene.setFill(Color.WHITE);
						Main.animation.start();
						Main.changeScene(SceneManager.villageScene);
						finish = true;
						PauseTransition temp = new PauseTransition(Duration.millis(2000));
						temp.setOnFinished(eventTemp -> SceneManager.villageScene.setFill(Color.BLACK));
						temp.play();
						return;
					}
					lb = listLb2.get(index2);
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
								getChildren().remove(0);
							}catch(Exception e) {}
							getChildren().add(lb);
							StackPane.setAlignment(lb, Pos.CENTER);
						}
					);
					tempTime.play();
				}
			}
		);
		ft1.play();
	}
	public void startCall()
	{
		call();
	}
}
