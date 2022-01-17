package cs.controllers;

import java.io.IOException;

import cs.Utilities;
import cs.models.FXParent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Primary Application of JavaFX
 */
public class CSApplication extends Application {

	private Stage stage;
	private DBController database;

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;
		this.stage.setTitle("Client Scheduling Program");
		database = new DBController();
		update(Utilities.LOGIN_FXML);
		this.stage.show();

	}

	/**
	 * It will update the different scene into the stage.
	 * 
	 * @param file
	 *            scene file
	 */
	public void update(String file) {

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../views/" + file));
		try {

			Parent parent = loader.load();
			FXParent fxParent = loader.getController();
			fxParent.setApplication(this);
			fxParent.setStage(stage);
			fxParent.setDatabase(database);
			fxParent.updateUI();
			stage.setScene(new Scene(parent));

		} catch (IOException ex) {
			ex.printStackTrace();
			Utilities.error("Loading Scene", "Unable to load the scene.",
					stage);
		}

	}

}
