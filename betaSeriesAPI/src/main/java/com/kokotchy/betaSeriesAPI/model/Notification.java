package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Notification)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

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

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, id);
		result = HashCodeUtil.hash(result, html);
		result = HashCodeUtil.hash(result, date);
		result = HashCodeUtil.hash(result, seen);
		return result;
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
		return String.format("%s[%d] %s", !seen ? "*" : "", id, html);
	}
}
