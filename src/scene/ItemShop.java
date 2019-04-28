package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import logic.base.Potion;
import main.Main;
import scene.shop.ItemShopButton;

public class ItemShop extends GridPane {
	
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	
	private Label label = new Label("Item Shop");
	private Button btn = new Button("back");
	private shops.ItemShop itemShop;
	
	public ItemShop() {
		itemShop = new shops.ItemShop();
		label.setFont(new Font(20));
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.getPrimaryStage().setScene(SceneManager.villageScene);
				VillageEntityLogic.exitShop();
			}
		});
		this.add(btn, 0, 0);
		this.setHgap(HGAP);
		this.setVgap(VGAP);
		
		
		int i = 0;
		int j = 1;
		for(Potion potion : itemShop.getPotionList()) {
			System.out.println(i + "," + j);
			ItemShopButton itemButton = new ItemShopButton(potion);
			itemButton.setLogic(itemShop);
			this.add(itemButton, i, j);
			if(i < 4) i++; else {i = 0; j++;}
		}

	}

}
