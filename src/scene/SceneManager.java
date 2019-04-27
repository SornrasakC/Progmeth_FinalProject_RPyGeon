package scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneManager {
	
	public static Village villagePane = new Village();
	
	public static Scene villageScene = new Scene(villagePane);
	public static Scene creditScene = new Scene(new Credit());
	public static Scene mainScreenScene = new Scene(new MainMenu());
	public static Scene StartNamingScene = new Scene(new StartNaming());
	public static Scene ConfirmPrologueScene = new Scene(new ConfirmPrologue());
	public static Scene PrologueScene = new Scene(new Prologue());
	
	public SceneManager() {
		
	}
	
	public static Village getVillagePane() {
		return villagePane;
	}

}
