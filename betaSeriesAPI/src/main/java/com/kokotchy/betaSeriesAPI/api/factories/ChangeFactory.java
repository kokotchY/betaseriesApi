package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Change;

/**
 * Factory to create change
 * 
 * @author kokotchy
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
		change.setType(UtilsJson.getStringValue(changeObject, Constants.TYPE));
		change
				.setValue(UtilsJson.getStringValue(changeObject,
						Constants.VALUE));
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
		change.setType(UtilsXml.getString(node, Constants.TYPE));
		change.setValue(UtilsXml.getString(node, Constants.VALUE));
		return change;
	}
}
