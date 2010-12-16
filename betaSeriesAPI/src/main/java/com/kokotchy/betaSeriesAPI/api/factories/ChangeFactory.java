package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Change;

/**
 * Factory to create change
 * 
 * @author kokotchy
 */
public class ChangeFactory {
	/**
	 * TODO Fill it
	 */
	private static final String VALUE = "value";

	/**
	 * TODO Fill it
	 */
	private static final String TYPE = "type";

	/**
	 * Create a new change from the json object
	 * 
	 * @param changeObject
	 *            json object
	 * @return Change
	 */
	public static Change createChange(JSONObject changeObject) {
		Change change = new Change();
		change.setType(UtilsJson.getStringValue(changeObject, TYPE));
		change.setValue(UtilsJson.getStringValue(changeObject, VALUE));
		return change;
	}

	/**
	 * Create a new change from the node
	 * 
	 * @param node
	 *            Node
	 * @return Change
	 */
	public static Change createChange(Node node) {
		Change change = new Change();
		change.setType(UtilsXml.readString(node, TYPE));
		change.setValue(UtilsXml.readString(node, VALUE));
		return change;
	}
}
