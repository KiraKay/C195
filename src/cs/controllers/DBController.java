package cs.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.models.Appointment;
import cs.models.ContactPerson;
import cs.models.Country;
import cs.models.Customer;

/**
 * Database Controller.
 */
public class DBController {

	private Connection connection;
	private Statement statement;
	private int userId;

	/**
	 * Constructor for the database to connect to the database and execute it
	 * using the statements.
	 */
	public DBController() {

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/client_schedule", "sqlUser",
					"Passw0rd!");
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Login the user using the email and password. return true if logged in
	 * else false.
	 * 
	 * @param email
	 *            for login
	 * @param password
	 *            for login
	 * @return true if logged in else false
	 */
	public boolean loginUser(String email, String password) {

		try {
			ResultSet result = statement
					.executeQuery("select user_id from users where user_name='"
							+ email + "' and password='" + password + "'");
			if (result.next()) {
				userId = result.getInt(1);
				return true;
			}
		} catch (SQLException e) {
		}
		return false;

	}

	/**
	 * Get countries and divisions from the database and return in the map form.
	 * 
	 * @return map of countries.
	 */
	public Map<String, Country> getCountries() {

		Map<String, Country> countries = new HashMap<>();
		try {
			ResultSet result = statement.executeQuery(
					"select division_id,first_level_divisions.country_id,division,country from countries join first_level_divisions where countries.country_id = first_level_divisions.country_id;");
			while (result.next()) {
				int divId = result.getInt(1);
				int countId = result.getInt(2);
				String division = result.getString(3);
				String country = result.getString(4);
				if (!countries.containsKey(country)) {
					countries.put(country, new Country(countId, country));
				}
				countries.get(country).getDivisions().put(division, divId);
			}

		} catch (SQLException e) {
		}
		return countries;

	}

	/**
	 * Get data for the customers from the database.
	 * 
	 * @return list of customers.
	 */
	public List<Customer> getCustomers() {

		List<Customer> customers = new ArrayList<>();
		try {
			ResultSet results = statement.executeQuery(
					"select customer_id,customer_name,address,postal_code,phone,country,division,customers.division_id from customers join first_level_divisions join countries where customers.division_id = first_level_divisions.division_id and first_level_divisions.country_id = countries.country_id;");
			while (results.next()) {
				customers.add(
						new Customer(results.getInt(1), results.getString(2),
								results.getString(3), results.getString(4),
								results.getString(5), results.getString(6),
								results.getString(7), results.getInt(8)));
			}
		} catch (SQLException e) {
		}
		return customers;

	}

	/**
	 * Add customer in the database.
	 * 
	 * @param customer
	 *            to add
	 */
	public void addCustomer(Customer customer) {

		try {

			Date date = new Date(new java.util.Date().getTime());
			statement.execute("insert into customers values(" + customer.getId()
					+ ", '" + customer.getName() + "', '"
					+ customer.getAddress() + "', '" + customer.getPostal()
					+ "'," + "'" + customer.getPhone() + "', '" + date
					+ "','root','" + date + "','root',"
					+ customer.getDivisionId() + ");");

		} catch (SQLException e) {
		}

	}

	/**
	 * Delete customer from the database.
	 * 
	 * @param id
	 *            to delete
	 */
	public void deleteCustomer(int id) {

		try {

			statement.execute(
					"delete from appointments where customer_id=" + id);
			statement.execute("delete from customers where customer_id=" + id);

		} catch (SQLException e) {
		}

	}

	/**
	 * Read contacts from the database and return it.
	 * 
	 * @return list of contacts
	 */
	public List<ContactPerson> getContacts() {

		List<ContactPerson> contacts = new ArrayList<>();
		try {
			ResultSet results = statement
					.executeQuery("select * from contacts;");
			while (results.next()) {
				contacts.add(new ContactPerson(results.getInt(1),
						results.getString(2), results.getString(3)));
			}
		} catch (SQLException e) {
		}
		return contacts;

	}

	/**
	 * Read appointments from the database and return it.
	 * 
	 * @return list of appointments
	 */
	public List<Appointment> getAppointments() {

		List<Appointment> apts = new ArrayList<>();
		try {
			ResultSet results = statement
					.executeQuery("select * from appointments;");
			while (results.next()) {
				apts.add(
						new Appointment(results.getInt(1), results.getString(2),
								results.getString(3), results.getString(4),
								results.getString(5), results.getTimestamp(6).toLocalDateTime(),
								results.getTimestamp(7).toLocalDateTime(), results.getInt(12),
								results.getInt(13), results.getInt(14)));
			}
		} catch (SQLException e) {
		}
		return apts;

	}

	/**
	 * Add appointment into the database.
	 * 
	 * @param appointment
	 *            to add
	 */
	public void addAppointment(Appointment appointment) {

		try {

			Date date = new Date(new java.util.Date().getTime());
			statement.execute("insert into appointments " + "values("
					+ appointment.getAppointmentId() + ",'"
					+ appointment.getTitle() + "','"
					+ appointment.getDescription() + "'," + "'"
					+ appointment.getLocation() + "','" + appointment.getType()
					+ "','" + appointment.getStart() + "'," + "'"
					+ appointment.getEnd() + "','" + date
					+ "','root','" + date + "','root',"
					+ appointment.getCustomerId() + ","
					+ appointment.getUserId() + "," + appointment.getContactId()
					+ ");");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Delete appointment from the database.
	 * 
	 * @param id
	 *            to delete
	 */
	public void deleteAppointment(int id) {

		try {

			statement.execute(
					"delete from appointments where appointment_id=" + id);

		} catch (SQLException e) {
		}

	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

}
