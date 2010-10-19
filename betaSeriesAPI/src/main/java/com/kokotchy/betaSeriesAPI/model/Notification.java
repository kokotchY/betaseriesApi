package com.kokotchy.betaSeriesAPI.model;

/**
 * Model of a notification
 * 
 * @author kokotchy
 */
public class Notification {

	/**
	 * Id of the notification
	 */
	private int id;

	/**
	 * Content of the notification
	 */
	private String html;

	/**
	 * Date of the notification
	 */
	private int date;

	/**
	 * Seen flag of the notification
	 */
	private boolean seen;

	/**
	 * Return the date
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Return the content
	 * 
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * Return the id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * If the notification is seen
	 * 
	 * @return the seen
	 */
	public boolean isSeen() {
		return seen;
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
	 * Set the html
	 * 
	 * @param html
	 *            the html to set
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * Set the id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set if the notifictaion is seen
	 * 
	 * @param seen
	 *            the seen to set
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
