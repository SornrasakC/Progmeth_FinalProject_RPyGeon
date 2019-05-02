package scene;



import java.io.File;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Village extends Pane
{
	private Canvas canvas;
	private MediaPlayer mediaPlayer;
	public Village()
	{
		canvas = new Canvas(1280, 720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		new File(ClassLoader.getSystemResource("FindTheWay.mp3").toString());

		Media backgroundMusic = new Media(getClass().getClassLoader().getResource("FindTheWay.mp3").toExternalForm());
		mediaPlayer = new MediaPlayer(backgroundMusic);
		mediaPlayer.play();
		
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

	public void paintCanvas() 
	{
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		for (IRenderable entity : RenderableHolder.getInstance().getEntities())
		{
			if (entity.isVisible())
			{
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
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();	
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
