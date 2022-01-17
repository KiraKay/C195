package cs;

import java.util.Random;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Utilities.
 * 
 * Extra required methods.
 */
public class Utilities {

	// constants..
	public static final String LOGIN_FXML = "login.fxml";
	public static final String APPOINTMENT_FXML = "appointment.fxml";
	public static final String CUSTOMER_FXML = "customer.fxml";
	public static final String REPORT_FXML = "report.fxml";
	public static final String DASHBOARD_FXML = "dashboard.fxml";
	private static Random random = new Random();

	/**
	 * Generating alert.
	 * 
	 * @param header
	 *            for alert
	 * @param text
	 *            for alert
	 * @param type
	 *            for alert
	 * @param stage
	 *            for alert
	 */
	public static void alert(String header, String text, Alert.AlertType type,
			Stage stage) {

		Alert alert = new Alert(type);
		alert.initOwner(stage);
		alert.setTitle("Smart Board");
		alert.setContentText(text);
		alert.setHeaderText(header + "!");
		alert.show();

	}

	/**
	 * Generating alert.
	 * 
	 * @param header
	 *            for alert
	 * @param text
	 *            for alert
	 * @param stage
	 *            for alert
	 */
	public static void alert(String header, String text, Stage stage) {

		alert(header, text, Alert.AlertType.INFORMATION, stage);

	}

	/**
	 * Generating error.
	 * 
	 * @param header
	 *            for alert
	 * @param text
	 *            for alert
	 * @param stage
	 *            for alert
	 */
	public static void error(String header, String text, Stage stage) {

		alert(header, text, Alert.AlertType.ERROR, stage);

	}

	/**
	 * @return random ID generator
	 */
	public static int getID() {

		return random.nextInt(1000000); //When you create a new customer this is what generates the ID

	}

}
