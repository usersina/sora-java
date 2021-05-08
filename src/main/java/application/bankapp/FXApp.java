package application.bankapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Do not start app with this, start App.java instead
public class FXApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new Pane(), 800, 600));
		primaryStage.show();
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("scenes/Index.fxml"));
			Scene scene = new Scene(root, 600, 400);
			primaryStage.setResizable(false);
			primaryStage.setTitle("My demo app");
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
