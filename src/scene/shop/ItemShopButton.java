package scene.shop;

import java.lang.reflect.Field;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import shops.ItemShop;

public class ItemShopButton extends Button {
	String path;
	Image image;
	Potion thisPotion;
	
	public static final int HOVER_TO_APPEAR_DURATION = 250;
	public static final int APPEAR_DURATION = 50000;
	
	public ItemShopButton(Potion potion) {
		thisPotion = potion;
		//TODO add sprites
//		path = potion.getName() + ".png";
		path =  "WIP.png";
		image = new Image(ClassLoader.getSystemResourceAsStream(path));
		
		this.setGraphic(new ImageView(image));
		this.setPadding(new Insets(4));
		
		//TODO More graphic
		
		WebView  webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent
        (
    		"<h3>" + thisPotion.getName() + "</h3>" + thisPotion.getDescription()
		);
		
		Tooltip tooltip = new Tooltip();
		tooltip.setPrefSize(700, 120);
		tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		tooltip.setGraphic(webView);
		
		
		hackTooltipStartTiming(tooltip);
		this.setTooltip(tooltip);
		
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
	
	public static void hackTooltipStartTiming(Tooltip tooltip) {
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

}
