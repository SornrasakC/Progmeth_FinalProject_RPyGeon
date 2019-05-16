package scene.battleOverlay;


import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import logic.logics.Player;
import magic.OffensiveMagic;
import scene.Battle;

public class SpellList extends VBox
{
	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
	private static final String SPELL_CLASS = "spellListButton";
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
							int amount = magic.use(Player.player, Battle.getMonster());
							if(magic instanceof OffensiveMagic)
							{
								Battle.getBattleAnimation().playSpellAnimation();
								Battle.playerReport(magic.getName() + " deals " + amount + " damage!");
								Battle.setPlayerTurn(false);
								Battle.setInAnimation(true);
								Battle.endOverlay();
							}
							else // Healing magic
							{
								Battle.getBattleAnimation().playSpellAnimation();
								startSpellAnimation();
								Battle.playerReport(magic.getName() + " heals " + amount + " HP!");
								Battle.setPlayerTurn(false);
								Battle.setInAnimation(true);
								Battle.endOverlay();
							}
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
	private void startSpellAnimation()
	{
		//TODO ANIMATION
	}
}
