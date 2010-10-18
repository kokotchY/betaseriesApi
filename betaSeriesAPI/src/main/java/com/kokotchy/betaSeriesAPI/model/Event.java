package com.kokotchy.betaSeriesAPI.model;

/**
 * @author kokotchy
 * 
 */
public class Event {

	/**
	 * 
	 */
	private EventType type;

	/**
	 * 
	 */
	private String ref;

	/**
	 * 
	 */
	private String login;

	/**
	 * 
	 */
	private String event;

	/**
	 * 
	 */
	private int date;

	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param ref
	 *            the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
