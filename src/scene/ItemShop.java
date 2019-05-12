package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.base.Potion;
import logic.logics.Player;
import main.Main;
import scene.shop.ItemShopButton;
import sharedObject.RenderableHolder;

public class ItemShop extends VBox {
	
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	
	private static final int BUTTON_SIZE = 230;
	
	private Label label = new Label("Item Shop");
	private Button btn = new Button("back");
	private shops.ItemShop itemShop;
	private Label money;
	private GridPane itemGrid;
	private HBox topBar;
	private ImageView coin;
	
	public ItemShop() {
		itemGrid = new GridPane();
		topBar = new HBox();
		itemShop = new shops.ItemShop();
		label.setFont(new Font(40));
		label.setPadding(new Insets(15, 0, 0, 0));
		
		coin = new ImageView(RenderableHolder.coin);
		money = new Label(Player.player.getMoney() + "");
		money.setFont(new Font(30));
		money.setPadding(new Insets(15, 0, 0, 0));
		
		topBar.setSpacing(20);
		topBar.setPadding(new Insets(20, 20, 0, 20));
		topBar.getChildren().addAll(btn,label,coin,money);
		label.setPadding(new Insets(0, 300, 0, 300));
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.changeScene(SceneManager.villageScene);
				VillageEntityLogic.exitShop();
			}
		});
		btn.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
//		itemGrid.add(btn, 0, 0);
		itemGrid.setHgap(HGAP);
		itemGrid.setVgap(VGAP);
		itemGrid.setPadding(new Insets(20));
		
		
		int i = 0;
		int j = 0;
		for(Potion potion : itemShop.getPotionList()) {
			System.out.println(i + "," + j);
			ItemShopButton itemButton = new ItemShopButton(potion);
			itemButton.setLogic(itemShop);
			itemButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			itemGrid.add(itemButton, i, j);
			if(i < 4) i++; else {i = 0; j++;}
		}
		
		this.getChildren().addAll(topBar,itemGrid);
	}
	
	public void updateMoney() {
		this.money.setText(Player.player.getMoney() + "");
	}

	
}
