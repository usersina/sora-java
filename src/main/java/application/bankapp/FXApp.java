package application.bankapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// Do not start app with this, start App.java instead
public class FXApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/Index.fxml"));
			// Move window on drag & drop
			root.setOnMousePressed(pressEvent -> {
				root.setOnMouseDragged(dragEvent -> {
					primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
					primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
				});
			});

			// Set window style & title
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setTitle("My demo app");
			primaryStage.setResizable(false);

			// Create & show the scene
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
