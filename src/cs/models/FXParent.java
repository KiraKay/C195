package cs.models;

import cs.controllers.CSApplication;
import cs.controllers.DBController;
import javafx.stage.Stage;

/**
 * Parent class for controller
 */
public abstract class FXParent {

	private CSApplication application;
	private DBController database;
	private Stage stage;

	/**
	 * Parent controller.
	 * 
	 * @param application
	 *            cs application
	 */
	public FXParent(CSApplication application) {

		this.application = application;

	}

	/**
	 * Default Constructor
	 */
	public FXParent() {

	}

	/**
	 * To update the UI after setting scene.
	 */
	public abstract void updateUI();

	/**
	 * @return the application
	 */
	public CSApplication getApplication() {

		return application;

	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(CSApplication application) {

		this.application = application;

	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {

		return stage;

	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(Stage stage) {

		this.stage = stage;

	}

	/**
	 * @return the database
	 */
	public DBController getDatabase() {

		return database;

	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(DBController database) {

		this.database = database;

	}

}