package scene;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainMenu extends Application {
	private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    
    private Label menuTitle = new Label("Main Menu");
    private Pane root = new Pane();
    private VBox menuBox = new VBox(15);
    
    private List<Pair<String, Runnable>> menuList = Arrays.asList(
            new Pair<String, Runnable>("Start", () -> {}),
            new Pair<String, Runnable>("Credits", () -> {}),
            new Pair<String, Runnable>("Exit", Platform::exit)
    );
    
    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuList.forEach(data -> {
        	MainMenuItem item = new MainMenuItem(data.getKey());
            item.setOnAction(data.getValue());
            Rectangle clip = new Rectangle(300, 30);
            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        Label title = new Label("RJyGeon");
        title.setFont(new Font(50));
        title.setPrefWidth(WIDTH);
        title.setAlignment(Pos.CENTER);
//        title.setTranslateX(WIDTH / 2 - title.getWidth() / 2);
        title.setTranslateY(HEIGHT / 4);
        Rectangle rectBG = new Rectangle(WIDTH, HEIGHT, Color.WHITE);
        root.getChildren().add(rectBG);
        root.getChildren().add(title);
        root.getChildren().add(menuBox);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		addMenu(480, 300);
		Scene scene = new Scene(root);
        primaryStage.setTitle("RJyGeon");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
