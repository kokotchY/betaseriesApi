package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Model of an event
 * 
 * @author kokotchy
 */
public class Event {

	/**
	 * Create a new event from a node
	 * 
	 * @param node
	 *            Node
	 * @return Event
	 */
	public static Event createEvent(Node node) {
		Event event = new Event();
		event.setType(Utils.readString(node, "type"));
		event.setRef(Utils.readString(node, "ref"));
		event.setLogin(Utils.readString(node, "login"));
		event.setHtml(Utils.readString(node, "html"));
		event.setDate(Utils.readInt(node, "date"));
		return event;
	}

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
	 * Html
	 */
	private String html;

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
	 *Return the html
	 * 
	 * @return Return the html to get
	 */
	public String getHtml() {
		return html;
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
	 * Set the html
	 * 
	 * @param html
	 *            Html
	 */
	public void setHtml(String html) {
		this.html = html;
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
		if (type.equals("markas")) {
			setType(EventType.MARKAS);
		}
	}

	@Override
	public String toString() {
		String format = "[%s] %s";
		return String.format(format, type, event);
	}
}
