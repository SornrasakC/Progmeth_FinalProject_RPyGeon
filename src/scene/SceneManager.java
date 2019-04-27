package scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneManager {
	
	public static Village villagePane = new Village();
	
	public static Scene villageScene = new Scene(villagePane);
	public static Scene creditScene = new Scene(new Credit());
	public static Scene mainScreenScene = new Scene(new MainMenu());
	public static Scene startNamingScene = new Scene(new StartNaming());
	public static Scene confirmPrologueScene = new Scene(new ConfirmPrologue());
	public static Scene prologueScene = new Scene(new Prologue());
	public static Scene itemshopScene = new Scene(new ItemShop());
	
	public SceneManager() {
		
	}
	
	public static Village getVillagePane() {
		return villagePane;
	}

}
