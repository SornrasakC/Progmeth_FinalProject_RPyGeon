package scene;

import java.util.ArrayList;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.base.Monster;
import logic.base.StatType;
import logic.logics.Dungeon;
import logic.logics.Player;
import logic.logics.Rand;
import main.Main;

public class Battle extends GridPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static Monster monster;
	private static Dungeon dungeon;
	private boolean playerTurn = true;
	Canvas battleCanvas;

	public Battle()
	{
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		battleCanvas = new Canvas(WIDTH * 4 / 5,HEIGHT * 4 / 5);
		
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("dungeon1.png")), 0, 0);
		
		
		ListView<String> listView = new ListView<String>();
		listView.setPrefWidth(WIDTH / 5);
		
		UiButton attackButton = new UiButton("ATTACK");
		UiButton spellButton = new UiButton("SPELL");
		UiButton itemButton = new UiButton("ITEM");
		UiButton escapeButton = new UiButton("ESCAPE");
		
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
//		setHgap(10);
		add(battleCanvas, 0, 0, 4, 1);
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
	public static void setMonster(Monster monster)
	{
		Battle.monster = monster;
	}
	public static void setDungeon(Dungeon dungeon)
	{
		Battle.dungeon = dungeon;
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
					//TODO interfaces
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
					//TODO show magic list
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
					//TODO escape fail!
				}
			}
		);
	}
	private void backToVillage()
	{
		monster.fullHeal();
		Player.player.fullHeal();
		VillageEntityLogic.exitDungeon();
		Main.changeScene(SceneManager.villageScene);
	}
}

