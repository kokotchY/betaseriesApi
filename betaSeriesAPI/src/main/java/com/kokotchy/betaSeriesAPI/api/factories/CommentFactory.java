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
	 * Create a comment from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Comment
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
	 * Create a comment from a node
	 * 
	 * @param node
	 *            Node
	 * @return Comment
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
}
