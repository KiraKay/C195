package cs.models;

/**
 * Customer structure class
 */
public class Customer {

	private int id;
	private String name;
	private String address;
	private String postal;
	private String phone;
	private String country;
	private String state;
	private int divisionId;

	/**
	 * Constructor for the customer.
	 * 
	 * @param id
	 *            of customer
	 * @param name
	 *            of customer
	 * @param address
	 *            of customer
	 * @param postal
	 *            of customer
	 * @param phone
	 *            of customer
	 * @param country
	 *            of customer
	 * @param state
	 *            of customer
	 * @param divisionId
	 *            of customer
	 */
	public Customer(int id, String name, String address, String postal,
			String phone, String country, String state, int divisionId) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.postal = postal;
		this.phone = phone;
		this.country = country;
		this.state = state;
		this.divisionId = divisionId;

	}

	/**
	 * @return the id
	 */
	public int getId() {

		return id;

	}

	/**
	 * @return the name
	 */
	public String getName() {

		return name;

	}

	/**
	 * @return the address
	 */
	public String getAddress() {

		return address;

	}

	/**
	 * @return the postal
	 */
	public String getPostal() {

		return postal;

	}

	/**
	 * @return the phone
	 */
	public String getPhone() {

		return phone;

	}

	/**
	 * @return the country
	 */
	public String getCountry() {

		return country;

	}

	/**
	 * @return the state
	 */
	public String getState() {

		return state;

	}

	/**
	 * @return the divisionId
	 */
	public int getDivisionId() {

		return divisionId;

	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {

		this.id = id;

	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {

		this.name = name;

	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {

		this.address = address;

	}

	/**
	 * @param postal
	 *            the postal to set
	 */
	public void setPostal(String postal) {

		this.postal = postal;

	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {

		this.phone = phone;

	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {

		this.country = country;

	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {

		this.state = state;

	}

	/**
	 * @param divisionId
	 *            the divisionId to set
	 */
	public void setDivisionId(int divisionId) {

		this.divisionId = divisionId;

	}

}
