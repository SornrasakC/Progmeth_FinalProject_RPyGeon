package scene;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class DungeonBattle extends GridPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	Canvas battleCanvas;

	public DungeonBattle()
	{
		battleCanvas = new Canvas(WIDTH,HEIGHT);
		
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.setFill(Color.ALICEBLUE);
		gc.fill();

		
		ListView<String> listView = new ListView<String>();
		listView.setPrefWidth(WIDTH/5);
		
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
			button.setPrefWidth(WIDTH/5)
		);
		
		add(battleCanvas, 0, 0, 4, 0);
		add(listView, 1, 0, 0, 2);
		add(attackButton, 0, 1);
		add(spellButton, 1, 1);
		add(itemButton, 2, 1);
		add(escapeButton, 3, 1);
		
	}

}
