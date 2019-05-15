package scene;

import java.util.ArrayList;

import entity.VillageEntityLogic;
import input.InputUtility;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.base.Monster;
import logic.base.StatType;
import logic.logics.Dungeon;
import logic.logics.Player;
import logic.logics.Rand;
import main.Main;
import scene.battleOverlay.BattleAnimation;
import scene.battleOverlay.BattleEnd;
import scene.battleOverlay.ItemList;
import scene.battleOverlay.SpellList;
import sharedObject.BattleRenderableHolder;
import sharedObject.IRenderable;

public class Battle extends GridPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static Monster monster = Dungeon.getMonsterList().get(0);
	private static Dungeon dungeon = Dungeon.getDungeonList().get(0);
	private Button attackButton, spellButton, itemButton, escapeButton;
	private static Canvas battleCanvas;
	private static ObservableList<Label> logDataList;
	private static ListView<Label> listView;
//	private static Canvas overlayCanvas;
	private static StackPane stackPane;
	private static VBox spellList, itemList;
	private static StackPane battleEnd;
	private static boolean inOverlay;
	private static boolean playerTurn;
	private static boolean inAnimation;
	private static int fightNumber;
	private static BattleAnimation battleAnimation;
	
	public Battle()
	{
		inOverlay = false;
		playerTurn = true;
		inAnimation = false;
		fightNumber = 1;
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		battleCanvas = new Canvas(WIDTH * 4 / 5,HEIGHT * 4 / 5);
		
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.drawImage(BattleRenderableHolder.dungeonBackgrounds[dungeon.getFloor()], 0, 0);
//		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("dungeon1.png")), 0, 0);
		
		stackPane = new StackPane();
		stackPane.getChildren().add(battleCanvas);
		
		logDataList = FXCollections.observableArrayList();
				
		listView = new ListView<Label>(logDataList);
		listView.setPrefWidth(WIDTH / 5);
		
		attackButton = new Button("ATTACK");
		spellButton = new Button("SPELL");
		itemButton = new Button("ITEM");
		escapeButton = new Button("ESCAPE");
		
		ArrayList<Button> buttonList = new ArrayList<Button>();
		buttonList.add(attackButton);
		buttonList.add(spellButton);
		buttonList.add(itemButton);
		buttonList.add(escapeButton);
		
		buttonList.forEach
		(button -> 
			{
				button.setPrefSize(WIDTH / 5 - 5, HEIGHT * 9 / 10 - 5);
				button.setFont(new Font(40));
				GridPane.setMargin(button, new Insets(5, 0, 5, 5));
			}
		);
		GridPane.setMargin(escapeButton, new Insets(5, 5, 5, 5));

		add(stackPane, 0, 0, 4, 1);
		add(listView, 4, 0, 1, 2);
		add(attackButton, 0, 1);
		add(spellButton, 1, 1);
		add(itemButton, 2, 1);
		add(escapeButton, 3, 1);
		//test animation
		battleAnimation = new BattleAnimation();
		stackPane.getChildren().add(battleAnimation);
		//test animation
		
		
		setAttack(attackButton);
		setSpell(spellButton);
		setItem(itemButton);
		setEscape(escapeButton);
		setListViewBackground();
		setListViewCss();
		
		attackButton.getStyleClass().addAll("attackButton", "actionButton");
		spellButton.getStyleClass().addAll("spellButton", "actionButton");
		itemButton.getStyleClass().addAll("itemButton", "actionButton");
		escapeButton.getStyleClass().addAll("escapeButton", "actionButton");
	}
	
	private void setAttack(Button attackButton)
	{
		attackButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					battleAnimation.playAttackAnimation();
					playerTurn = false;
					inAnimation = true;
					int damage = monster.receiveDamage(Player.player.randPhyAtk(), StatType.PHYATK);
					playerReport(Player.player.getName() + " deals " + damage + " damage!");
				}
			}
		);
	}
	private void setSpell(Button spellButton)
	{
		spellButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					spellList = new SpellList();
					startOverlay();
					stackPane.getChildren().add(spellList);
					FadeTransition fade = new FadeTransition(Duration.millis(500), spellList);
					fade.setFromValue(0);
					fade.setToValue(1);
					fade.play();
					
					spellList.setMaxSize(WIDTH * 3 / 5, HEIGHT * 3 / 5);
					
					StackPane.setAlignment(spellList, Pos.CENTER);
				}
			}
		);
	}
	private void setItem(Button itemButton)
	{
		itemButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					itemList = new ItemList();
					startOverlay();
					stackPane.getChildren().add(itemList);
					FadeTransition fade = new FadeTransition(Duration.millis(500), itemList);
					fade.setFromValue(0);
					fade.setToValue(1);
					fade.play();
					
					itemList.setMaxSize(WIDTH * 3 / 5, HEIGHT * 3 / 5);
					StackPane.setAlignment(itemList, Pos.CENTER);
				}
			}
		);
	}
	private void setEscape(Button escapeButton)
	{
		escapeButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{	
					if(dungeon.getFloor() > 5)
					{
						systemReport("You are not allowed to escape from the boss!!");
					}
					else
					{
						playerTurn = false;
						if(Rand.chance(50))
						{
							backToVillage();		
						}
						else
						{
							systemReport("Escape Failed!!");
						}
					}
				}
			}
		);
	}
	public static void backToVillage()
	{
		Battle.monster.fullHeal();
		Player.player.fullHeal();
		VillageEntityLogic.exitDungeon();
		InputUtility.clearInput();
		Main.battleAnimation.stop();
		Main.animation.start();
		Main.changeScene(SceneManager.villageScene);
		
	}
	public void paintCanvas() 
	{
		//test
		battleAnimation.update();
		//test
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		for (IRenderable entity : BattleRenderableHolder.getInstance().getEntities())
		{
			if (entity.isVisible())
			{
				entity.draw(gc);
			}
		}
		if(Player.player.isDead() || monster.isDead())
		{
			
			if(Player.player.isDead())
			{
				System.exit(0);
			}
			else if(!inOverlay)
			{
				startOverlay();
				battleEnd = new BattleEnd();
				
				FadeTransition ftwait = new FadeTransition(Duration.millis(100), battleEnd);
				ftwait.setOnFinished
				((event)->
					{
						
						stackPane.getChildren().add(battleEnd);
					}
				);
				ftwait.play();
				FadeTransition ft = new FadeTransition(Duration.millis(2100), battleEnd);
				ft.setFromValue(0.0);
				ft.setToValue(1.0);
				ft.play();
				
				battleEnd.setMaxSize(WIDTH * 3 / 5, HEIGHT * 3 / 5);
				StackPane.setAlignment(battleEnd, Pos.CENTER);
			}
		}
		if(!playerTurn || inAnimation || inOverlay)
		{
			attackButton.setDisable(true);
			spellButton.setDisable(true);
			itemButton.setDisable(true);
			escapeButton.setDisable(true);
		}
		else
		{
			attackButton.setDisable(false);
			spellButton.setDisable(false);
			itemButton.setDisable(false);
			escapeButton.setDisable(false);
		}
	}
	public void drawBackground()
	{
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("dungeon" + dungeon.getFloor() + ".png")), 0, 0);
	}
	
	public static void startOverlay()
	{
		battleCanvas.setEffect(new GaussianBlur());
		inOverlay = true;
	}
	public static void endOverlay()
	{
		battleCanvas.setEffect(null);
		stackPane.getChildren().clear();
		stackPane.getChildren().add(battleCanvas);
		//test animation
		stackPane.getChildren().add(battleAnimation);
		//test animation
		inOverlay = false;
	}
	
	public static void playerReport(String report)
	{
		report = formatReport(report);
		Label label = new Label(report);
		label.getStyleClass().add("PlayerReport");
		Battle.logDataList.add(0, label);
//		LogManager.getLogManager().reset();
	}
	public static void monsterReport(String report)
	{
		report = formatReport(report);
		Label label = new Label(report);
		label.getStyleClass().add("MonsterReport");
		Battle.logDataList.add(0, label);
//		LogManager.getLogManager().reset();
	}
	public static void systemReport(String report)
	{
		report = formatReport(report);
		Label label = new Label(report);
		label.getStyleClass().add("SystemReport");
		Battle.logDataList.add(0, label);
//		LogManager.getLogManager().reset();
	}
	private static String formatReport(String report)
	{
		int count = 0;
		String newReport = "";
		String[] inputs = report.split(" ");
		for(String input : inputs)
		{
			count += input.length() + 1;
			if(input.length() > 21)
			{
				newReport += "\n" + input + "\n";
				count = 0;
				continue;
			}
			if(count > 21)
			{
				newReport += "\n" + input;
				count = input.length();
				continue;
			}
			newReport += " " + input;

		}
		return newReport.substring(1);
	}
	private static void setListViewBackground()
	{
		for(int i = 0; i < 32; i++)
		{
			Label label = new Label(" ");
			label.getStyleClass().add("Fill");
			Battle.logDataList.add(0, label);
		}

	}
	private void setListViewCss()
	{
		listView.setCellFactory(x ->
			{
				ListCell<Label> cell = new ListCell<Label>()
				{
					
					@Override
					protected void updateItem(Label item, boolean empty)
					{
						super.updateItem(item, empty);
						if (empty || item == null)
						{
							// There is no item to display in this cell, so leave it empty
							setGraphic(null);

							// Clear the style from the cell
							setStyle(null);
						}
						else
						{
							if (item.getStyleClass().contains("PlayerReport"))
							{
								System.out.println("GETPlayer");
								getStyleClass().removeAll("PlayerReport", "MonsterReport", "SystemReport", "Fill");
								getStyleClass().add("PlayerReport");
							}
							if (item.getStyleClass().contains("MonsterReport"))
							{
								System.out.println("GETMonster");
								getStyleClass().removeAll("PlayerReport", "MonsterReport", "SystemReport", "Fill");
								getStyleClass().add("MonsterReport");
							}
							if (item.getStyleClass().contains("SystemReport"))
							{
								getStyleClass().removeAll("PlayerReport", "MonsterReport", "SystemReport", "Fill");
								getStyleClass().add("SystemReport");
							}
							if (item.getStyleClass().contains("Fill"))
							{
								getStyleClass().removeAll("PlayerReport", "MonsterReport", "SystemReport", "Fill");
								getStyleClass().add("Fill");
							}
							// Finally, show the item text in the cell
							setText(item.getText());

						}
					}
				};
				return cell;
			});
	}
	public Button getAttackButton()
	{
		return attackButton;
	}
	public Button getSpellButton()
	{
		return spellButton;
	}
	public Button getItemButton()
	{
		return itemButton;
	}
	public Button getEscapeButton()
	{
		return escapeButton;
	}
	public Canvas getBattleCanvas()
	{
		return battleCanvas;
	}
	public static Monster getMonster()
	{
		return monster;
	}
	public static Dungeon getDungeon()
	{
		return dungeon;
	}
	public static void setMonster(Monster monster)
	{
		if(monster.getLevel() == 12)
		{
			dungeon.setFloor(7);
			Player.player.resetStatPotionEffects();
			systemReport("Gate Guardian reset your stats!!");
		}
		Battle.monster.fullHeal();
		Battle.monster = monster;
		
	}
	public static void setDungeon(Dungeon dungeon)
	{
		Battle.dungeon = dungeon;
	}

	public static ObservableList<Label> getLogDataList()
	{
		return logDataList;
	}

	public static void setLogDataList(ObservableList<Label> logDataList)
	{
		Battle.logDataList = logDataList;
	}

	public static ListView<Label> getListView()
	{
		return listView;
	}

	public static void setListView(ListView<Label> listView)
	{
		Battle.listView = listView;
	}

	public static StackPane getStackPane()
	{
		return stackPane;
	}

	public static void setStackPane(StackPane stackPane)
	{
		Battle.stackPane = stackPane;
	}

	public static VBox getSpellList()
	{
		return spellList;
	}

	public static void setSpellList(VBox spellList)
	{
		Battle.spellList = spellList;
	}

	public static VBox getItemList()
	{
		return itemList;
	}

	public static void setItemList(VBox itemList)
	{
		Battle.itemList = itemList;
	}

	public static boolean isInOverlay()
	{
		return inOverlay;
	}

	public static void setInOverlay(boolean inOverlay)
	{
		Battle.inOverlay = inOverlay;
	}

	public static boolean isPlayerTurn()
	{
		return playerTurn;
	}

	public static void setPlayerTurn(boolean playerTurn)
	{
		Battle.playerTurn = playerTurn;
	}

	public static boolean isInAnimation()
	{
		return inAnimation;
	}

	public static void setInAnimation(boolean inAnimation)
	{
		Battle.inAnimation = inAnimation;
	}

	public void setAttackButton(Button attackButton)
	{
		this.attackButton = attackButton;
	}

	public void setSpellButton(Button spellButton)
	{
		this.spellButton = spellButton;
	}

	public void setItemButton(Button itemButton)
	{
		this.itemButton = itemButton;
	}

	public void setEscapeButton(Button escapeButton)
	{
		this.escapeButton = escapeButton;
	}

	public static void setBattleCanvas(Canvas battleCanvas)
	{
		Battle.battleCanvas = battleCanvas;
	}

	public static StackPane getBattleEnd()
	{
		return battleEnd;
	}

	public static void setBattleEnd(StackPane battleEnd)
	{
		Battle.battleEnd = battleEnd;
	}

	public static int getFightNumber()
	{
		return fightNumber;
	}

	public static void setFightNumber(int fightNumber)
	{
		Battle.fightNumber = fightNumber;
	}
	
}

