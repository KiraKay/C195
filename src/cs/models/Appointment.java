 package cs.models;

import java.time.LocalDateTime;

/**
 * Appointment structure class
 */
public class Appointment {

	private int appointmentId;
	private String title;
	private String description;
	private String location;
	private String type;
	private LocalDateTime start;
	private LocalDateTime end;
	private int customerId;
	private int userId;
	private int contactId;
	
	/**
	 * Constructor.
	 * 
	 * @param appointmentId of appointment
	 * @param title of appointment
	 * @param description of appointment
	 * @param location of appointment
	 * @param type of appointment
	 * @param start of appointment
	 * @param end of appointment
	 * @param customerId of appointment
	 * @param userId of appointment
	 * @param contactId of appointment
	 */
	public Appointment(int appointmentId, String title, String description,
			String location, String type, LocalDateTime start, LocalDateTime end, int customerId,
			int userId, int contactId) {
		
		this.appointmentId = appointmentId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.type = type;
		this.start = start;
		this.end = end;
		this.customerId = customerId;
		this.userId = userId;
		this.contactId = contactId;
		
	}

	/**
	 * @return the appointmentId
	 */
	public int getAppointmentId() {
		
		return appointmentId;
		
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		
		return title;
		
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		
		return description;
		
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		
		return location;
		
	}

	/**
	 * @return the type
	 */
	public String getType() {
		
		return type;
		
	}

	/**
	 * @return the start
	 */
	public LocalDateTime getStart() {
		
		return start;
		
	}

	/**
	 * @return the end
	 */
	public LocalDateTime getEnd() {
		
		return end;
		
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		
		return customerId;
		
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		
		return userId;
		
	}

	/**
	 * @return the contactId
	 */
	public int getContactId() {
		
		return contactId;
		
	}

	/**
	 * @param appointmentId the appointmentId to set
	 */
	public void setAppointmentId(int appointmentId) {
		
		this.appointmentId = appointmentId;
		
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		
		this.title = title;
		
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		
		this.description = description;
		
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		
		this.location = location;
		
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		
		this.type = type;
		
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(LocalDateTime start) {
		
		this.start = start;
		
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDateTime end) {
		
		this.end = end;
		
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		
		this.customerId = customerId;
		
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		
		this.userId = userId;
		
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(int contactId) {
		
		this.contactId = contactId;
		
	}
	
}
