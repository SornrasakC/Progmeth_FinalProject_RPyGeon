package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import logic.base.Potion;
import logic.logics.Player;
import main.Main;
import scene.shop.ShopButton;
import sharedObject.RenderableHolder;

public class ItemShop extends StackPane {
	
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	private static final int BUTTON_SIZE = 230;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private Label label = new Label("Item Shop");
	private Button backButton = new Button("back");
	private shops.ItemShop itemShop;
	private Label money;
	private GridPane itemGrid;
	private HBox topBar;
	private ImageView coin;
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
	
	public ItemShop() {
		contentBox = new VBox();
		itemGrid = new GridPane();
		topBar = new HBox();
		itemShop = new shops.ItemShop();
		label.setFont(new Font(40));
		label.setPadding(new Insets(15, 0, 0, 0));
		
		imageBG = new ImageView(RenderableHolder.itemShopBackground);
		imageBG.setFitHeight(HEIGHT);
		imageBG.setFitWidth(WIDTH);
		imageBG.setEffect(new GaussianBlur());
		
		coin = new ImageView(RenderableHolder.coin);
		money = new Label(Player.player.getMoney() + "");
		money.setFont(new Font(30));
		money.setPadding(new Insets(15, 0, 0, 0));
		
		topBar.setSpacing(20);
		topBar.setPadding(new Insets(20, 20, 0, 20));
		topBar.getChildren().addAll(backButton,label,coin,money);
		label.setPadding(new Insets(0, 300, 0, 300));
		
		//create back button
		SVGPath shape = new SVGPath();
		shape.setContent("M 0 40 L 40 0 L 400 0 L 400 80 L 40 80 Z ");
		backButton.setShape(shape);
		backButton.setPrefSize(BUTTON_SIZE, 70);
		
		backButton.setStyle(BACK_BUTTON_NORMAL);
		backButton.setOnMouseEntered(e -> {
			backButton.setStyle(BACK_BUTTON_HOVER);
		});
		backButton.setOnMouseExited(e -> {
			backButton.setStyle(BACK_BUTTON_NORMAL);
		});
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.changeScene(SceneManager.villageScene);
				VillageEntityLogic.exitShop();
			}
		});
		
		itemGrid.setHgap(HGAP);
		itemGrid.setVgap(VGAP);
		itemGrid.setPadding(new Insets(20));
		
		
		int i = 0;
		int j = 0;
		for(Potion potion : itemShop.getPotionList()) {
			System.out.println("Item shop load button @ "+i + "," + j);
			ShopButton itemButton = new ShopButton(potion);
			itemButton.setLogic(itemShop);
			itemButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			itemGrid.add(itemButton, i, j);
			if(i < 4) i++; else {i = 0; j++;}
		}
		
		
		contentBox.getChildren().addAll(topBar,itemGrid);
		this.getChildren().addAll(imageBG, contentBox);
	}
	
	public void updateMoney() {
		this.money.setText(Player.player.getMoney() + "");
	}

	
}
