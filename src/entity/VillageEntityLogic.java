package entity;

import java.util.ArrayList;
import java.util.List;

import scene.SceneManager;
import sharedObject.RenderableHolder;

public class VillageEntityLogic {
	
	private List<Entity> gameObjectContainer;
	
	private PlayerEntity player;
	private ShopEntity shop1;
	
	public VillageEntityLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
		player = new PlayerEntity(320,240,2);
		shop1 = new ShopEntity(100,100);
		addNewObject(player);
		addNewObject(shop1);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		player.update();
	}

}
