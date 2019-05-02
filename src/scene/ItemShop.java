package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import logic.base.Potion;
import main.Main;
import scene.shop.ItemShopButton;

public class ItemShop extends GridPane {
	
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	
	private static final int BUTTON_SIZE = 230;
	
	private Label label = new Label("Item Shop");
	private Button btn = new Button("back");
	private shops.ItemShop itemShop;
	
	public ItemShop() {
		itemShop = new shops.ItemShop();
		label.setFont(new Font(40));
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.changeScene(SceneManager.villageScene);
				VillageEntityLogic.exitShop();
			}
		});
		btn.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		this.add(btn, 0, 0);
		this.setHgap(HGAP);
		this.setVgap(VGAP);
		this.setPadding(new Insets(20));
		
		
		int i = 1;
		int j = 0;
		for(Potion potion : itemShop.getPotionList()) {
			System.out.println(i + "," + j);
			ItemShopButton itemButton = new ItemShopButton(potion);
			itemButton.setLogic(itemShop);
			itemButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
			this.add(itemButton, i, j);
			if(i < 4) i++; else {i = 0; j++;}
		}

	}

	
}
