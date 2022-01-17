package cs.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import cs.Utilities;
import cs.models.Appointment;
import cs.models.FXParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Controller for the Dashboard.
 */
public class DashboardController extends FXParent implements Initializable {

    private static boolean display = false;
    
	@Override
	public void initialize(URL url, ResourceBundle resources) {

	}

	@Override
	public void updateUI() {

		String output = "Appointments for today";
		List<Appointment> appointments = getDatabase().getAppointments();
		LocalDate now = LocalDate.now();
		for (Appointment each : appointments) {
			if (each.getStart().toLocalDate().isEqual(now)) {
				output += "\n -> " + each.getTitle() + " ("
						+ each.getDescription() + ")";
			}
		}
		if (!display) {
            Utilities.alert("Appointments", output, getStage());
            display = true;
        }
		
	}

	/**
	 * When customer button is pressed to open the corresponding screen.
	 * 
	 * @param ae
	 *            event
	 */
	@FXML
	public void customers(ActionEvent ae) {
		this.getApplication().update(Utilities.CUSTOMER_FXML);
	}

	/**
	 * When appointment button is pressed to open the corresponding screen.
	 * 
	 * @param ae
	 *            event
	 */
	@FXML
	public void appointments(ActionEvent ae) {
		this.getApplication().update(Utilities.APPOINTMENT_FXML);
	}

	/**
	 * When report button is pressed to open the corresponding screen.
	 * 
	 * @param ae
	 *            event
	 */
	@FXML
	public void reports(ActionEvent ae) {
		this.getApplication().update(Utilities.REPORT_FXML);
	}

	/**
	 * When quit button is pressed to open the corresponding screen.
	 * 
	 * @param ae
	 *            event
	 */
	@FXML
	public void quit(ActionEvent ae) {
		System.exit(0);
	}

}
