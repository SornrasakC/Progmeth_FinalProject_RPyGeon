package entity;

import java.util.ArrayList;
import java.util.List;

import input.InputUtility;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import main.Main;
import scene.SceneManager;
import sharedObject.RenderableHolder;

public class VillageEntityLogic
{

	private List<Entity> gameObjectContainer;

	private static PlayerEntity player;
	private static ShopEntity itemShop;
	private static BlacksmithEntity blacksmith;
	private static DungeonEntity dungeon;
	private static BaseEntity base;

	private static boolean isInOtherEntity = false;

	public VillageEntityLogic()
	{
		this.gameObjectContainer = new ArrayList<Entity>();

		player = new PlayerEntity(940, 150, 2);
		itemShop = new ShopEntity(300, 50);
		blacksmith = new BlacksmithEntity(620, 50);
		dungeon = new DungeonEntity(940, 50);
		base = new BaseEntity(620, 500);
		addNewObject(player);
		addNewObject(itemShop);
		addNewObject(blacksmith);
		addNewObject(dungeon);
		addNewObject(base);
	}

	protected void addNewObject(Entity entity)
	{
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate()
	{
		player.update();
		if (!isInOtherEntity && player.collideWith(itemShop))
		{
			enterShop();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					SceneManager.getItemshopPane().updateMoney();
					Main.changeScene(SceneManager.itemshopScene);
				}
			});
		}
		else if (!isInOtherEntity && player.collideWith(dungeon))
		{
			enterDungeon();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					SceneManager.reDungeonChooseFloor();
					PauseTransition pt = new PauseTransition(Duration.millis(30));
					pt.setOnFinished(event -> Main.changeScene(SceneManager.dungeonChooseFloorScene));
					pt.play();
//					Main.changeScene(SceneManager.dungeonChooseFloorScene);
				}
			});
		}
		else if (!isInOtherEntity && player.collideWith(blacksmith))
		{
			enterShop();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					SceneManager.getBlacksmithShopPane().updateAvailableWeapon();
					SceneManager.getBlacksmithShopPane().updateMoney();
					// change to blacksmith scene
					Main.changeScene(SceneManager.blacksmithScene);
				}
			});
		}
		else if (!isInOtherEntity && player.collideWith(base))
		{
			enterShop();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					Main.changeScene(SceneManager.baseScene);
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
		player.teleportTo(itemShop.x + (itemShop.sprite.getWidth() / 2), itemShop.y + itemShop.sprite.getHeight() + 70);
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

	public static void exitBlacksmith()
	{
		isInOtherEntity = false;
		player.unFreeze();
		player.teleportTo(blacksmith.x + (blacksmith.sprite.getWidth() / 2),
				blacksmith.y + blacksmith.sprite.getHeight() + 70);
	}

	public static void exitBase()
	{
		isInOtherEntity = false;
		player.unFreeze();
		player.teleportTo(base.x + (base.sprite.getWidth() / 2), base.y + base.sprite.getHeight() - 200);
	}
}
