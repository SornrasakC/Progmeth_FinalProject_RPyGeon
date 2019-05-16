package scene.battleOverlay;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import logic.base.Potion;
import logic.logics.Player;
import scene.Battle;

public class ItemList extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	private static final String ITEM_CLASS = "itemListButton";
	private PauseTransition animationWait;
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
						startItemAnimation();
						Battle.setInAnimation(true);
						Battle.endOverlay();
						animationWait = new PauseTransition(Duration.seconds(1));
						animationWait.setOnFinished
						(event->
							{
								Player.player.usePotion(potion);
								Battle.playerReport(Player.player.getName() + " used " + potion.getName() + "!");
								Battle.setPlayerTurn(false);
							}
						);
						animationWait.play();
						
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
	private void startItemAnimation()
	{
		Battle.getBattleAnimation().playItemAnimation();
	}
}
