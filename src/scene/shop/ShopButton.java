package scene.shop;

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
import logic.base.Potion;
import logic.logics.Player;
import scene.SceneManager;
import sharedObject.RenderableHolder;
import shops.BlackSmith;
import shops.ItemShop;

public class ShopButton extends Button {
	private String path;
	private Image image;
	private Potion thisPotion;
	private Weapon thisWeapon;
	private int price;
	
	private static final int HOVER_TO_APPEAR_DURATION = 250;
	private static final int APPEAR_DURATION = 50000;
	
//	private static final String NORMAL_STYLE = "-fx-background-color: slateblue; -fx-text-fill: white;";
	private static final String NORMAL_STYLE =  "-fx-background-color:" +
										        "#3c7fb1," +
										        "linear-gradient(#fafdfe, #e8f5fc)," +
										        "linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);" +
											    "-fx-background-insets: 0,1,2;" +
											    "-fx-background-radius: 3,2,1;" +
											    "-fx-padding: 3 30 3 30;" +
											    "-fx-text-fill: black;" +
											    "-fx-font-size: 14px;";
	private static final String HOVERED_STYLE = "-fx-background-color:" +
										        "#3c7fb1," +
										        "linear-gradient(#fafdfe, #fcf2e8)," +
										        "linear-gradient(#fdf2e8 0%, #fcebd9 49%, #fddebf 50%, #f5cfa8 100%);" +
											    "-fx-background-insets: 0,1,2;" +
											    "-fx-background-radius: 3,2,1;" +
											    "-fx-padding: 3 30 3 30;" +
											    "-fx-text-fill: black;" +
											    "-fx-font-size: 14px;";
	private static final String HOVERED_STYLE_RED = "-fx-background-color:" +
										        "#af3c3c," +
										        "linear-gradient(#fafdfe, #fce8e8)," +
										        "linear-gradient(#fde8e8 0%, #fcd9d9 49%, #fdbfbf 50%, #f5a8a8 100%);" +
											    "-fx-background-insets: 0,1,2;" +
											    "-fx-background-radius: 3,2,1;" +
											    "-fx-padding: 3 30 3 30;" +
											    "-fx-text-fill: black;" +
											    "-fx-font-size: 14px;";
	
	public ShopButton(Potion potion) {
		this.thisPotion = potion;
		this.price = potion.getCost();
		
		//select sprite
		switch(potion.getName()) {
		case("Red Cookies"): image = RenderableHolder.redCookie; break;
		case("Blue Cookies"): image = RenderableHolder.blueCookie; break;
		case("Fairly Normal HP Potion"): image = RenderableHolder.hpPotion; break;
		case("Fairly Normal MP Potion"): image = RenderableHolder.mpPotion; break;
		case("Chicken Dinner"): image = RenderableHolder.chickenDinner; break;
		case("Cocaine"): image = RenderableHolder.cocain; break;
		case("Phoenix Kit"): image = RenderableHolder.phoenixKit; break;
		case("Low Quality Super Duper Lucky Randomly Recovering Potion"): image = RenderableHolder.lowRecPotion; break;
		case("High Quality Super Duper Lucky Randomly Recovering Potion"): image = RenderableHolder.highRecPotion; break;
		case("M44"): image = RenderableHolder.m44; break;
		case("Shroud's right arm"): image = RenderableHolder.rightArm; break;
		case("Oten's tear"): image = RenderableHolder.otenTear; break;
		case("Trap Card: Mirror Force"): image = RenderableHolder.mirrorForce; break;
		default:path =  "WIP.png";
				image = new Image(ClassLoader.getSystemResourceAsStream(path));
				break;
		}
		
		
		this.setGraphic(new ImageView(image));
		this.setPadding(new Insets(4));
		
		
		
		setItemCustomTooltip();
		this.setStyle(NORMAL_STYLE);
		changeBackgroundOnHover(this);
		
//		this.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
		
	}
	
