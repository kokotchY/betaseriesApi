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
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static Error createError(JSONObject jsonObject) {
		Error error = new Error();
		error.setCode(UtilsJson.getIntValue(jsonObject, "code"));
		error.setText(UtilsJson.getStringValue(jsonObject, "content"));
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
		int code = UtilsXml.getAttributeIntValue(node);
		Error error = new Error(code);
		error.setText(node.getText());
		return error;
	}

}
