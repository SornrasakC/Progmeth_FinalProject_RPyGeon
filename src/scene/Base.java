package scene;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sharedObject.RenderableHolder;

public class Base extends StackPane
{
	private static final double INSETS = 20;
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	private static final int BUTTON_SIZE = 150;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private Label label = new Label("Base");
	private Button backButton = new Button("back");
	private GridPane inventoryGrid;
	private HBox equipmentBox;
	private HBox wholePane;
	private ImageView imageBG;
	private ImageView playerModel;
	
	private Button weaponButton;
	private Button shirtButton;
	private Button pantsButton;
	private Button bootsButton;
	private Button invisButton;
	
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
		wholePane = new HBox();
		inventoryGrid = new GridPane();
		equipmentBox = new HBox();
		wholePane.setPadding(new Insets(INSETS));
		inventoryGrid.setHgap(HGAP);
		inventoryGrid.setVgap(VGAP);
		equipmentBox.setSpacing(HGAP);
		
		weaponButton = new Button();
		shirtButton = new Button();
		pantsButton = new Button();
		bootsButton = new Button();
		invisButton = new Button();
		invisButton.setVisible(false);
		weaponButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		shirtButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		pantsButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		bootsButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		
		VBox rightSideEquipmentsBox = new VBox();
		rightSideEquipmentsBox.setSpacing(VGAP);
		rightSideEquipmentsBox.setPadding(new Insets(150 , HGAP, VGAP, HGAP));
		rightSideEquipmentsBox.getChildren().addAll(shirtButton, pantsButton, bootsButton);
		
		VBox leftSideEquipmentsBox = new VBox();
		leftSideEquipmentsBox.setSpacing(VGAP);
		leftSideEquipmentsBox.setPadding(new Insets(HEIGHT / 2 - 200 , HGAP, HEIGHT / 2 - 100, HGAP));
		leftSideEquipmentsBox.getChildren().addAll(weaponButton);
		
		playerModel = new ImageView(RenderableHolder.emiliaFullBody);
		playerModel.setFitHeight(HEIGHT - 100);
		
		equipmentBox.getChildren().addAll(leftSideEquipmentsBox, playerModel, rightSideEquipmentsBox);
		wholePane.getChildren().addAll(equipmentBox, inventoryGrid);
		this.getChildren().add(wholePane);
	}

}
