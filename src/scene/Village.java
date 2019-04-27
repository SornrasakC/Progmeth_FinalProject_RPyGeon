package scene;

import input.InputUtility;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Village extends Pane
{
	private Canvas canvas;
	
	public Village()
	{
		canvas = new Canvas(1280, 720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		addListerner();
		drawBackground();
		drawDungeon(gc);
		paintCanvas();
	}

	public void drawBackground()
	{
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	public void drawDungeon(GraphicsContext gc)
	{
		
//		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png")), 300, 50);
//		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png")), 620, 50);
//		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png")), 940, 50);
		
	}

	public void paintCanvas() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
//		gc.setFill(Color.AZURE);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			// System.out.println(entity.getZ());
			if (entity.isVisible()) {
				entity.draw(gc);
			}
		}
	}
	
	public void addListerner() {
		
		
		this.canvas.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.canvas.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

		this.canvas.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftDown();
		});

		this.canvas.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftRelease();
		});

		this.canvas.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
		});

		this.canvas.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
		});

		this.canvas.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});

		this.canvas.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
}
