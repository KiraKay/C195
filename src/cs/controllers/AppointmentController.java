package cs.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cs.Utilities;
import cs.models.Appointment;
import cs.models.ContactPerson;
import cs.models.FXParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the Appointment
 */
public class AppointmentController extends FXParent implements Initializable {

	@FXML
	private TextField idField, titleField, descriptionField, locationField,
			ftField, stField, typeField;
	@FXML
	private DatePicker sdField, fdField;
	@FXML
	private ComboBox<String> customerField, contactField;
	@FXML
	private TableView<Appointment> appointmentTable;
	@FXML
	private TableColumn<Appointment, String> idCol;
	@FXML
	private TableColumn<Appointment, String> descriptionCol;
	@FXML
	private TableColumn<Appointment, String> appointmentCol;
	@FXML
	private TableColumn<Appointment, String> locationCol;
	@FXML
	private TableColumn<Appointment, String> aptStartCol;
	@FXML
	private TableColumn<Appointment, String> aptFinishCol;
	@FXML
	private TableColumn<Appointment, String> typeCol;
	@FXML
	private TableColumn<Appointment, String> customerCol;
	@FXML
	private TableColumn<Appointment, String> userCol;
	@FXML
	private TableColumn<Appointment, String> contactCol;
	@FXML
	private boolean update = false;
	private Map<String, Integer> customers;
	private Map<String, Integer> contacts;
	private List<Appointment> appointments;
	private int id;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@Override
	public void updateUI() {

		customers = new HashMap<>();
		contacts = new HashMap<>();

		// That Lambda-Expression is used to fetch the
		// data of the customers from the database and
		// create a map of Customers with a key as customer
		// name and value as the object.
		// It also update the JComboBox of customers while
		// iterating through all of the customers.
		getDatabase().getCustomers().stream().forEach(cust -> {
			customers.put(cust.getName(), cust.getId());
			customerField.getItems().add(cust.getName());
		});

		for (ContactPerson each : getDatabase().getContacts()) {
			contacts.put(each.getName(), each.getId());
			contactField.getItems().add(each.getName());
		}
		customerField.getSelectionModel().clearAndSelect(0);
		contactField.getSelectionModel().clearAndSelect(0);
		idCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("appointmentId"));
		appointmentCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("title"));
		descriptionCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("Description"));
		locationCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("location"));
		aptStartCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("start"));
		aptFinishCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("end"));
		typeCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("Type"));
		customerCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("customerId"));
		userCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("userId"));
		contactCol.setCellValueFactory(
				new PropertyValueFactory<Appointment, String>("contactId"));
		idField.setEditable(false);
		id = Utilities.getID();
		updateData();

	}

	/**
	 * Updating the data of the appointments
	 */
	public void updateData() {

		appointments = getDatabase().getAppointments();
		appointmentTable.getItems().clear();
		appointmentTable.getItems().setAll(appointments);

	}

	/**
	 * When select button is pressed, it will update the data of selected row in
	 * the fields.
	 *
	 * @param ae
	 *            source
	 */
	@FXML
	public void select(ActionEvent ae) {

		if (appointmentTable.getSelectionModel().getSelectedIndex() != -1) {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
			Appointment appointment = appointments.get(
					appointmentTable.getSelectionModel().getSelectedIndex());
			id = appointment.getAppointmentId();
			idField.setText(appointment.getAppointmentId() + "");
			titleField.setText(appointment.getTitle() + "");
			descriptionField.setText(appointment.getDescription() + "");
			locationField.setText(appointment.getLocation() + "");
			typeField.setText(appointment.getType() + "");
			sdField.setValue(appointment.getStart().toLocalDate());
			fdField.setValue(appointment.getEnd().toLocalDate());
			stField.setText(appointment.getStart().toLocalTime().format(dtf));
			ftField.setText(appointment.getEnd().toLocalTime().format(dtf));
			String cust = "", cont = "";
			for (String customer : customers.keySet()) {
				if (customers.get(customer) == appointment.getCustomerId()) {
					cust = customer;
					break;
				}
			}
			for (String contact : contacts.keySet()) {
				if (contacts.get(contact) == appointment.getContactId()) {
					cont = contact;
					break;
				}
			}
			customerField.getSelectionModel().select(cust);
			contactField.getSelectionModel().select(cont);
			update = true;

		} else {
			Utilities.error("Updation", "Select one row from the table",
					getStage());
		}

	}

	/**
	 * When create button is pressed, it will create a new appointment and
	 * update the table.
	 *
	 * @param ae
	 *            source
	 */
	@FXML
	public void create(ActionEvent ae) {

		String title = titleField.getText();
		String desp = descriptionField.getText();
		String location = locationField.getText();
		String type = typeField.getText();
		String st = stField.getText();
		String ft = ftField.getText();
		LocalTime stTime, ftTime;
		try {
			stTime = LocalTime.parse(st);
			ftTime = LocalTime.parse(ft);
		} catch (Exception e) {
			Utilities.error("Time Error", "Time should be in format HH:MM like 22:13", getStage());
			return;
		}
		int custId = customers
				.get(customerField.getSelectionModel().getSelectedItem());
		int contId = contacts
				.get(contactField.getSelectionModel().getSelectedItem());
		int userId = getDatabase().getUserId();

		if (title.isEmpty() || desp.isEmpty() || location.isEmpty()
				|| type.isEmpty() || st.isEmpty() || ft.isEmpty()) {
			Utilities.error("Appointments", "Empty fields are not allowed.",
					getStage());
		} else {

			LocalDateTime start = LocalDateTime.of(sdField.getValue(), stTime);
			LocalDateTime end = LocalDateTime.of(fdField.getValue(), ftTime);
			int startHour = stTime.getHour();
			int endHour = ftTime.getHour();

			if (stTime.equals(ftTime) || stTime.isAfter(ftTime)) {
				Utilities.error("Time Error", "Starting time should be before finishing time", getStage());
			} else if (!start.toLocalDate().isEqual(end.toLocalDate())) {
				Utilities.error("Date Error", "Starting and finishing date of appointment should be same!", getStage());
			} else if (startHour < 8 || startHour > 21 || endHour < 8 || endHour > 21) {
				Utilities.error("Time Error", "Appointments should be between 8:00 to 21:59", getStage());
			} else {

				List<Appointment> appointments = getDatabase().getAppointments();
				for (Appointment each: appointments) {
					if (each.getStart().toLocalDate().isEqual(sdField.getValue()) ) {

						LocalTime astTime = each.getStart().toLocalTime();
						LocalTime aftTime = each.getEnd().toLocalTime();

						if (
								(stTime.equals(astTime) || ftTime.equals(aftTime)
										|| stTime.equals(aftTime) || ftTime.equals(astTime) ||

										(stTime.isAfter(astTime) && stTime.isBefore(aftTime)) ||
										(ftTime.isAfter(astTime) && ftTime.isBefore(aftTime)) ||
										(astTime.isAfter(stTime) && astTime.isBefore(ftTime)) ||
										(aftTime.isAfter(stTime) && aftTime.isBefore(ftTime))
								)
						) {
							Utilities.error("Time Error", "Appointments date and time is "
									+ "overlapping with other appointment", getStage());
							return;
						}
					}
				}

				if (update) {
					getDatabase().deleteAppointment(id);
				}
				getDatabase().addAppointment(new Appointment(id, title, desp,
						location, type, start, end, custId, userId,
						contId));
				update = false;
				id = Utilities.getID();
				updateData();

			}

		}

	}

	/**
	 * Clear the fields.
	 *
	 * @param ae action event
	 */
	@FXML
	public void clear(ActionEvent ae) {

		update = false;
		idField.setText("");
		titleField.setText("");
		descriptionField.setText("");
		locationField.setText("");
		ftField.setText("");
		stField.setText("");
		typeField.setText("");
		sdField.setValue(null);
		fdField.setValue(null);
		customerField.getSelectionModel().clearAndSelect(0);
		contactField.getSelectionModel().clearAndSelect(0);

	}

	/**
	 * When delete button is pressed, it will delete the selected row and update
	 * the table.
	 *
	 * @param ae
	 *            source
	 */
	@FXML
	public void delete(ActionEvent ae) {

		if (appointmentTable.getSelectionModel().getSelectedIndex() != -1) {

			getDatabase()
					.deleteAppointment(
							appointments
									.get(appointmentTable.getSelectionModel()
											.getSelectedIndex())
									.getAppointmentId());
			updateData();

		} else {
			Utilities.error("Deletion", "Select one row from the table",
					getStage());
		}

	}

	/**
	 * When back button is pressed, we will move to the dashboard.
	 *
	 * @param ae
	 *            source
	 */
	@FXML
	public void back(ActionEvent ae) {
		this.getApplication().update(Utilities.DASHBOARD_FXML);
	}

}
