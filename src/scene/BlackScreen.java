package scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BlackScreen extends StackPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private Canvas canvas;
	public BlackScreen()
	{
		setPrefSize(WIDTH, HEIGHT);
		canvas = new Canvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
	}


}
