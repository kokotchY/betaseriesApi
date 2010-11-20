package com.kokotchy.betaSeriesAPI.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
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

		JSONObject changes = UtilsJson.getJSONObject(versionObject, "changes");
		String[] names = JSONObject.getNames(changes);
		try {
			for (String name : names) {
				JSONObject changeObject = changes.getJSONObject(name);
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
	private Map<String, Change> changes = new HashMap<String, Change>();

	/**
	 * Add a change
	 * 
	 * @param change
	 *            Change
	 */
	private void addChange(Change change) {
		changes.put(change.getValue(), change);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Version)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the changes
	 * 
	 * @return the changes
	 */
	public Map<String, Change> getChanges() {
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

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, date);
		result = HashCodeUtil.hash(result, changes);
		return result;
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
