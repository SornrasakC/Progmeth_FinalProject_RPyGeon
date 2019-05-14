package scene;

import entity.VillageEntityLogic;
import item.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import logic.base.Potion;
import main.Main;
import scene.shop.ItemShopButton;

public class BlacksmithShop extends GridPane {
	
	private static final double VGAP = 20;
	private static final double HGAP = 20;
	
	private static final int BUTTON_SIZE = 230;
	
	private Label label = new Label("Item Shop");
	private Button btn = new Button("back");
	private shops.BlackSmith blacksmithShop;
	private int notAvailableItem = 0;
	
	public BlacksmithShop() {
		blacksmithShop = new shops.BlackSmith();
		label.setFont(new Font(40));
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.changeScene(SceneManager.villageScene);
				VillageEntityLogic.exitShop();
			}
		});
		btn.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		this.add(btn, 0, 0);
		this.setHgap(HGAP);
		this.setVgap(VGAP);
		this.setPadding(new Insets(20));
		
		this.notAvailableItem = blacksmithShop.getWeaponToSellList().size() - blacksmithShop.getWeaponAvailableList(logic.logics.Player.player).size();
		System.out.println(this.notAvailableItem + "");
		
		int i = 1;
		int j = 0;
		for(Weapon weapon : blacksmithShop.getWeaponAvailableList(logic.logics.Player.player)) {
			System.out.println(weapon.getName());
		}
		
		Button btt = new Button("test");
		SVGPath shape = new SVGPath();
		shape.setContent("M 0 40 L 40 0 L 400 0 L 400 80 L 40 80 Z ");
		btt.setShape(shape);
		btt.setPrefSize(150, 50);
		this.add(btt, 1, 0);
		

	}

	
}
