package com.kokotchy.betaSeriesAPI.model;

/**
 * @author kokotchy
 * 
 */
public class Notification {

	/**
	 * 
	 */
	private int id;

	/**
	 * 
	 */
	private String html;

	/**
	 * 
	 */
	private int date;

	/**
	 * 
	 */
	private boolean seen;

	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the seen
	 */
	public boolean isSeen() {
		return seen;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * @param html
	 *            the html to set
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param seen
	 *            the seen to set
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
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
