package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Change in the api
 * 
 * @author kokotchy
 */
public class Change {

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

	/**
	 * Type of change
	 */
	private String type;

	/**
	 * Value of change
	 */
	private String value;

	/**
	 * Return the type of the change
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Return the value of the change
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the type of the change
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Set the value of the change
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return type + " " + value;
	}
}
