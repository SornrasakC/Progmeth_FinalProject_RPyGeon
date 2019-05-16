package scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Base extends StackPane
{
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	private static final int BUTTON_SIZE = 230;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private Label label = new Label("Base");
	private Button backButton = new Button("back");
	private GridPane inventoryGrid;
	private GridPane equipmentGrid;
	private HBox topBar;
	private VBox contentBox;
	private ImageView imageBG;
	private static final String BACK_BUTTON_NORMAL ="-fx-background-color: \r\n" + 
			"    linear-gradient(#ffd65b, #e68400),\r\n" + 
			"    linear-gradient(#ffef84, #f2ba44),\r\n" + 
			"    linear-gradient(#ffea6a, #efaa22),\r\n" + 
			"    linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n" + 
			"    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
			"-fx-background-radius: 30;\r\n" + 
			"-fx-background-insets: 0,1,2,3,0;\r\n" + 
			"-fx-text-fill: #654b00;\r\n" + 
			"-fx-font-weight: bold;\r\n" + 
			"-fx-font-size: 14px;\r\n" + 
			"-fx-padding: 10 20 10 20;";
			
	private static final String BACK_BUTTON_HOVER = "-fx-background-color: \r\n" + 
			"     linear-gradient(#ffd65b, #e68400),\r\n" + 
			"     linear-gradient(#ffef84, #f2ba44),\r\n" + 
			"     linear-gradient(#ffea6a, #efaa22),\r\n" + 
			"     linear-gradient(#eea10b 0%, #f8c202 50%, #ffe657 100%),\r\n" + 
			"     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
			" -fx-background-radius: 30;\r\n" + 
			" -fx-background-insets: 0,1,2,3,0;\r\n" + 
			" -fx-text-fill: #654b00;\r\n" + 
			" -fx-font-weight: bold;\r\n" + 
			" -fx-font-size: 14px;\r\n" +
			" -fx-padding: 10 20 10 20;";
	
	public Base()
	{
		inventoryGrid = new GridPane();
		equipmentGrid = new GridPane();
	}

}
