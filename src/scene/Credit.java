package scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Credit extends Pane {
	
	public Credit() {
		Canvas canvas = new Canvas(1280,720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		drawBackground(gc);
		drawSomething(gc);
	}
	
	public void drawBackground(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}
	
	public void drawSomething(GraphicsContext gc) {
		gc.setFill(Color.AZURE);
		gc.fillText("Credit", 680, 360);
	}

}
