package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;
import com.kokotchy.betaSeriesAPI.model.Version;
import com.kokotchy.betaSeriesAPI.model.VersionFile;

/**
 * @author canas
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
		statusInfo.setWebsiteStatus(UtilsXml.readString(websiteNode, "status"));
		statusInfo.setDatabaseStatus(UtilsXml.readString(websiteNode,
				"database"));

		Node apiNode = document.selectSingleNode("/root/api");
		statusInfo.setVersion(UtilsXml.readString(apiNode, "version"));
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
				"status"));
		statusInfo.setDatabaseStatus(UtilsJson.getStringValue(websiteObject,
				"database"));

		JSONObject apiObject = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/api");
		statusInfo.setVersion(UtilsJson.getStringValue(apiObject, "version"));
		try {
			JSONObject versions = UtilsJson
					.getJSONObject(apiObject, "versions");
			String[] versionsName = JSONObject.getNames(versions);
			for (String name : versionsName) {
				JSONObject versionObject = versions.getJSONObject(name);
				Version version = VersionFactory.createVersion(versionObject);
				statusInfo.addVersion(version);
			}

			JSONObject files = UtilsJson.getJSONObject(apiObject, "files");
			String[] filesName = JSONObject.getNames(files);
			for (String name : filesName) {
				JSONObject fileObject = files.getJSONObject(name);
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
