package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Change;

/**
 * @author canas
 */
public class ChangeFactory {
	/**
	 * Create a new change from the json object
	 * 
	 * @param changeObject
	 *            json object
	 * @return Change
	 */
	public static Change createChange(JSONObject changeObject) {
		Change change = new Change();
		change.setType(UtilsJson.getStringValue(changeObject, "type"));
		change.setValue(UtilsJson.getStringValue(changeObject, "value"));
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
		change.setType(UtilsXml.readString(node, "type"));
		change.setValue(UtilsXml.readString(node, "value"));
		return change;
	}
}
