package scene;



import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.base.Potion;
import logic.logics.Player;
import main.Main;
import shops.ItemShop;

public class Credit extends StackPane
{
	private boolean cheated = false;
	private static boolean backed = false;
	public Credit()
	{
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		Label label1 = new Label("ReeJyGeon Developed by:\n-FrostyCactus\n-HardenBoi");
		label1.setTextFill(Color.WHITE);
		label1.setFont(new Font("Helvetica", 50));
		Label label2 = new Label("CHEAT ACTIVATED");
		label2.setFont(new Font(90));
		label2.setTextFill(Color.RED);
		setOnMouseClicked
		(event->
			{
				if(cheated && !backed)
				{
					backed = true;
					PauseTransition pt = new PauseTransition(Duration.millis(2000));
					pt.setOnFinished(event2 -> Main.changeScene(SceneManager.mainScreenScene));
					pt.play();
				}
				else if(!backed)
				{
					Main.changeScene(SceneManager.mainScreenScene);
					backed = true;
				}
			}
		);
		Rectangle rect = new Rectangle(60, 60, Color.BLACK);
		rect.setOnMouseClicked
		(event ->
		{
			Player.player.setBaseMinPhyAtk(1000);
			Player.player.setBaseMaxPhyAtk(2000);
			Player.player.setBaseMinMagAtk(1000);
			Player.player.setBaseMaxMagAtk(2000);
			Player.player.setBaseMaxHp(2000);
			Player.player.setBaseMaxMp(2000);
			Player.player.gainMoney(5000);
			ItemShop itemShop = new ItemShop();
			if(!cheated)
			{
				for(Potion i : itemShop.getPotionList())
				{
					Player.player.gainPotion(i);
					Player.player.levelUp();
				}
			}
			Player.player.setConqueredFloor(8);
			Player.player.fullHeal();
			getChildren().clear();
			getChildren().add(label2);
			setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			cheated = true;
		}
		);
		
		getChildren().addAll(label1, rect);
		StackPane.setAlignment(rect, Pos.TOP_LEFT);
	}
	public static void setBacked(boolean backed)
	{
		Credit.backed = backed;
	}

	

}
