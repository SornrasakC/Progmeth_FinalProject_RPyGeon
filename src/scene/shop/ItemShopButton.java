package scene.shop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.base.Potion;
import logic.logics.Player;
import shops.ItemShop;

public class ItemShopButton extends Button {
	String path;
	Image image;
	Potion thisPotion;
	
	public ItemShopButton(Potion potion) {
		thisPotion = potion;
		//TODO add sprites
//		path = potion.getName() + ".png";
		path =  "WIP.png";
		image = new Image(ClassLoader.getSystemResourceAsStream(path));
		
		this.setGraphic(new ImageView(image));
		this.setPadding(new Insets(4));
	}
	
	public void setLogic(ItemShop itemShop){
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Bought" + thisPotion.getName());
				Player.player.buyPotion(itemShop, thisPotion);
			}
		});
	}

}
