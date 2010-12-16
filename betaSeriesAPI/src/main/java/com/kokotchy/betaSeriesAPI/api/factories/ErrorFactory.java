package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Error;

/**
 * Error factory
 * 
 * @author kokotchy
 */
public class ErrorFactory {
	/**
	 * TODO Fill it
	 */
	private static final String CONTENT = "content";

	/**
	 * TODO Fill it
	 */
	private static final String CODE = "code";

	/**
	 * Create an error from a json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Error
	 */
	public static Error createError(JSONObject jsonObject) {
		Error error = new Error();
		error.setCode(UtilsJson.getIntValue(jsonObject, CODE));
		error.setText(UtilsJson.getStringValue(jsonObject, CONTENT));
		return error;
	}

	/**
	 * Create an error from a node
	 * 
	 * @param node
	 *            Node
	 * @return Error
	 */
	public static Error createError(Node node) {
		int code = UtilsXml.getAttributeIntValue(node, CODE);
		Error error = new Error(code);
		error.setText(node.getText());
		return error;
	}

}
