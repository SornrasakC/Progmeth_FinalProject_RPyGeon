package scene;

import java.util.ArrayList;
import java.util.logging.LogManager;

import entity.VillageEntityLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.base.Monster;
import logic.base.StatType;
import logic.logics.Dungeon;
import logic.logics.Player;
import logic.logics.Rand;
import main.Main;
import scene.battleOverlay.SpellList;
import sharedObject.BattleRenderableHolder;
import sharedObject.IRenderable;

public class Battle extends GridPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static Monster monster;
	private static Dungeon dungeon;
	private UiButton attackButton, spellButton, itemButton, escapeButton;
	private boolean playerTurn = true;
	private static Canvas battleCanvas;
	private static ObservableList<Label> logDataList;
	private static ListView<Label> listView;
	private static Canvas overlayCanvas;
	private static StackPane stackPane;
	private static VBox spellList, itemList;
	
	public Battle()
	{
//		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		battleCanvas = new Canvas(WIDTH * 4 / 5,HEIGHT * 4 / 5);
		
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("dungeon1.png")), 0, 0);
		
		stackPane = new StackPane();
		stackPane.getChildren().add(battleCanvas);
		
		overlayCanvas = new Canvas(WIDTH * 4 / 5,HEIGHT * 4 / 5);
		GraphicsContext gc2 = overlayCanvas.getGraphicsContext2D();
		gc2.setFill(Color.BLACK);
		gc2.fillRect(0, 0, WIDTH * 4 / 5, HEIGHT * 4 / 5);
		overlayCanvas.setOpacity(0.6);
		
		logDataList = FXCollections.observableArrayList();
				
		listView = new ListView<Label>(logDataList);
		listView.setPrefWidth(WIDTH / 5);
		
		Label label1 = new Label("asdasd");
		logDataList.add(label1);
		
		attackButton = new UiButton("ATTACK");
		spellButton = new UiButton("SPELL");
		itemButton = new UiButton("ITEM");
		escapeButton = new UiButton("ESCAPE");
		
		ArrayList<UiButton> buttonList = new ArrayList<UiButton>();
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

//		add(battleCanvas, 0, 0, 4, 1);
		add(stackPane, 0, 0, 4, 1);
		add(listView, 4, 0, 1, 2);
		add(attackButton, 0, 1);
		add(spellButton, 1, 1);
		add(itemButton, 2, 1);
		add(escapeButton, 3, 1);
		
		
		setAttack(attackButton);
		setSpell(spellButton);
		setItem(itemButton);
		setEscape(escapeButton);
		
	}
	
	private void setAttack(UiButton attackButton)
	{
		attackButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					playerTurn = false;
					int damage = monster.receiveDamage(Player.player.randPhyAtk(), StatType.PHYATK);
					report(Player.player.getName() + " deals " + damage + " damage!");
				}
			}
		);
	}
	private void setSpell(UiButton spellButton)
	{
		spellButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					playerTurn = false;
					spellList = new SpellList();
					stackPane.getChildren().add(overlayCanvas);
					stackPane.getChildren().add(spellList);
					spellList.setPrefSize(WIDTH * 3 / 5, HEIGHT * 3 / 5);
					StackPane.setAlignment(spellList, Pos.CENTER);
				}
			}
		);
	}
	private void setItem(UiButton itemButton)
	{
		itemButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					playerTurn = false;
					//TODO show item list
				}
			}
		);
	}
	private void setEscape(UiButton escapeButton)
	{
		escapeButton.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					playerTurn = false;
					if(Rand.rand(100) < 35)
					{
						backToVillage();		
					}
					else
					{
						report("Escape Failed!!");
					}
				}
			}
		);
	}
	private void backToVillage()
	{
		monster.fullHeal();
		Player.player.fullHeal();
		VillageEntityLogic.exitDungeon();
		Main.animation.start();
		Main.battleAnimation.stop();
		Main.changeScene(SceneManager.villageScene);
	}
	public void paintCanvas() 
	{
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		for (IRenderable entity : BattleRenderableHolder.getInstance().getEntities())
		{
			if (entity.isVisible())
			{
				entity.draw(gc);
//				System.out.println("draw entity" );
			}
		}
	}
	public void drawBackground()
	{
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("dungeon1.png")), 0, 0);
	}

	public static void endOverlay()
	{
		stackPane.getChildren().remove(overlayCanvas);
		stackPane.getChildren().remove(spellList);
		stackPane.getChildren().remove(itemList);
	}
	
	public static void report(Label label)
	{
		Battle.logDataList.add(label);
		listView.scrollTo(label);
		LogManager.getLogManager().reset();
	}
	public static void report(String report)
	{
		Label label = new Label(report);
		Battle.logDataList.add(label);
		listView.scrollTo(label);
		LogManager.getLogManager().reset();
	}
	public UiButton getAttackButton()
	{
		return attackButton;
	}
	public UiButton getSpellButton()
	{
		return spellButton;
	}
	public UiButton getItemButton()
	{
		return itemButton;
	}
	public UiButton getEscapeButton()
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
		Battle.monster = monster;
	}
	public static void setDungeon(Dungeon dungeon)
	{
		Battle.dungeon = dungeon;
	}
	
}

