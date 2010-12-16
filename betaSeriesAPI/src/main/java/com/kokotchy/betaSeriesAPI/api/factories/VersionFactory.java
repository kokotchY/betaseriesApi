package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Version;

/**
 * Version factory
 * 
 * @author kokotchy
 */
public class VersionFactory {
	/**
	 * TODO Fill it
	 */
	private static final String CHANGES = "changes";

	/**
	 * TODO Fill it
	 */
	private static final String DATE = "date";

	/**
	 * Create a new version from the json object
	 * 
	 * @param versionObject
	 *            json object
	 * @return Version
	 */
	public static Version createVersion(JSONObject versionObject) {
		Version version = new Version();
		version.setDate(UtilsJson.getIntValue(versionObject, DATE));

		JSONObject changes = UtilsJson.getJSONObject(versionObject, CHANGES);
		String[] names = JSONObject.getNames(changes);
		try {
			for (String name : names) {
				JSONObject changeObject = changes.getJSONObject(name);
				version.addChange(ChangeFactory.createChange(changeObject));
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
		version.setDate(UtilsXml.readInt(node, DATE));

		List<Node> changes = node.selectNodes("changes/change");
		for (Node changeNode : changes) {
			version.addChange(ChangeFactory.createChange(changeNode));
		}

		return version;
	}
}
