package scene.component;

import java.lang.reflect.Field;

import item.Weapon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
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
	private int position;

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

	public BaseButton(int position)
	{
		//Type 0:weapon, 1:shirt, 2:pants, 3:boots
		this.position = position;
		switch(position) {
			case 0: image = Player.player.getEquippedWeapon().getSprite(); this.thisWeapon =  Player.player.getEquippedWeapon(); break;
			case 1: image = Player.player.getEquippedChestArmour().getSprite(); this.thisArmour = Player.player.getEquippedChestArmour(); break;
			case 2: image = Player.player.getEquippedPantsArmour().getSprite(); this.thisArmour = Player.player.getEquippedPantsArmour(); break;
			case 3: image = Player.player.getEquippedShoesArmour().getSprite(); this.thisArmour = Player.player.getEquippedShoesArmour(); break;
			default: image = RenderableHolder.placeHolder;
		}
		setGraphic(new ImageView(image));
		setPadding(new Insets(4));

		if(this.position == 0) {
			setWeaponCustomTooltip();
		}else {
			setArmourCustomTooltip();
		}
		setStyle(NORMAL_STYLE);
		changeBackgroundOnHover(this);
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
	
	public void updateButton() {
		switch(position) {
			case 0: image = Player.player.getEquippedWeapon().getSprite(); this.thisWeapon =  Player.player.getEquippedWeapon(); break;
			case 1: image = Player.player.getEquippedChestArmour().getSprite(); this.thisArmour = Player.player.getEquippedChestArmour(); break;
			case 2: image = Player.player.getEquippedPantsArmour().getSprite(); this.thisArmour = Player.player.getEquippedPantsArmour(); break;
			case 3: image = Player.player.getEquippedShoesArmour().getSprite(); this.thisArmour = Player.player.getEquippedShoesArmour(); break;
			default: image = RenderableHolder.placeHolder;
		}
		setGraphic(new ImageView(image));
		if(this.position == 0) {
			setWeaponCustomTooltip();
		}else {
			setArmourCustomTooltip();
		}
	}
	
	 private void setWeaponCustomTooltip() {
		 WebView  webView = new WebView();
	        WebEngine webEngine = webView.getEngine();
	        webEngine.loadContent
	        (
	    		"<h3>" + thisWeapon.getName() + "</h3>" + thisWeapon.getDescription() +
	    		"<ul = \"stats\">" +
	    		"<li>Physical Attack : " + thisWeapon.getBaseMinPhyAtk() + " - " + thisWeapon.getBaseMaxPhyAtk() + "</li>" + 
	    		"<li>Magical Attack : " + thisWeapon.getBaseMinMagAtk() + " - " + thisWeapon.getBaseMaxMagAtk() + "</li>" + 
	    		"</ul>"
			);
	        
			Tooltip tooltip = new Tooltip();
			tooltip.setPrefSize(700, 160);
			tooltip.setStyle("-fx-background-color:white;");
			tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			tooltip.setGraphic(webView);
			
			setTooltipTiming(tooltip);
			this.setTooltip(tooltip);
	 }
	 
	 private void setArmourCustomTooltip() {
		 WebView  webView = new WebView();
	        WebEngine webEngine = webView.getEngine();
	        webEngine.loadContent
	        (
	    		"<h3>" + thisArmour.getName() + "</h3>" + thisArmour.getDescription() +
	    		"<ul = \"stats\">" +
	    		"<li>Physical Defense : " + thisArmour.getBasePhyDef() + "</li>" + 
	    		"<li>Magical Defense : " + thisArmour.getBaseMagDef() + "</li>" + 
	    		"</ul>"
			);
	        
			Tooltip tooltip = new Tooltip();
			tooltip.setPrefSize(700, 160);
			tooltip.setStyle("-fx-background-color:white;");
			tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			tooltip.setGraphic(webView);
			
			setTooltipTiming(tooltip);
			this.setTooltip(tooltip);
	 }
	 
	 public static void setTooltipTiming(Tooltip tooltip) {
		    try {
		        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
		        fieldBehavior.setAccessible(true);
		        Object objBehavior = fieldBehavior.get(tooltip);

		        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
		        Field fadeTimer = objBehavior.getClass().getDeclaredField("hideTimer");

		        fieldTimer.setAccessible(true);
		        fadeTimer.setAccessible(true);
		        
		        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);
		        Timeline objFadeTimer = (Timeline) fadeTimer.get(objBehavior);

		        objTimer.getKeyFrames().clear();
		        objTimer.getKeyFrames().add(new KeyFrame(new Duration(HOVER_TO_APPEAR_DURATION)));
		        
		        objFadeTimer.getKeyFrames().clear();
		        objFadeTimer.getKeyFrames().add(new KeyFrame(new Duration(APPEAR_DURATION)));
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 private void changeBackgroundOnHover(Node node) {
		    node.styleProperty().bind(
		      Bindings
		        .when(node.hoverProperty())
		          .then(
		            new SimpleStringProperty(HOVERED_STYLE)
		          )
		          .otherwise(
		            new SimpleStringProperty(NORMAL_STYLE)
		          )
		    );
	 }

}
