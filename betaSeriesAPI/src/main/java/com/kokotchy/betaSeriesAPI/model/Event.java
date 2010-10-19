package com.kokotchy.betaSeriesAPI.model;

/**
 * Model of an event
 * 
 * @author kokotchy
 */
public class Event {

	/**
	 * Type of the event
	 */
	private EventType type;

	/**
	 * Reference (???)
	 */
	private String ref;

	/**
	 * Login of the author of the event
	 */
	private String login;

	/**
	 * Event
	 */
	private String event;

	/**
	 * Date
	 */
	private int date;

	/**
	 * Return the date
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Return the event
	 * 
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Return the login
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Return the ref
	 * 
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * return the type
	 * 
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * Set the date
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * Set the event
	 * 
	 * @param event
	 *            the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * Set the login
	 * 
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set the ref
	 * 
	 * @param ref
	 *            the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * Set the type
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/**
	 * Set the type
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