	public ShopButton(Weapon weapon) {
		this.thisWeapon = weapon;
		this.price = weapon.getPrice();
				
		//select sprite
		switch(weapon.getName()) {
		case("Red Cookies"): image = RenderableHolder.redCookie; break;
		case("Blue Cookies"): image = RenderableHolder.blueCookie; break;
		case("Fairly Normal HP Potion"): image = RenderableHolder.hpPotion; break;
		case("Fairly Normal MP Potion"): image = RenderableHolder.mpPotion; break;
		case("Chicken Dinner"): image = RenderableHolder.chickenDinner; break;
		case("Cocaine"): image = RenderableHolder.cocain; break;
		case("Phoenix Kit"): image = RenderableHolder.phoenixKit; break;
		case("Low Quality Super Duper Lucky Randomly Recovering Potion"): image = RenderableHolder.lowRecPotion; break;
		case("High Quality Super Duper Lucky Randomly Recovering Potion"): image = RenderableHolder.highRecPotion; break;
		case("M44"): image = RenderableHolder.m44; break;
		case("Shroud's right arm"): image = RenderableHolder.rightArm; break;
		case("Oten's tear"): image = RenderableHolder.otenTear; break;
		case("Trap Card: Mirror Force"): image = RenderableHolder.mirrorForce; break;
		default:path =  "WIP.png";
				image = new Image(ClassLoader.getSystemResourceAsStream(path));
				break;
		}
		
		this.setGraphic(new ImageView(image));
		this.setPadding(new Insets(4));
		
		
		
		setWeaponCustomTooltip();
		this.setStyle(NORMAL_STYLE);
		changeBackgroundOnHover(this);
	}
	
	public void setLogic(ItemShop itemShop){
		ShopButton node = this;
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				System.out.println("Potion bought : " + thisPotion.getName());
				Player.player.buyPotion(itemShop, thisPotion);
				SceneManager.getItemshopPane().updateMoney();
				if(Player.player.getMoney() < price) {
	        		node.setStyle(HOVERED_STYLE_RED);
	        	}else {
	        		node.setStyle(HOVERED_STYLE);
	        	}
				//add play sound
			}
		});
	}
	
	public void setLogic(BlackSmith blacksmith){
		ShopButton node = this;
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				System.out.println("Weapon bought : " + thisWeapon.getName());
				Player.player.buyWeapon(blacksmith, thisWeapon);
				SceneManager.getBlacksmithShopPane().updateMoney();
				if(Player.player.getMoney() < price) {
	        		node.setStyle(HOVERED_STYLE_RED);
	        	}else {
	        		node.setStyle(HOVERED_STYLE);
	        	}
				//add play sound
			}
		});
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
//		    node.styleProperty().bind(
//		      Bindings
//		        .when(node.hoverProperty())
//		          .then(
//		            new SimpleStringProperty(HOVERED_STYLE)
//		          )
//		          .otherwise(
//		            new SimpleStringProperty(NORMAL_STYLE)
//		          )
//		    );
		 
		 node.setOnMouseEntered(e -> {
			 if(Player.player.getMoney() < price) {
	        		node.setStyle(HOVERED_STYLE_RED);
	        	}else {
	        		node.setStyle(HOVERED_STYLE);
	        	}
		 });
		 node.setOnMouseExited(e -> {
			 node.setStyle(NORMAL_STYLE);
		 });
		  }
	 
	 private void setItemCustomTooltip() {
		 
		WebView  webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent
        (
    		"<h3>" + thisPotion.getName() + " — Price: " + thisPotion.getCost() + " Gold</h3>" + thisPotion.getDescription()
		);
        
		Tooltip tooltip = new Tooltip();
		tooltip.setPrefSize(700, 120);
		tooltip.setStyle("-fx-background-color:white;");
		tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		tooltip.setGraphic(webView);
		
		setTooltipTiming(tooltip);
		this.setTooltip(tooltip);
	 }
	 
	 private void setWeaponCustomTooltip() {
		 WebView  webView = new WebView();
	        WebEngine webEngine = webView.getEngine();
	        webEngine.loadContent
	        (
	    		"<h3>" + thisWeapon.getName() + " — Price: " + thisWeapon.getPrice() + " Gold</h3>" + thisWeapon.getDescription() +
	    		"<ul = \"stats\">" +
	    		"<li>Physical Attack : " + thisWeapon.getBaseMinPhyAtk() + " - " + thisWeapon.getBaseMaxPhyAtk() + "</li>" + 
	    		"<li>Magical Attack : " + thisWeapon.getBaseMinMagAtk() + " - " + thisWeapon.getBaseMaxMagAtk() + "</li>" + 
	    		"</ul>"
			);
	        
			Tooltip tooltip = new Tooltip();
			tooltip.setPrefSize(700, 150);
			tooltip.setStyle("-fx-background-color:white;");
			tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			tooltip.setGraphic(webView);
			
			setTooltipTiming(tooltip);
			this.setTooltip(tooltip);
	 }

}
