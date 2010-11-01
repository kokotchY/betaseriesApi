package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Change {

	/**
	 * TODO Fill it
	 * 
	 * @param node
	 * @return
	 */
	public static Change createChange(Node node) {
		Change change = new Change();
		change.setType(Utils.readString(node, "type"));
		change.setValue(Utils.readString(node, "value"));
		return change;
	}

	/**
	 * TODO Fill it
	 */
	private String type;

	/**
	 * TODO Fill it
	 */
	private String value;

	/**
	 * TODO Fill it
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * TODO Fill it
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
