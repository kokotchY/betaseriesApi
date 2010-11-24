package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

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

	// /**
	// * Event
	// */
	// private String event;

	/**
	 * Date
	 */
	private int date;

	/**
	 * Html
	 */
	private String html;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Event)) {
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

	// /**
	// * Return the event
	// *
	// * @return the event
	// */
	// public String getEvent() {
	// return event;
	// }

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

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, type);
		result = HashCodeUtil.hash(result, ref);
		result = HashCodeUtil.hash(result, login);
		// result = HashCodeUtil.hash(result, event);
		result = HashCodeUtil.hash(result, date);
		result = HashCodeUtil.hash(result, html);
		return result;
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

	// /**
	// * Set the event
	// *
	// * @param event
	// * the event to set
	// */
	// public void setEvent(String event) {
	// this.event = event;
	// }

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
		if (type.equals("friend_add")) {
			setType(EventType.FRIEND_ADD);
		} else if (type.equals("friend_delete")) {
			setType(EventType.FRIEND_DELETE);
		} else if (type.equals("markas")) {
			setType(EventType.MARKAS);
		} else if (type.equals("add_serie")) {
			setType(EventType.ADD_SERIE);
		} else if (type.equals("del_serie")) {
			setType(EventType.DEL_SERIE);
		} else if (type.equals("archive")) {
			setType(EventType.ARCHIVE);
		} else if (type.equals("unarchive")) {
			setType(EventType.UNARCHIVE);
		} else if (type.equals("recommandation")) {
			setType(EventType.RECOMMANDATION);
		} else if (type.equals("recommandation_decline")) {
			setType(EventType.RECOMMANDATION_DECLINE);
		} else if (type.equals("recommandation_accept")) {
			setType(EventType.RECOMMANDATION_ACCEPT);
		} else if (type.equals("inscription")) {
			setType(EventType.INSCRIPTION);
		} else if (type.equals("update")) {
			setType(EventType.UPDATE);
		} else if (type.equals("subtitles")) {
			setType(EventType.SUBTITLES);
		} else if (type.equals("comment")) {
			setType(EventType.COMMENT);
		}
	}

	@Override
	public String toString() {
		String format = "[%s] %s";
		return String.format(format, type, html);
	}
}
