package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Change;
import com.kokotchy.betaSeriesAPI.model.Version;

/**
 * @author canas
 */
public class VersionFactory {
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
}
