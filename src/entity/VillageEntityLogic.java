package entity;

import java.util.ArrayList;
import java.util.List;

import input.InputUtility;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import scene.SceneManager;
import sharedObject.RenderableHolder;

public class VillageEntityLogic {
	
	private List<Entity> gameObjectContainer;
	
	private PlayerEntity player;
	private ShopEntity shop1;
	private ShopEntity shop2;
	private ShopEntity shop3;
	
	private boolean isInShop = false;
	
	public VillageEntityLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
		player = new PlayerEntity(320,240,2);
		shop1 = new ShopEntity(300, 50);
		shop2 = new ShopEntity(620, 50);
		shop3 = new ShopEntity(940, 50);
		addNewObject(player);
		addNewObject(shop1);
		addNewObject(shop2);
		addNewObject(shop3);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		player.update();
		if(!isInShop && player.collideWith(shop1)) {
			isInShop = true;
			player.freeze();
			System.out.println("Collision detected");
			InputUtility.clearInput();
			Stage itemShopStage = new Stage();
			itemShopStage.setScene(SceneManager.itemshopScene);
			itemShopStage.setOnCloseRequest((WindowEvent) -> {
				isInShop = false;
				player.unFreeze();
				player.teleportTo(shop1.x + (shop1.sprite.getWidth() / 2), shop1.y + shop1.sprite.getHeight() + 70);
			});
			itemShopStage.show();
		}
	}

}
