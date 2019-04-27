package scene;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ItemShop extends Pane {
	
	Label label = new Label("Item Shop");
	
	public ItemShop() {
		label.setFont(new Font(20));
		this.getChildren().add(label);
	}

}
