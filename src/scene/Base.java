package scene;

import item.Weapon;
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
import logic.base.Potion;
import logic.logics.Player;
import scene.component.BaseButton;
import scene.component.InventoryItemButton;
import scene.component.ShopButton;
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
	private Label inventoryLabel = new Label("Inventory");
	private VBox inventoryBox;
	private GridPane inventoryGrid;
	private HBox equipmentBox;
	private HBox wholePane;
	private ImageView imageBG;
	private ImageView playerModel;
	
	private BaseButton weaponButton;
	private BaseButton shirtButton;
	private BaseButton pantsButton;
	private BaseButton bootsButton;
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
		inventoryBox = new VBox();
		wholePane.setPadding(new Insets(INSETS));
		inventoryGrid.setHgap(HGAP);
		inventoryGrid.setVgap(VGAP);
		inventoryGrid.setPrefSize(WIDTH/2, HEIGHT - 200);;
		equipmentBox.setSpacing(HGAP);
		
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
		leftSideEquipmentsBox.setPadding(new Insets(HEIGHT / 2 - 200 , HGAP, HEIGHT / 2 - 100, HGAP));
		leftSideEquipmentsBox.getChildren().addAll(weaponButton);
		
		playerModel = new ImageView(RenderableHolder.emiliaFullBody);
		playerModel.setFitHeight(HEIGHT - 100);
		
		
		inventoryBox.getChildren().addAll(inventoryLabel, inventoryGrid);
		
		equipmentBox.getChildren().addAll(leftSideEquipmentsBox, playerModel, rightSideEquipmentsBox);
		wholePane.getChildren().addAll(equipmentBox, inventoryGrid);
		setEquippedButtonLogic();
		this.getChildren().add(wholePane);
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
				// TODO Auto-generated method stub
				
			}
		});
		
		pantsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bootsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void inventoryShowWeapons() {
		inventoryGrid.getChildren().clear();

		int i = 0;
		int j = 0;
		for(Weapon weapon : Player.player.getWeaponInventory()) {
			System.out.println("Base load weapon button @ "+i + "," + j);
			InventoryItemButton inventoryButton = new InventoryItemButton(weapon);
			inventoryButton.setWeaponButtonLogic(weaponButton);
			inventoryButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			inventoryGrid.add(inventoryButton, i, j);
			if(i < 4) i++; else {i = 0; j++;}
		}
	}
	

}
