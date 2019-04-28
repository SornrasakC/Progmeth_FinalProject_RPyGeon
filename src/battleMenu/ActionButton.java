package battleMenu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ActionButton extends Button {
	
	private String item;
	
	public ActionButton (String Action) {
		//Action : Attack Spell Item Flee Hug
		this.setPadding(new Insets(10));

		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
		CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		String url;
		switch(Action) {
			case "Attack" : url = "Wood.png"; this.item = "Wood"; break;
			case "Spell" : url = "Iron_ore.png"; this.item = "Iron"; break;
			case "Item" : url = "Iron_plate.png"; this.item = "Iron Plate"; break;
			case "Flee" : url = "Iron_Sword.png"; this.item = "Iron Sword"; break;
			case "Hug"	: url = "Gem.png"; this.item = "Gem"; break;
			default : url = "Other.png"; this.item = "Other";
		}
		
		this.setGraphic(new ImageView(ClassLoader.getSystemResource(url).toString()));
	}
	
	public void highlight() {
		
		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	
	public void unhighlight() {

		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	
	public String getItem() {
		return this.item;
	}
}
