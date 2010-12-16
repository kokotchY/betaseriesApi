package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Comment;

/**
 * Comments factory
 * 
 * @author kokotchy
 */
public class CommentFactory {
	/**
	 * TODO Fill it
	 */
	private static final String IN_REPLY_TO = "in_reply_to";

	/**
	 * TODO Fill it
	 */
	private static final String LOGIN = "login";

	/**
	 * TODO Fill it
	 */
	private static final String INNER_ID = "inner_id";

	/**
	 * TODO Fill it
	 */
	private static final String DATE = "date";

	/**
	 * TODO Fill it
	 */
	private static final String TEXT = "text";

	/**
	 * Create a comment from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Comment
	 */
	public static Comment createComment(JSONObject jsonObject) {
		Comment comment = new Comment();
		comment.setContent(UtilsJson.getStringValue(jsonObject, TEXT));
		comment.setDate(UtilsJson.getIntValue(jsonObject, DATE));
		comment.setInnerId(UtilsJson.getIntValue(jsonObject, INNER_ID));
		comment.setLogin(UtilsJson.getStringValue(jsonObject, LOGIN));
		comment.setReplyToId(UtilsJson.getIntValue(jsonObject, IN_REPLY_TO));
		return comment;
	}

	/**
	 * Create a comment from a node
	 * 
	 * @param node
	 *            Node
	 * @return Comment
	 */
	public static Comment createComment(Node node) {
		Comment comment = new Comment();
		comment.setContent(UtilsXml.readString(node, TEXT));
		comment.setDate(UtilsXml.readInt(node, DATE));
		comment.setInnerId(UtilsXml.readInt(node, INNER_ID));
		comment.setLogin(UtilsXml.readString(node, LOGIN));
		comment.setReplyToId(UtilsXml.readInt(node, IN_REPLY_TO));
		return comment;
	}
}
