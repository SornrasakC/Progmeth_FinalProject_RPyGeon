package scene.battleOverlay;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import logic.base.Potion;
import logic.logics.Player;
import scene.Battle;

public class ItemList extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	private static final String ITEM_CLASS = "itemListButton";
	public ItemList()
	{
		ArrayList<Potion> list = Player.player.getPotionInventory().keySet().stream().collect(Collectors.toCollection(ArrayList::new));
		list.sort((a, b) -> compare(a, b));
		list.forEach
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
						Battle.setPlayerTurn(false);
						Battle.setInAnimation(true);
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
	private int compare(Potion a, Potion b)
	{
		return a.getPriority() - b.getPriority();
	}

}
