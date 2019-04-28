package scene;

import entity.VillageEntityLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import main.Main;

public class ItemShop extends Pane {
	
	private Label label = new Label("Item Shop");
	private Button btn = new Button("back");
	
	public ItemShop() {
		label.setFont(new Font(20));
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.getPrimaryStage().setScene(SceneManager.villageScene);
				VillageEntityLogic.exitShop();
			}
		});
		this.getChildren().add(label);
		this.getChildren().add(btn);
	}

}
