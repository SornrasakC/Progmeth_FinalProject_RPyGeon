package scene.battleOverlay;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BattleEnd extends StackPane
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	public BattleEnd()
	{
		setBorder(new Border(new BorderStroke(Color.WHITESMOKE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(10))));
		
//		setStyle("-fx-background-radius: 20;");
		setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResourceAsStream("BattleEnd.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT)));
	}
	

}
