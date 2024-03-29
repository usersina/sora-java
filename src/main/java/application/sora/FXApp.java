package application.sora;

import java.io.IOException;

import application.sora.constants.FXMLConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// Do not start app with this, start App.java instead
public class FXApp extends Application {
	private static Stage currentStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		currentStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource(FXMLConstants.LOGIN));
			// Move window on drag & drop
			root.setOnMousePressed(pressEvent -> {
				root.setOnMouseDragged(dragEvent -> {
					primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
					primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
				});
			});

			// Set window style & title
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Sora");

			// Create & show the scene
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

			// DEVONLY: Show the chat window
			// Globals.setLoggedUserId(1L);
			// new ChatWindow().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeScene(String fxml) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		// Move window on drag & drop
		root.setOnMousePressed(pressEvent -> {
			root.setOnMouseDragged(dragEvent -> {
				currentStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				currentStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		currentStage.getScene().setRoot(root);
	}

	/**
	 * Resize the main window size to match the ones passed in the params.
	 * This needs to be executed when changing from one scene to another.
	 *
	 * @param width  the new width of the scene
	 * @param height the new height of the scene
	 */
	public static void resizeWindow(int width, int height) {
		currentStage.setWidth(width);
		currentStage.setHeight(height);
	}

	/**
	 * Center the current stage on the current display screen. This is useful
	 * right after calling `resizeWindow` or `changeScene`.
	 */
	public static void centerStage() {
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
		currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);
	}

	protected static void main(String[] args) {
		launch(args);
	}
}
