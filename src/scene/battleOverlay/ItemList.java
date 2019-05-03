package scene.battleOverlay;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import logic.logics.Player;
import scene.Battle;

public class ItemList extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	private static final String ITEM_CLASS = "itemListButton";
	public ItemList()
	{
		Player.player.getPotionInventory().keySet().forEach
		(
			potion ->
			{
				Button button = new Button(potion.toString() + " #" + Player.player.getPotionInventory().get(potion));
				button.setOnAction
				(
					action ->
					{
						Player.player.usePotion(potion);
						Battle.report(Player.player.getName() + " used " + potion.getName() + "!");
						Battle.endOverlay();
					}
				);
				button.setPrefWidth(WIDTH * 3 / 5);
				button.getStyleClass().add(ITEM_CLASS);
				getChildren().add(button);
			}
		);
		Button returnButton = new Button("Return");
		returnButton.setOnAction
		(
			(event)->
			{
				Battle.endOverlay();
			}
		);
		returnButton.setPrefWidth(WIDTH * 3 / 5);
		returnButton.getStyleClass().add(ITEM_CLASS);
		getChildren().add(returnButton);
	}

}
