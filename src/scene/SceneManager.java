package scene;

import javafx.scene.Scene;

public class SceneManager {
	
	public static Scene villageScene = new Scene(new Village());
	public static Scene creditScene = new Scene(new Credit());
	public static Scene mainScreenScene = new Scene(new MainMenu());
	
	public SceneManager() {
		
	}

}
