package scene;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DungeonBattle extends GridPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	Canvas battleCanvas;

	public DungeonBattle()
	{
		battleCanvas = new Canvas(WIDTH * 4 / 5,HEIGHT * 4 / 5);
		
		GraphicsContext gc = battleCanvas.getGraphicsContext2D();
		gc.setFill(Color.ALICEBLUE);
		gc.fillRect(0, 0, WIDTH * 4 / 5,HEIGHT * 4 / 5);

		
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
				GridPane.setMargin(button, new Insets(0, 0, 0, 5));
			}
		);
		GridPane.setMargin(escapeButton, new Insets(0, 5, 0, 5));
//		setHgap(10);
		add(battleCanvas, 0, 0, 4, 1);
		add(listView, 4, 0, 1, 2);
		add(attackButton, 0, 1);
		add(spellButton, 1, 1);
		add(itemButton, 2, 1);
		add(escapeButton, 3, 1);
		
	}

}
