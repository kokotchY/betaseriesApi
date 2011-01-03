package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;
import com.kokotchy.betaSeriesAPI.model.Version;
import com.kokotchy.betaSeriesAPI.model.VersionFile;

/**
 * StatusInfo factory
 * 
 * @author kokotchy
 */
public class StatusInfoFactory {
	/**
	 * Create the status informations from the document
	 * 
	 * @param document
	 *            Document
	 * @return Status informations
	 */
	@SuppressWarnings("unchecked")
	public static StatusInfo createStatusInfo(Document document) {
		StatusInfo statusInfo = new StatusInfo();
		Node websiteNode = document.selectSingleNode("/root/website");
		statusInfo.setWebsiteStatus(UtilsXml.readString(websiteNode,
				Constants.STATUS));
		statusInfo.setDatabaseStatus(UtilsXml.readString(websiteNode,
				Constants.DATABASE));

		Node apiNode = document.selectSingleNode("/root/api");
		statusInfo.setVersion(UtilsXml.readString(apiNode, Constants.VERSION));
		List<Node> versions = apiNode.selectNodes("versions/version");
		for (Node node : versions) {
			Version version = VersionFactory.createVersion(node);
			statusInfo.addVersion(version);
		}

		List<Node> files = apiNode.selectNodes("files/file");
		for (Node node : files) {
			VersionFile file = VersionFileFactory.createVersionFile(node);
			statusInfo.addVersionFile(file);
		}
		return statusInfo;
	}

	/**
	 * Create a status informations from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Status informations
	 */
	public static StatusInfo createStatusInfo(JSONObject jsonObject) {
		StatusInfo statusInfo = new StatusInfo();
		JSONObject websiteObject = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/website");
		statusInfo.setWebsiteStatus(UtilsJson.getStringValue(websiteObject,
				Constants.STATUS));
		statusInfo.setDatabaseStatus(UtilsJson.getStringValue(websiteObject,
				Constants.DATABASE));

		JSONObject apiObject = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/api");
		statusInfo.setVersion(UtilsJson.getStringValue(apiObject,
				Constants.VERSION));
		JSONObject versions = UtilsJson.getJSONObject(apiObject,
				Constants.VERSIONS);
		JSONObject[] array = UtilsJson.getArray(versions);
		for (JSONObject versionObject : array) {
			Version version = VersionFactory.createVersion(versionObject);
			statusInfo.addVersion(version);
		}

		JSONObject files = UtilsJson.getJSONObject(apiObject,
				Constants.FILES);
		// JSONObject[] fileArray = UtilsJson.getArray(files);
		String[] filesName = new String[] { "comments", "members", "planning", "shows", "subtitles", "timeline" };
		try {
			for (String filename : filesName) {
				JSONObject fileObject = files.getJSONObject(filename);
				VersionFile file = VersionFileFactory
						.createVersionFile(fileObject);
				statusInfo.addVersionFile(file);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return statusInfo;
	}
}
