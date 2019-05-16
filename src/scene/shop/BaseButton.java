package scene.shop;

import item.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.base.Armour;
import logic.logics.Player;
import scene.SceneManager;
import sharedObject.RenderableHolder;
import shops.ItemShop;

public class BaseButton extends Button
{
	private String path;
	private Image image;
	private Armour thisArmour;
	private Weapon thisWeapon;

	private static final int HOVER_TO_APPEAR_DURATION = 250;
	private static final int APPEAR_DURATION = 50000;

	private static final String NORMAL_STYLE = "-fx-background-color:" + "#3c7fb1,"
			+ "linear-gradient(#fafdfe, #e8f5fc),"
			+ "linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);" + "-fx-background-insets: 0,1,2;"
			+ "-fx-background-radius: 3,2,1;" + "-fx-padding: 3 30 3 30;" + "-fx-text-fill: black;"
			+ "-fx-font-size: 14px;";
	private static final String HOVERED_STYLE = "-fx-background-color:" + "#3c7fb1,"
			+ "linear-gradient(#fafdfe, #fcf2e8),"
			+ "linear-gradient(#fdf2e8 0%, #fcebd9 49%, #fddebf 50%, #f5cfa8 100%);" + "-fx-background-insets: 0,1,2;"
			+ "-fx-background-radius: 3,2,1;" + "-fx-padding: 3 30 3 30;" + "-fx-text-fill: black;"
			+ "-fx-font-size: 14px;";
	private static final String HOVERED_STYLE_RED = "-fx-background-color:" + "#af3c3c,"
			+ "linear-gradient(#fafdfe, #fce8e8),"
			+ "linear-gradient(#fde8e8 0%, #fcd9d9 49%, #fdbfbf 50%, #f5a8a8 100%);" + "-fx-background-insets: 0,1,2;"
			+ "-fx-background-radius: 3,2,1;" + "-fx-padding: 3 30 3 30;" + "-fx-text-fill: black;"
			+ "-fx-font-size: 14px;";

	public BaseButton(Armour armour)
	{
		thisArmour = armour;
		switch (armour.getName())
		{
			case ("Red Cookies"):
				image = RenderableHolder.redCookie;
				break;
			case ("Blue Cookies"):
				image = RenderableHolder.blueCookie;
				break;
			case ("Fairly Normal HP Potion"):
				image = RenderableHolder.hpPotion;
				break;
			case ("Fairly Normal MP Potion"):
				image = RenderableHolder.mpPotion;
				break;
			case ("Chicken Dinner"):
				image = RenderableHolder.chickenDinner;
				break;
			case ("Cocaine"):
				image = RenderableHolder.cocain;
				break;
			case ("Phoenix Kit"):
				image = RenderableHolder.phoenixKit;
				break;
			case ("Low Quality Super Duper Lucky Randomly Recovering Potion"):
				image = RenderableHolder.lowRecPotion;
				break;
			case ("High Quality Super Duper Lucky Randomly Recovering Potion"):
				image = RenderableHolder.highRecPotion;
				break;
			case ("M44"):
				image = RenderableHolder.m44;
				break;
			case ("Shroud's right arm"):
				image = RenderableHolder.rightArm;
				break;
			case ("Oten's tear"):
				image = RenderableHolder.otenTear;
				break;
			case ("Trap Card: Mirror Force"):
				image = RenderableHolder.mirrorForce;
				break;
			default:
				path = "WIP.png";
				image = new Image(ClassLoader.getSystemResourceAsStream(path));
				break;
		}

		setGraphic(new ImageView(image));
		setPadding(new Insets(4));

//		setItemCustomTooltip();
		setStyle(NORMAL_STYLE);
//		changeBackgroundOnHover(this);
	}

	public BaseButton(Weapon weapon)
	{
		thisWeapon = weapon;
		switch (weapon.getName())
		{
			case ("Red Cookies"):
				image = RenderableHolder.redCookie;
				break;
			case ("Blue Cookies"):
				image = RenderableHolder.blueCookie;
				break;
			case ("Fairly Normal HP Potion"):
				image = RenderableHolder.hpPotion;
				break;
			case ("Fairly Normal MP Potion"):
				image = RenderableHolder.mpPotion;
				break;
			case ("Chicken Dinner"):
				image = RenderableHolder.chickenDinner;
				break;
			case ("Cocaine"):
				image = RenderableHolder.cocain;
				break;
			case ("Phoenix Kit"):
				image = RenderableHolder.phoenixKit;
				break;
			case ("Low Quality Super Duper Lucky Randomly Recovering Potion"):
				image = RenderableHolder.lowRecPotion;
				break;
			case ("High Quality Super Duper Lucky Randomly Recovering Potion"):
				image = RenderableHolder.highRecPotion;
				break;
			case ("M44"):
				image = RenderableHolder.m44;
				break;
			case ("Shroud's right arm"):
				image = RenderableHolder.rightArm;
				break;
			case ("Oten's tear"):
				image = RenderableHolder.otenTear;
				break;
			case ("Trap Card: Mirror Force"):
				image = RenderableHolder.mirrorForce;
				break;
			default:
				path = "WIP.png";
				image = new Image(ClassLoader.getSystemResourceAsStream(path));
				break;
		}

		setGraphic(new ImageView(image));
		setPadding(new Insets(4));

//		setItemCustomTooltip();
		setStyle(NORMAL_STYLE);
//		changeBackgroundOnHover(this);
	}

	public void setLogic(Weapon weapon)
	{
		setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				
//				SceneManager.getItemshopPane().updateMoney();
//				if (Player.player.getMoney() < price)
//				{
//					setStyle(HOVERED_STYLE_RED);
//				}
//				else
//				{
//					setStyle(HOVERED_STYLE);
//				}
			}
		});
	}

}
