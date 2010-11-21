package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Model of a comment
 * 
 * TODO Fill it
 * 
 * @author kokotchy
 */
public class Comment {

	/**
	 * TODO Fill it
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static Comment createComment(JSONObject jsonObject) {
		Comment comment = new Comment();
		comment.setContent(UtilsJson.getStringValue(jsonObject, "text"));
		comment.setDate(UtilsJson.getIntValue(jsonObject, "date"));
		comment.setInnerId(UtilsJson.getIntValue(jsonObject, "inner_id"));
		comment.setLogin(UtilsJson.getStringValue(jsonObject, "login"));
		comment.setReplyToId(UtilsJson.getIntValue(jsonObject, "in_reply_to"));
		return comment;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param node
	 * @return
	 */
	public static Comment createComment(Node node) {
		Comment comment = new Comment();
		comment.setContent(UtilsXml.readString(node, "text"));
		comment.setDate(UtilsXml.readInt(node, "date"));
		comment.setInnerId(UtilsXml.readInt(node, "inner_id"));
		comment.setLogin(UtilsXml.readString(node, "login"));
		comment.setReplyToId(UtilsXml.readInt(node, "in_reply_to"));
		return comment;
	}

	/**
	 * TODO Fill it
	 */
	private String login;

	/**
	 * TODO Fill it
	 */
	private int date;

	/**
	 * TODO Fill it
	 */
	private String content;

	/**
	 * TODO Fill it
	 */
	private int innerId;

	/**
	 * TODO Fill it
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
	 * TODO Fill it
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the innerId
	 */
	public int getInnerId() {
		return innerId;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * TODO Fill it
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
	 * TODO Fill it
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param innerId
	 *            the innerId to set
	 */
	public void setInnerId(int innerId) {
		this.innerId = innerId;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param replyToId
	 *            the replyToId to set
	 */
	public void setReplyToId(int replyToId) {
		this.replyToId = replyToId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
