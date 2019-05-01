package scene;



import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.logics.Player;
import main.Main;

public class StartNaming extends StackPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	public StartNaming()
	{

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getChildren().add(canvas);
		setCanvas(gc);
//		MainMenuItem item = new MainMenuItem(data.getKey());
//		item.setOnAction(data.getValue());
//		Rectangle clip = new Rectangle(300, 30);
//		item.setClip(clip);
//
//		menuBox.getChildren().addAll(item);
	}
	private void setCanvas(GraphicsContext gc)
	{

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		
		Label label = new Label("How Should We Call You?");
		label.setTextFill(Color.WHITE);
		label.setFont(new Font(30));
		label.setTranslateY(-HEIGHT/5);
		
		TextField textField = new TextField();
		textField.setFont(new Font(30));
		textField.setPromptText("Your Name"); // ???
		textField.setAlignment(Pos.CENTER);
		textField.setMaxWidth(WIDTH / 3);
		textField.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, null)));
		textField.setBorder(new Border(new BorderStroke(Color.GREEN,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		textField.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	String name = textField.getText();
	            	if(name.length() > 0)
	            	{
	            		Player.player = new Player(name);
	            		Main.changeScene(SceneManager.confirmPrologueScene);
	            	}
	            }
	        }
	    });

		
		
		getChildren().addAll(label, textField);
		StackPane.setAlignment(label, Pos.CENTER);
		StackPane.setAlignment(textField, Pos.CENTER);
	}

	
}
