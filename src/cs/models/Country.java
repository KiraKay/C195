package cs.models;

import java.util.HashMap;

/**
 * Country structure for database
 */
public class Country {

	private int id;
	private String name;
	private HashMap<String, Integer> divisions;

	/**
	 * Constructor for country.
	 * 
	 * @param id
	 *            for country
	 * @param name
	 *            for country
	 */
	public Country(int id, String name) {

		this.id = id;
		this.name = name;
		this.divisions = new HashMap<>();

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
	 * @return the divisions
	 */
	public HashMap<String, Integer> getDivisions() {

		return divisions;

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
	 * @param divisions
	 *            the divisions to set
	 */
	public void setDivisions(HashMap<String, Integer> divisions) {

		this.divisions = divisions;

	}

}
