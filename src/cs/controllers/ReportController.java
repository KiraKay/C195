package cs.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cs.Utilities;
import cs.models.Appointment;
import cs.models.ContactPerson;
import cs.models.FXParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the report.
 */
public class ReportController extends FXParent implements Initializable {
	
	@FXML
	private TextField firstReport, secondReport;
	@FXML
	private TableView<Appointment> reportTable;
	@FXML
	private ComboBox<String> contactField;
	@FXML
	private TableColumn<Appointment, String> idCol, titleCol, 
		typeCol, despCol, startCol, endCol, customerCol;
	private List<ContactPerson> contacts;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		
	}

	/**
	 * Updating the UI for 3 different reports.
	 */
	@Override
	public void updateUI() {
		
		firstReport.setText(String.valueOf(getDatabase().getCustomers().size()));
		List<Appointment> appointments = getDatabase().getAppointments();


		secondReport.setText(String.valueOf(appointments.size()));
		
		contacts = new ArrayList<>();
		contactField.getItems().add("");
		for (ContactPerson each: getDatabase().getContacts()) {
			contactField.getItems().add(each.getName());
			contacts.add(each);
		}
		
		idCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("appointmentId"));
		titleCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("title"));
		typeCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("type"));
		despCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("description"));
		startCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("start"));
		endCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("end"));
		customerCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("customerId"));
		updateData();
		
	}
	
	// Updating data..
	@FXML
	public void updateData() {
		
		reportTable.getItems().clear();
		int id = contactField.getSelectionModel().getSelectedIndex() - 1;
		if (id >= 0) {
			id = contacts.get(id).getId();
			List<Appointment> appointments = getDatabase().getAppointments();
			List<Appointment> reported = new ArrayList<>();
			for (Appointment each: appointments) {
				if (each.getContactId() == id) {
					reported.add(each);
				}
			}
			reportTable.getItems().setAll(reported);
		}
		
		
	}
	
	/**
	 * When back button is pressed.
	 * 
	 * @param ae source
	 */
	@FXML
	public void back(ActionEvent ae) {
		
		this.getApplication().update(Utilities.DASHBOARD_FXML);
		
	}
	
}
