package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Model of a comment
 * 
 * @author kokotchy
 */
public class Comment {

	/**
	 * Login of the author of the comment
	 */
	private String login;

	/**
	 * Date of the comment
	 */
	private int date;

	/**
	 * Content of the comment
	 */
	private String content;

	/**
	 * Internal id of the message
	 */
	private int innerId;

	/**
	 * Id of the message replied
	 */
	private int replyToId;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Comment)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the content
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
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
	 * Return the id
	 * 
	 * @return the innerId
	 */
	public int getInnerId() {
		return innerId;
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
	 * Return the responsed id's comment
	 * 
	 * @return the replyToId
	 */
	public int getReplyToId() {
		return replyToId;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, login);
		result = HashCodeUtil.hash(result, date);
		result = HashCodeUtil.hash(result, content);
		result = HashCodeUtil.hash(result, innerId);
		result = HashCodeUtil.hash(result, replyToId);
		return result;
	}

	/**
	 * Set the content of the comment
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Set the date of the comment
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * Set the id of the comment
	 * 
	 * @param innerId
	 *            the innerId to set
	 */
	public void setInnerId(int innerId) {
		this.innerId = innerId;
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
	 * Set the replied id's comment
	 * 
	 * @param replyToId
	 *            the replyToId to set
	 */
	public void setReplyToId(int replyToId) {
		this.replyToId = replyToId;
	}

	@Override
	public String toString() {
		String pattern = "[%d] %s - %s";
		return String.format(pattern, date, login, content);
	}

}
