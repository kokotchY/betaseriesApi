package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Error;

/**
 * Error factory
 * 
 * @author kokotchy
 */
public class ErrorFactory {
	/**
	 * Create an error from a json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Error
	 */
	public static Error createError(JSONObject jsonObject) {
		Error error = new Error();
		error.setCode(UtilsJson.getIntValue(jsonObject, Constants.CODE));
		error.setText(UtilsJson.getStringValue(jsonObject, Constants.CONTENT));
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
		int code = UtilsXml.getAttributeIntValue(node, Constants.CODE);
		Error error = new Error(code);
		error.setText(node.getText());
		return error;
	}

}
