package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Version;

/**
 * Version factory
 * 
 * @author kokotchy
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
		version.setDate(UtilsJson.getIntValue(versionObject, Constants.DATE));

		JSONObject[] jsonObjects = UtilsJson.getArray(UtilsJson.getJSONObject(versionObject,
				Constants.CHANGES));
		for (JSONObject changeObject : jsonObjects) {
			version.addChange(ChangeFactory.createChange(changeObject));
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
		version.setDate(UtilsXml.readInt(node, Constants.DATE));

		List<Node> changes = node.selectNodes("changes/change");
		for (Node changeNode : changes) {
			version.addChange(ChangeFactory.createChange(changeNode));
		}

		return version;
	}
}
