package entity;

import java.util.ArrayList;
import java.util.List;

import input.InputUtility;
import javafx.application.Platform;
import main.Main;
import scene.SceneManager;
import sharedObject.RenderableHolder;

public class VillageEntityLogic
{

	private List<Entity> gameObjectContainer;

	private static PlayerEntity player;
	private static ShopEntity shop1;
	private static ShopEntity shop2;
	private static ShopEntity dungeon;

	private static boolean isInOtherEntity = false;

	public VillageEntityLogic()
	{
		this.gameObjectContainer = new ArrayList<Entity>();

		player = new PlayerEntity(320, 240, 2);
		shop1 = new ShopEntity(300, 50);
		shop2 = new ShopEntity(620, 50);
		dungeon = new ShopEntity(940, 50);
		addNewObject(player);
		addNewObject(shop1);
		addNewObject(shop2);
		addNewObject(dungeon);
	}

	protected void addNewObject(Entity entity)
	{
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate()
	{
		player.update();
		if (!isInOtherEntity && player.collideWith(shop1))
		{
			enterShop();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					Main.changeScene(SceneManager.itemshopScene);
				}
			});
		}
		else if(!isInOtherEntity && player.collideWith(dungeon))
		{
			enterDungeon();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					Main.changeScene(SceneManager.dungeonChooseFloorScene);
				}
			});
		}

	}

	public static void enterShop()
	{
		isInOtherEntity = true;
		player.freeze();
		System.out.println("Collision detected");
		InputUtility.clearInput();
	}

	public static void exitShop()
	{
		isInOtherEntity = false;
		player.unFreeze();
		player.teleportTo(shop1.x + (shop1.sprite.getWidth() / 2), shop1.y + shop1.sprite.getHeight() + 70);
	}
	
	public static void enterDungeon()
	{
		isInOtherEntity = true;
		player.freeze();
		System.out.println("Collision detected");
		InputUtility.clearInput();
	}
	
	public static void exitDungeon()
	{
		isInOtherEntity = false;
		player.unFreeze();
		player.teleportTo(dungeon.x + (dungeon.sprite.getWidth() / 2), dungeon.y + dungeon.sprite.getHeight() + 70);
	}

}
