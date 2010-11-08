package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * A version of the api
 * 
 * @author kokotchy
 */
public class Version {

	/**
	 * Create a new version from the json object
	 * 
	 * @param versionObject
	 *            json object
	 * @return Version
	 */
	public static Version createVersion(JSONObject versionObject) {
		Version version = new Version();
		version.setDate(UtilsJson.getIntValue(versionObject, "date"));

		JSONArray changesArray = UtilsJson.getJSONArray(versionObject,
				"changes");
		try {
			int length = changesArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject changeObject = changesArray.getJSONObject(i);
				version.addChange(Change.createChange(changeObject));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return version;
	}

	/**
	 * Create a version from the node
	 * 
	 * @param node
	 *            Node
	 * @return Version
	 */
	@SuppressWarnings("unchecked")
	public static Version createVersion(Node node) {
		Version version = new Version();
		version.setDate(UtilsXml.readInt(node, "date"));

		List<Node> changes = node.selectNodes("changes/change");
		for (Node changeNode : changes) {
			version.addChange(Change.createChange(changeNode));
		}

		return version;
	}

	/**
	 * Date
	 */
	private int date;

	/**
	 * List of changes
	 */
	private List<Change> changes = new LinkedList<Change>();

	/**
	 * Add a change
	 * 
	 * @param change
	 *            Change
	 */
	private void addChange(Change change) {
		changes.add(change);
	}

	/**
	 * Return the changes
	 * 
	 * @return the changes
	 */
	public List<Change> getChanges() {
		return changes;
	}

	/**
	 * Return the date
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Set the date
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}
}
