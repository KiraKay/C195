package cs.controllers;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cs.Utilities;
import cs.models.Country;
import cs.models.Customer;
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
 * Controller for the Customer.
 */
public class CustomerController extends FXParent implements Initializable {

	@FXML
	private TextField idField, nameField, addressField, postalField, phoneField;
	@FXML
	private ComboBox<String> countryField, stateField;
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, String> idCol, nameCol, addressCol, postalCol,
			phoneCol, countryCol, stateCol;
	private boolean update = false;
	private Map<String, Country> countries;
	private List<Customer> customers;
	private int id;

	@Override
	public void initialize(URL url, ResourceBundle resources) {

	}

	@Override
	public void updateUI() {

		countries = getDatabase().getCountries();
		countryField.getItems().add("");
		for (String each : countries.keySet()) {
			countryField.getItems().add(each);
		}
		countryField.getSelectionModel().select(0);
		idCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("id"));
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("name"));
		addressCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("address"));
		postalCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("postal"));
		phoneCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("phone"));
		countryCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("country"));
		stateCol.setCellValueFactory(
				new PropertyValueFactory<Customer, String>("state"));
		idField.setEditable(false);
		id = Utilities.getID();
		updateData();

	}

	/**
	 * It will update the state and the customer table in the screen.
	 */
	@FXML
	public void updateData() {

		stateField.getItems().clear();
		stateField.getItems().add("");
		String country = countryField.getSelectionModel().getSelectedItem();
		// I was using many lines of code to iterate through the different states
		// of the country and adding them into the JComboBox whenever user
		// change the country name from the user-interaction based JComboBox
		// That Lambda-Expression is used to sum-up 7 to 8 lines of code, in a 
		// single line to make it very clear and straight.
		if (!country.isEmpty()) {
			countries.get(country).getDivisions().keySet()
	        	.stream().forEach(a -> stateField.getItems().add(a));
			stateField.getSelectionModel().clearAndSelect(0);
		}
		customers = getDatabase().getCustomers();
		customerTable.getItems().clear();
		customerTable.getItems().setAll(customers);

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

		if (customerTable.getSelectionModel().getSelectedIndex() != -1) {

			Customer customer = customers
					.get(customerTable.getSelectionModel().getSelectedIndex());
			id = customer.getId();
			idField.setText(customer.getId() + "");
			nameField.setText(customer.getName());
			addressField.setText(customer.getAddress());
			postalField.setText(customer.getPostal());
			phoneField.setText(customer.getPhone());
			countryField.getSelectionModel().select(customer.getCountry());
			stateField.getSelectionModel().select(customer.getState());
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

		String name = nameField.getText();
		String address = addressField.getText();
		String code = postalField.getText();
		String phone = phoneField.getText();
		String country = countryField.getSelectionModel().getSelectedItem();
		String state = stateField.getSelectionModel().getSelectedItem();
		if (name.isEmpty() || address.isEmpty() || code.isEmpty()
				|| phone.isEmpty() || country.isEmpty() || state.isEmpty()) {
			Utilities.error("Customer", "Empty fields are not allowed.",
					getStage());
		} else {

			if (update) {
				getDatabase().deleteCustomer(id);
			}
			getDatabase().addCustomer(
					new Customer(id, name, address, code, phone, country, state,
							countries.get(country).getDivisions().get(state)));
			update = false;
			id = Utilities.getID();
			clear(ae);
			updateData();

		}

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

		if (customerTable.getSelectionModel().getSelectedIndex() != -1) {

			getDatabase().deleteCustomer(customers
					.get(customerTable.getSelectionModel().getSelectedIndex())
					.getId());
			updateData();
			Utilities.alert("Record Removed", "You have deleted a record", getStage());

		} else {
			Utilities.error("Deletion", "Select one row from the table",
					getStage());
		}

	}
	
	/**
	 * It will clear the data from all
	 * of the fields.
	 * 
	 * @param ae click event
	 */
	@FXML
	public void clear(ActionEvent ae) {
		
		update = false;
		idField.setText("");
		nameField.setText("");
		addressField.setText("");
		postalField.setText("");
		phoneField.setText("");
		countryField.getSelectionModel().clearAndSelect(0);
		
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
