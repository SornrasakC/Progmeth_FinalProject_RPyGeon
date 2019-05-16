package scene;

import entity.VillageEntityLogic;
import item.ChestArmour;
import item.PantsArmour;
import item.ShoesArmour;
import item.Weapon;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import logic.logics.Player;
import main.Main;
import scene.component.BaseButton;
import scene.component.InventoryItemButton;
import sharedObject.RenderableHolder;

public class Base extends StackPane
{
	private static final double INSETS = 20;
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	private static final int BUTTON_SIZE = 130;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
//	private Label label = new Label("Base");
	private Button backButton = new Button("back");
	private Label inventoryText = new Label("Inventory");
	private VBox inventoryBox;
	private GridPane inventoryGrid;
	private HBox equipmentBox;
	private HBox wholePane;
	private ImageView imageBG;
	private ImageView playerModel;
	private StackPane inventoryStack;
	
	private BaseButton weaponButton;
	private BaseButton shirtButton;
	private BaseButton pantsButton;
	private BaseButton bootsButton;
	
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
		inventoryBox = new VBox();
		inventoryStack = new StackPane();
		wholePane.setPadding(new Insets(INSETS));
		inventoryGrid.setHgap(HGAP);
		inventoryGrid.setVgap(VGAP);
		inventoryGrid.setPrefSize(WIDTH/2, HEIGHT - 200);;
		inventoryGrid.setPadding(new Insets(INSETS));
		equipmentBox.setSpacing(HGAP);
		
		SVGPath shape = new SVGPath();
		shape.setContent("M 0 40 L 40 0 L 400 0 L 400 80 L 40 80 Z ");
		backButton.setShape(shape);
		backButton.setPrefSize(BUTTON_SIZE, 70);

		backButton.setStyle(BACK_BUTTON_NORMAL);
		backButton.setOnMouseEntered(e ->
			{
				backButton.setStyle(BACK_BUTTON_HOVER);
			});
		backButton.setOnMouseExited(e ->
			{
				backButton.setStyle(BACK_BUTTON_NORMAL);
			});
		backButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Main.changeScene(SceneManager.villageScene);
				VillageEntityLogic.exitBase();
				backButton.setDisable(true);
				PauseTransition pt = new PauseTransition(Duration.millis(1000));
				pt.setOnFinished(event2 -> backButton.setDisable(false));
				pt.play();
			}
		});
		
		weaponButton = new BaseButton(0);
		shirtButton = new BaseButton(1);
		pantsButton = new BaseButton(2);
		bootsButton = new BaseButton(3);
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
		leftSideEquipmentsBox.setPadding(new Insets(HEIGHT / 3 - 50 , HGAP, VGAP, HGAP));
		leftSideEquipmentsBox.getChildren().addAll(backButton,weaponButton);
		
		playerModel = new ImageView(RenderableHolder.emiliaFullBody);
		playerModel.setFitHeight(HEIGHT - 100);
		playerModel.setPreserveRatio(true);
		
		
		inventoryBox.getChildren().addAll(inventoryText, inventoryGrid);
		inventoryText.setTextAlignment(TextAlignment.CENTER);
		inventoryText.setPrefWidth(inventoryBox.getWidth());
		inventoryText.setFont(new Font(28));
		inventoryText.setPadding(new Insets(HGAP,inventoryBox.getWidth()/ 2,HGAP,100));
		inventoryText.setStyle("-fx-font-weight: bold");
		
		ImageView inventoryBG =  new ImageView(RenderableHolder.inventoryBackground);
		inventoryBG.setFitWidth((WIDTH/2) - 50);
		inventoryStack.getChildren().addAll(inventoryBG, inventoryBox);
		
		equipmentBox.getChildren().addAll(leftSideEquipmentsBox, playerModel, rightSideEquipmentsBox);
		wholePane.getChildren().addAll(equipmentBox, inventoryStack);
//		wholePane.setPadding(new Insets(20));
		imageBG = new ImageView(RenderableHolder.leafyBackground);
		imageBG.prefHeight(HEIGHT);
		imageBG.prefWidth(WIDTH);
		setEquippedButtonLogic();
		this.getChildren().addAll(imageBG, wholePane);
		this.setPadding(new Insets(INSETS));
	}
	
	private void setEquippedButtonLogic() {
		weaponButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inventoryShowWeapons();
			}
		});
		
		shirtButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inventoryShowShirtArmour();
				
			}
		});
		
		pantsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inventoryShowPantsArmour();
				
			}
		});
		
		bootsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inventoryShowBootsArmour();
			}
		});
	}
	
	public void inventoryShowWeapons() {
		inventoryGrid.getChildren().clear();
		System.out.println("Loading weapon inventory");
		int i = 0;
		int j = 0;
		for(Weapon weapon : Player.player.getWeaponInventory()) {
			System.out.println("Base load weapon button @ "+i + "," + j);
			InventoryItemButton inventoryButton = new InventoryItemButton(weapon);
			inventoryButton.setWeaponButtonLogic(weaponButton);
			inventoryButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			inventoryGrid.add(inventoryButton, i, j);
			if(i < 3) i++; else {i = 0; j++;}
		}
		if(Player.player.getWeaponInventory().size() == 0) {
		}
		System.out.println("Load complete");
	}
	
	public void inventoryShowShirtArmour() {
		inventoryGrid.getChildren().clear();
		System.out.println("Loading shirt armour inventory");
		int i = 0;
		int j = 0;
		for(ChestArmour armour : Player.player.getChestArmourInventory()) {
			System.out.println("Base load shirt armour button @ "+i + "," + j);
			InventoryItemButton inventoryButton = new InventoryItemButton(armour);
			inventoryButton.setShirtButtonLogic(shirtButton);
			inventoryButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			inventoryGrid.add(inventoryButton, i, j);
			if(i < 3) i++; else {i = 0; j++;}
		}
		System.out.println("Load complete");
	}
	
	public void inventoryShowPantsArmour() {
		inventoryGrid.getChildren().clear();
		System.out.println("Loading pants armour inventory");
		int i = 0;
		int j = 0;
		for(PantsArmour armour : Player.player.getPantsArmourInventory()) {
			System.out.println("Base load shirt armour button @ "+i + "," + j);
			InventoryItemButton inventoryButton = new InventoryItemButton(armour);
			inventoryButton.setPantsButtonLogic(pantsButton);
			inventoryButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			inventoryGrid.add(inventoryButton, i, j);
			if(i < 3) i++; else {i = 0; j++;}
		}
		System.out.println("Load complete");
	}
	
	public void inventoryShowBootsArmour() {
		inventoryGrid.getChildren().clear();
		System.out.println("Loading shirt armour inventory");
		int i = 0;
		int j = 0;
		for(ShoesArmour armour : Player.player.getShoesArmourInventory()) {
			System.out.println("Base load shirt armour button @ "+i + "," + j);
			InventoryItemButton inventoryButton = new InventoryItemButton(armour);
			inventoryButton.setBootsButtonLogic(bootsButton);
			inventoryButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			inventoryGrid.add(inventoryButton, i, j);
			if(i < 3) i++; else {i = 0; j++;}
		}
		System.out.println("Load complete");
	}
	

}