package scene.battleOverlay;


import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import logic.logics.Player;
import magic.OffensiveMagic;
import scene.Battle;

public class SpellList extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	private static final String SPELL_CLASS = "spellListButton";
	private PauseTransition waitAnimation;
//	private static final PseudoClass SPELL_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	public SpellList()
	{
		Player.player.getMagicInventory().forEach
		(
			(magic)->
			{
				Button button = new Button(magic.toString());
				button.setOnAction
				(
					(event)->
					{
						if(magic.canUse(Player.player))
						{
							Battle.getBattleAnimation().playSpellAnimation();
							waitAnimation = new PauseTransition(Duration.millis(500));
							waitAnimation.setOnFinished
							(eventWait ->
								{
									int amount = magic.use(Player.player, Battle.getMonster());
									if(magic instanceof OffensiveMagic)
									{
										Battle.playerReport(magic.getName() + " deals " + amount + " damage!");
										Battle.setPlayerTurn(false);
									}
									else // Healing magic
									{
										Battle.getBattleAnimation().playSpellAnimation();
										Battle.playerReport(magic.getName() + " heals " + amount + " HP!");
										Battle.setPlayerTurn(false);
									}
								}
							);
							waitAnimation.play();
							Battle.endOverlay();
							Battle.setInAnimation(true);
							
						}
						else
						{
							Battle.systemReport("Not enough MP!");
						}
					}
				);
				button.setPrefWidth(WIDTH * 3 / 5);
				button.getStyleClass().add(SPELL_CLASS);
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
		returnButton.getStyleClass().add(SPELL_CLASS);
		getChildren().add(returnButton);
	}
}
