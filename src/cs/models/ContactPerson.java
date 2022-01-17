package cs.models;

/**
 * Contact person from database.
 */
public class ContactPerson {

	private int id;
	private String name;
	private String email;
	
	/**
	 * Constructor for Contact person.
	 * 
	 * @param id of contact
	 * @param name of contact
	 * @param email of contact
	 */
	public ContactPerson(int id, String name, String email) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		
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
	 * @return the email
	 */
	public String getEmail() {
		
		return email;
		
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
}
