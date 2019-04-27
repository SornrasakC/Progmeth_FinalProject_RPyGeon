package scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Village extends Pane
{

	public Village()
	{
		Canvas canvas = new Canvas(1280, 720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		drawBackground(gc);
		drawDungeon(gc);
//		drawSomething(gc);
	}

	public void drawBackground(GraphicsContext gc)
	{
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	public void drawDungeon(GraphicsContext gc)
	{
		
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png")), 300, 50);
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png")), 620, 50);
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png")), 940, 50);
		
	}

//	public void drawSomething(GraphicsContext gc) {
//		gc.setFill(Color.AZURE);
//		gc.fillText("Village", 680, 360);
//	}
}
